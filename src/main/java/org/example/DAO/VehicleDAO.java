package org.example.DAO;

import org.example.DTO.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleDAO {
    private final Map<String, Vehicle> data = new HashMap<>();

    public boolean addVehicle(Vehicle vehicle){
        try{
            data.put(vehicle.getVehicleNumber(), vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Vehicle> getAllVehicle(){
       return new ArrayList<>(data.values());
    }

    public List<Vehicle> getAllParkedVehicle(){
        List<Vehicle> list = new ArrayList<>(data.values());
        return list.stream().filter(Vehicle::isParked).collect(Collectors.toList());
    }

    public List<Vehicle> getAllExitedVehicle(){
        List<Vehicle> list =  new ArrayList<>(data.values());
        return list.stream().filter(vehicle -> !vehicle.isParked()).collect(Collectors.toList());
    }

    public boolean isVehiclePresent(String vehicleNumber){
      return   data.containsKey(vehicleNumber);
    }

    public Vehicle getVehicle(String vehicleNumber){
        return data.get(vehicleNumber);
    }

    public boolean checkForSlotAvailability(String vehicleType, int capacity) {
        List<Vehicle> list =  new ArrayList<>(data.values());
        long filledCount = list.stream().filter(vehicle -> vehicle.getVehicleType().equals(vehicleType) && vehicle.isParked()).count();
        return filledCount < capacity;
    }

    public long availableSlot(String vehicleType, int capacity){
        List<Vehicle> list =  new ArrayList<>(data.values());
        long filledCount = list.stream().filter(vehicle -> vehicle.getVehicleType().equals(vehicleType) && vehicle.isParked()).count();
        return (capacity - filledCount);
    }
}
