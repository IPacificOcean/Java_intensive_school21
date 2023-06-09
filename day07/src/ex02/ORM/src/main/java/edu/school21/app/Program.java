package edu.school21.app;

import edu.school21.processor.OrmProcessor;

public class Program {
    public static void main(String[] args) {
        OrmProcessor ormProcessor = new OrmProcessor();
        ormProcessor.createSchema();
//        ormProcessor.createTable();
    }
}
