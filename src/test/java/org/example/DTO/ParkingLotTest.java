package org.example.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ParkingLotTest {

    public static ParkingLot buildParkingLot(){
        return ParkingLot.builder()
                .floors(new ArrayList<>(Arrays.asList("Floor 1", "Floor 2")))
                .entries(new ArrayList<>(Arrays.asList("Entry 1","Entry 2")))
                .exits(new ArrayList<>(Arrays.asList("Exit 1", "Exit 2")))
                .vehicleTypeList(new ArrayList<>(Collections.singletonList(new VehicleType(2, "Car"))))
                .build();
    }

}