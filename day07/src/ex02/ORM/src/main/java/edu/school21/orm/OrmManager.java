package edu.school21.orm;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import edu.school21.repositories.CreateTables;
import edu.school21.repositories.DBWorker;
import edu.school21.repositories.SecureStatements;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OrmManager {
    private final SecureStatements statements;
    public OrmManager() {
        DBWorker dbWorker = new DBWorker();
        CreateTables createTables = new CreateTables(dbWorker);
        statements = new SecureStatements(dbWorker.getDS());
//        schema = "day07/ex02/ORM/target/classes/schema.sql";
//        data = "day07/ex02/ORM/target/classes/data.sql";
        String schema = "target/classes/schema.sql";
        String data = "target/classes/data.sql";
        createTables.CreateTab(schema, data);
    }


    public void save(Object entity) throws IllegalAccessException, SQLException {
        Class<?> clazz = entity.getClass();
        OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
        String table =ormEntity.table();
        StringJoiner columnNames = new StringJoiner(", ");
        StringJoiner substitute = new StringJoiner(", ");
        List<Object> paramVal = new ArrayList<>();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
//
        for (Field field : fields) {

            if (field.isAnnotationPresent(OrmColumn.class)) {
                field.setAccessible(true);
                OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                columnNames.add(ormColumn.name());
                substitute.add("?");
                paramVal.add(field.get(entity));
            }
        }
        String querySave = String.format("insert into orm.%s (%s)\nvalues (%s)"
                , table, columnNames, substitute);

        statements.preparedStatement(querySave, (statement) -> {
            for (int i =0; i < paramVal.size(); ++i) {
                statement.setObject(i + 1, paramVal.get(i));
            }
            statement.executeUpdate();
            System.out.println(statement.unwrap(PreparedStatement.class).toString(). replace("RETURNING *", ""));
        });
    }

    public void update(Object entity) throws IllegalAccessException, SQLException {
        Class<?> clazz = entity.getClass();
        OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
        String table = ormEntity.table();
        StringBuilder query = new StringBuilder();
        query.append("update orm.").append(table).append(" set ");
        List<Object> paramValues = new ArrayList<>();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        System.out.println(query);
        Object id = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(OrmColumnId.class)) {
                id = field.get(entity);
            }
            if (field.isAnnotationPresent(OrmColumn.class)) {
                OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                query.append(ormColumn.name()).append(" = ?, ");
                paramValues.add(field.get(entity));
            }
        }
        query.setLength(query.length() - 2);
        query.append(" where id = ?");

        Object finalId = id;
        statements.preparedStatement(query.toString(), (stmt) -> {
            for (int i = 0; i < paramValues.size(); i++) {
                stmt.setObject(i + 1, paramValues.get(i));
            }
            stmt.setObject(paramValues.size() + 1, finalId);
            stmt.executeUpdate();
            System.out.println(stmt.unwrap(PreparedStatement.class)
                    .toString().replace("RETURNING *", ""));
        });
    }

    public <T> T findById(Long id, Class<T> aClass) throws SQLException {
        OrmEntity ormEntity = aClass.getAnnotation(OrmEntity.class);
        String table = ormEntity.table();

        String query = String.format("select * from orm.%s where id = ?", table);

        return statements.preparedStatement(query, (stmt) -> {
            stmt.setLong(1, id);
            System.out.println(stmt.unwrap(PreparedStatement.class)
                    .toString().replace("RETURNING *", ""));
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            T object = null;
            try {
                object = aClass.getDeclaredConstructor().newInstance();

                for (Field field : aClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = null;
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        value = resultSet.getObject(field.getAnnotation(OrmColumnId.class).id());
                    }
                    if (field.isAnnotationPresent(OrmColumn.class)) {
                        value = resultSet.getObject(field.getAnnotation(OrmColumn.class).name());
                    }

                    field.set(object, value);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
            return object;
        });
    }

}
