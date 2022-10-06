package org.example.DTO;

import java.time.LocalDateTime;

public class VehicleTest {
    public static Vehicle buildVehicleDTO(){
        return Vehicle.builder().build();
    }

    public static Vehicle buildVehicleDTOWithPaidStatusTrue(){
        return Vehicle.builder()
                .isPaid(true)
                .build();
    }

    public static Vehicle buildVehicleDTOWithInAndOutTime(){
        return Vehicle.builder()
                .inTime(LocalDateTime.now())
                .outTime(LocalDateTime.now().plusMinutes(10))
                .build();
    }

}