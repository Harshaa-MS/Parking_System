package org.example.service;

import org.example.DTO.ParkingLot;

public interface VehicleService {
    void addVehicle(ParkingLot parkingLot) ;

    void removeVehicle(ParkingLot parkingLot);

    void getAllVehicleDetails();

    void getAllParkedVehicleDetails();

    void getAllExitedVehicle();

    long availableSlot(String vehicleType, int capacity);
}
