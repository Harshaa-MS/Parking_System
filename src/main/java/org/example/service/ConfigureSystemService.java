package org.example.service;

import org.example.DTO.ParkingLot;

public interface ConfigureSystemService {
    ParkingLot configureSystem () ;
    void startSystem(ParkingLot parkingLot, boolean flag) ;
}
