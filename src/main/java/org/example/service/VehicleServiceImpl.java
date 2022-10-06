package org.example.service;

import org.example.DAO.VehicleDAO;
import org.example.DTO.ParkingLot;
import org.example.DTO.Vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class VehicleServiceImpl implements VehicleService {

    private  VehicleDAO vehicleDAO = new VehicleDAO();
    private  AmountService amountService = new AmountServiceImpl();

    public VehicleServiceImpl() {
    }

    public VehicleServiceImpl(AmountService amountService) {
        this.amountService = amountService;
    }

    public VehicleServiceImpl(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public VehicleServiceImpl(VehicleDAO vehicleDAO, AmountService amountService) {
        this.vehicleDAO = vehicleDAO;
        this.amountService = amountService;
    }

    @Override
    public void addVehicle(ParkingLot parkingLot) {
        Vehicle vehicle = new Vehicle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose vehicle type ");
        for (int i = 1; i <= parkingLot.getVehicleTypeList().size(); i++) {
            System.out.println(i + " for " + parkingLot.getVehicleTypeList().get(i - 1).getName());
        }
        int vehicleTypeId = scanner.nextInt();
        if (vehicleTypeId >= 1 && vehicleTypeId <= parkingLot.getVehicleTypeList().size()) {
            boolean isSlotAvailable = vehicleDAO.checkForSlotAvailability(parkingLot.getVehicleTypeList().get(vehicleTypeId - 1).getName(),
                    parkingLot.getVehicleTypeList().get(vehicleTypeId - 1).getCapacity());
            if(!isSlotAvailable){
                System.out.println("No slot available for this vehicle type ");
                return;
            }
            vehicle.setVehicleType(parkingLot.getVehicleTypeList().get(vehicleTypeId - 1).getName());
        } else {
            System.out.println("Wrong choice returning back to main menu");
            return;
        }
        System.out.println("Enter vehicle number");
        vehicle.setVehicleNumber(scanner.next());


        System.out.println("Choose floor");
        for (int i = 1; i <= parkingLot.getFloors().size(); i++) {
            System.out.println(i + " for " + parkingLot.getFloors().get(i - 1));
        }
        int floorId = scanner.nextInt();
        if (floorId >= 1 && floorId <= parkingLot.getFloors().size()) {
            vehicle.setFloor(parkingLot.getFloors().get(floorId - 1));
        } else {
            System.out.println("Wrong choice returning back to main menu");
            return;
        }
        System.out.println("Choose entry gate  ");
        for (int i = 1; i <= parkingLot.getEntries().size(); i++) {
            System.out.println(i + " for " + parkingLot.getEntries().get(i - 1));
        }
        int entryId = scanner.nextInt();
        if (entryId >= 1 && entryId <= parkingLot.getEntries().size()) {
            vehicle.setEntry(parkingLot.getEntries().get(entryId - 1));
        } else {
            System.out.println("Wrong choice returning back to main menu");
            return;
        }
        System.out.println(" If the customer has paid at the info portal");
        System.out.println(" 1 for Yes, 2 for No");
        int paid = scanner.nextInt();
        if (paid == 1) {
            vehicle.setPaid(true);
            System.out.println("Choose payment type");
            System.out.println(" 1 for Cash, 2 for Card");
            int paymentTypeId = scanner.nextInt();
            if (paymentTypeId == 1) {
                vehicle.setPaymentType("CASH");
            } else if (paymentTypeId == 2) {
                vehicle.setPaymentType("CARD");
            } else {
                System.out.println("Wrong choice returning back to main menu");
                return;
            }
        } else if (paid == 2) {
            vehicle.setPaid(false);

        } else {
            System.out.println("Wrong choice returning back to main menu");
            return;
        }
        vehicle.setInTime(LocalDateTime.now());
        vehicle.setParked(true);
        boolean flag = vehicleDAO.addVehicle(vehicle);
        if (!flag) {
            System.out.println("Something went wrong");
        }

    }

    @Override
    public void removeVehicle(ParkingLot parkingLot)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle number");
        String vehicleNumber = scanner.next();
        if (vehicleDAO.isVehiclePresent(vehicleNumber)) {
            Vehicle vehicle = vehicleDAO.getVehicle(vehicleNumber);
            System.out.println("Choose exit gate  ");
            for (int i = 1; i <= parkingLot.getExits().size(); i++) {
                System.out.println(i + " for " + parkingLot.getExits().get(i - 1));
            }
            int exitId = scanner.nextInt();
            if (exitId >= 1 && exitId <= parkingLot.getExits().size()) {
                vehicle.setExit(parkingLot.getExits().get(exitId - 1));
            } else {
                System.out.println("Wrong choice returning back to main menu");
                return;
            }
            vehicle.setOutTime(LocalDateTime.now());
            //Amount needs to be calculated
           // amountService.calculateTotalAmount(vehicle);
            vehicle.setAmount(amountService.calculateTotalAmount(vehicle));
            if (vehicle.isPaid()) {
                System.out.println(" Customer has paid at the info portal: " + vehicle.getAmount());
            } else {
                System.out.println("Amount: "+ vehicle.getAmount());
                System.out.println("Choose payment type");
                System.out.println(" 1 for Cash, 2 for Card");
                int paymentTypeId = scanner.nextInt();
                if (paymentTypeId == 1) {
                    vehicle.setPaymentType("CASH");
                } else if (paymentTypeId == 2) {
                    vehicle.setPaymentType("CARD");
                } else {
                    System.out.println("Wrong choice returning back to main menu");
                    return;
                }
            }
            vehicle.setParked(false);
        }else{
            System.out.println("Enter correct vehicle number");
        }

    }

    @Override
    public void getAllVehicleDetails() {
        List<Vehicle> list = vehicleDAO.getAllVehicle();
        for (Vehicle vehicle : list) {
            System.out.println(vehicle);
            System.out.println();
        }
    }

    @Override
    public void getAllParkedVehicleDetails() {
        List<Vehicle> list = vehicleDAO.getAllParkedVehicle();
        for (Vehicle vehicle : list) {
            System.out.println(vehicle);
            System.out.println();
        }
    }

    @Override
    public void getAllExitedVehicle() {
        List<Vehicle> list = vehicleDAO.getAllExitedVehicle();
        for (Vehicle vehicle : list) {
            System.out.println(vehicle);
            System.out.println();
        }
    }

    @Override
    public long availableSlot(String vehicleType, int capacity) {
        return vehicleDAO.availableSlot(vehicleType,capacity);
    }

}
