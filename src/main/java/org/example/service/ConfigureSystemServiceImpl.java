package org.example.service;

import org.example.DTO.ParkingLot;
import org.example.DTO.VehicleType;

import java.util.Scanner;

public class ConfigureSystemServiceImpl implements ConfigureSystemService {

    private VehicleService vehicleService = new VehicleServiceImpl();

    public ConfigureSystemServiceImpl() {
    }

    public ConfigureSystemServiceImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    Scanner scanner = new Scanner(System.in);


    @Override
    public ParkingLot configureSystem() {
        ParkingLot parkingLot = new ParkingLot();

        System.out.println("Welcome to the System");
        System.out.println("Enter basic details about parking lot");
        System.out.println("Enter the number of floors");
        int numberOfFloors = scanner.nextInt();

        for (int i = 0; i < numberOfFloors; i++) {
            parkingLot.getFloors().add("Floor " + (i + 1));
        }
        System.out.println("Enter the number of entries");
        int numberOfEntries = scanner.nextInt();
        for (int i = 0; i < numberOfEntries; i++) {
            parkingLot.getEntries().add("Entry " + (i + 1));
        }
        System.out.println("Enter the number of exits");
        int numberOfExits = scanner.nextInt();
        for (int i = 0; i < numberOfExits; i++) {
            parkingLot.getExits().add("Exit " + (i + 1));
        }
        System.out.println("Enter the number of vehicle types allowed");
        int numberOfVehicleTypes = scanner.nextInt();
        VehicleType vehicleType;
        for (int i = 0; i < numberOfVehicleTypes; i++) {
            vehicleType = new VehicleType();
            System.out.println("Enter vehicle type ");
            vehicleType.setName(scanner.next());
            System.out.println("Enter the capacity of particular type");
            vehicleType.setCapacity(scanner.nextInt());
            parkingLot.getVehicleTypeList().add(vehicleType);
        }



        return parkingLot;
    }

    @Override
    public void startSystem(ParkingLot parkingLot, boolean flag) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Parking Floors: " + parkingLot.getFloors().size() + ", Number of Entries: " + parkingLot.getEntries().size() + ", Number of exits: " + parkingLot.getExits().size());
            for (VehicleType vehicleType1 : parkingLot.getVehicleTypeList()) {
                System.out.println("Vehicle Type: " + vehicleType1.getName() + ", Capacity: " + vehicleType1.getCapacity() + ", Available Slot: " + vehicleService.availableSlot(vehicleType1.getName(), vehicleType1.getCapacity()));
            }
            System.out.println(" 1 for Adding vehicle to parking lot");
            System.out.println(" 2 for Removing vehicle from parking lot");
            System.out.println(" 3 for All vehicle details");
            System.out.println(" 4 for All parked vehicle details");
            System.out.println(" 5 for All exited vehicle details");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> vehicleService.addVehicle(parkingLot);
                case 2 -> vehicleService.removeVehicle(parkingLot);
                case 3 -> vehicleService.getAllVehicleDetails();
                case 4 -> vehicleService.getAllParkedVehicleDetails();
                case 5 -> vehicleService.getAllExitedVehicle();
                default -> {
                }
            }

            if (flag) {
                break;
            }
        }
    }
}
