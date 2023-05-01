package pl.javastart.task;

public class Vehicle {

    private String type;
    private String brand;
    private String model;
    private int year;
    private int mileage;
    private String vin;

    public Vehicle(String type, String brand, String model, int year, int mileage, String vin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return type + "," + brand + "," + model + "," + year + "," + mileage +
                "," + vin + "\n";
    }

    public String toStringPrintable() {
        return type + "," + brand + "," + model + ", rocznik " + year + ", przebieg " + mileage +
                " km, VIN " + vin + "\n";
    }
}
