package edu.school21.processor;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("edu.school21.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class OrmProcessor extends AbstractProcessor {

    private final String schemaName;
//    private final Path outputPath = Paths.get("target/classes", "schema.sql");
    String fileName;
    private final StringJoiner schema;

    private final Map<String, String> convertTypes;

    public OrmProcessor() {
        schemaName = "orm";
        fileName = "schema.sql";
        schema = new StringJoiner("\n");
        convertTypes = new HashMap<>();
        convertTypes.put("String", "varchar");
        convertTypes.put("Integer", "int");
        convertTypes.put("Double", "double precision");
        convertTypes.put("Boolean", "bool");
        convertTypes.put("Long", "bigint");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        Set<? extends Element> setElements = roundEnv.getElementsAnnotatedWith(OrmEntity.class);
        if (roundEnv.getElementsAnnotatedWith(OrmEntity.class).isEmpty()) return false;
        Elements elements = processingEnv.getElementUtils();
        createSchema();

        for (Element element : roundEnv.getElementsAnnotatedWith(OrmEntity.class)) {
            OrmEntity ormEntity = element.getAnnotation(OrmEntity.class);
            List<? extends  Element> fieldElements = element.getEnclosedElements();
            String columns = fieldElements.stream().map(fieldElement -> {
                if (fieldElement.getAnnotation(OrmColumnId.class) != null) {
                    return fieldElement.getAnnotation(OrmColumnId.class).id() + " "
                            + "bigserial";
                }
                if (fieldElement.getAnnotation(OrmColumn.class) != null) {
                    return fieldElement.getAnnotation(OrmColumn.class).name() + " "
                            + " " + addType(elements, fieldElement);
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.joining(",\n"));

            createTable(ormEntity, columns);
        }

        System.out.println(schema);

        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void createSchema() {
//        StringJoiner schema = new StringJoiner(";\n");
        schema.add("drop schema if exists " + schemaName + " cascade;")
                .add("create schema if not exists " + schemaName + ";");
        System.out.println(schema);
    }

    public void createTable(OrmEntity ormEntity, String columns) {
        schema.add("create table if not exists " + schemaName + "." + ormEntity.table() + " (")
                        .add(columns + ");\n");

        System.out.println(schema);
    }

    private String getType(Elements elements, Element element) {
        return elements.getTypeElement(element.asType().toString()).getSimpleName().toString();
    }

    private String addType(Elements elements, Element element) {
        if ("String".equals(getType(elements, element))) {
            return convertTypes.get(getType(elements, element)) + "(" + element.getAnnotation(OrmColumn.class)
                    .length() + ")";
        } else {
            return convertTypes.get(getType(elements, element));
        }
    }

    public void saveToFile() throws IOException {
        Path outputPath = Paths.get("target/classes", fileName);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath);
        bufferedWriter.write(schema.toString());
        bufferedWriter.close();
    }

}
