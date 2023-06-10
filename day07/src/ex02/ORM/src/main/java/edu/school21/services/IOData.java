package edu.school21.services;

import edu.school21.models.Car;
import edu.school21.models.User;
import edu.school21.orm.OrmManager;

import java.sql.SQLException;

public class IOData {
    OrmManager ormManager = new OrmManager();
    public void Output() throws SQLException, IllegalAccessException {

        User user = new User(null, "Michael", "Jackson", 51);
        ormManager.save(user);

        Car car = new Car(null, "Ford", 2010);
        ormManager.save(car);

//        user.setId(2L);
//        user.setFirstName("Frank");
//        user.setLastName("Sinatra");
//        user.setAge(82);
//        ormManager.update(user);

//        car.setId(2L);
//        car.setModel("Ferrari");
//        car.setYear(2019);
//        ormManager.update(car);

        User userFindById = ormManager.findById(3L, User.class);
        System.out.println(userFindById);
        Car carFindById= ormManager.findById(3L,Car.class);
        System.out.println(carFindById);

    }
}
