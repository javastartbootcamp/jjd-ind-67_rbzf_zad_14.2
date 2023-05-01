package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DataReader {
    private static final int EXIT = 0;
    private static final int ADD_NEW = 1;
    private static final int  GET_NEXT = 2;

    private Scanner scanner = new Scanner(System.in);

    private Queue<Vehicle> vehicles = new LinkedList<>();

    public void chooseOption() {
        int option;
        do {
            printOptions();
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case EXIT -> exit();
                case ADD_NEW -> addNew();
                case GET_NEXT -> getNext();
                default -> System.out.println("Wybrana opcja nie istnieje. Podaj jeszcze raz.");
            }
        } while (option != EXIT);
    }

    private void getNext() {
        Vehicle vehicle = vehicles.poll();
        System.out.println("Nastepny pojazd do kontroli:");
        System.out.println(vehicle);
    }

    private void exit() {
        writeVehiclesToFile();
        System.out.println("Koniec programu");
    }

    private void writeVehiclesToFile() {
        String fileName = "vehicles.csv";
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for (Vehicle vehicle : vehicles) {
                writer.write(String.valueOf(vehicle));
            }
        } catch (IOException e) {
            System.err.println("Nie udalo sie zapisac danych do pliku");
        }
    }

    private void addNew() {
        Vehicle vehicle = insertVehicleData();
        vehicles.offer(vehicle);

    }

    private Queue<Vehicle> importVehiclesFromFile() {
        String fileName = "vehicles.csv";
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(",");
                Vehicle vehicle = new Vehicle(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5]);
                vehicles.offer(vehicle);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie udalo sie odczytac pliku");
        }
        return vehicles;
    }

    private Vehicle insertVehicleData() {

        System.out.println("Podaj dane pojazdu:");
        System.out.println("Typ pojazdu:");
        String type = scanner.nextLine();
        System.out.println("Marka pojazdu:");
        String brand = scanner.nextLine();
        System.out.println("Model pojazdu:");
        String model = scanner.nextLine();
        System.out.println("Rocznik pojazdu:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Przebieg pojazdu:");
        int mileage = scanner.nextInt();
        scanner.nextLine();
        System.out.println("number VIN:");
        String vin = scanner.nextLine();
        return new Vehicle(type, brand, model, year, mileage, vin);
    }

    public void printOptions() {
        System.out.println("Wybierz opcje:");
        System.out.println("0 - wyjscie z programu,");
        System.out.println("1 - wprowadz nowy pojazd do kolejki,");
        System.out.println("2 - pobierz kolejny pojazd do przegladu.");
    }

    public void printVehiclesToBeExamined() {
        if (!vehicles.isEmpty()) {
            System.out.println("Pojazdy w kolejce do przegladu:");
            for (Vehicle vehicle : vehicles) {
                System.out.print(vehicle.toStringPrintable());
            }
        }
    }
}
