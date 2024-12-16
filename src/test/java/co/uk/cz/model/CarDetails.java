package co.uk.cz.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class CarDetails {

    @CsvBindByName(column = "REGISTRATION")
    private String registration;

    @CsvBindByName(column = "MAKE")
    private String make;

    @CsvBindByName(column = "MODEL")
    private String model;

    @Override
    public String toString() {
        return "Car{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

