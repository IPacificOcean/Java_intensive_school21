package edu.school21.orm;

import edu.school21.repositories.CreateTables;
import edu.school21.repositories.DBWorker;

public class OrmManager {
    public OrmManager() {
        DBWorker dbWorker = new DBWorker();
        CreateTables createTables = new CreateTables(dbWorker);
//        schema = "day07/ex02/ORM/target/classes/schema.sql";
//        data = "day07/ex02/ORM/target/classes/data.sql";
        String schema = "target/classes/schema.sql";
        String data = "target/classes/data.sql";
        createTables.CreateTab(schema, data);
    }


    public void save(Object object) {
    }

    public void update(Object object) {
    }

    public <T> T findById(Long id, Class<T> aClass) {
        return null;
    }

}
