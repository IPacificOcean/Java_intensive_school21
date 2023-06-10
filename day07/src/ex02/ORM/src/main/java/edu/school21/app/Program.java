package edu.school21.app;

import edu.school21.orm.OrmManager;
import edu.school21.services.IOData;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
//        OrmManager ormManager = new OrmManager();
        try {
            IOData ioData = new IOData();
            ioData.Output();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
