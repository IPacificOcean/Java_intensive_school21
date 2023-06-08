package edu.school21.models;


import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@OrmEntity(table= "simple_car")
public class Car {
    @OrmColumnId
    private Long id;

    @OrmColumn(name= "model", lenght = 20)
    private String model;

    @OrmColumn(name= "car_model_year")
    private Integer year;

    public Car() {}

}
