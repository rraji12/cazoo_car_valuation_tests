package co.uk.cz.model;

import com.opencsv.bean.CsvBindByName;

public class CarDetails {

    @CsvBindByName(column = "REGISTRATION")
    private String registration;

    @CsvBindByName(column = "MAKE")
    private String make;

    @CsvBindByName(column = "MODEL")
    private String model;

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

