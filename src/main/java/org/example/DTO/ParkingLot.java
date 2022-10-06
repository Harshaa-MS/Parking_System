package org.example.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLot {
    @Builder.Default
    private List<String> floors = new ArrayList<>();
    @Builder.Default
    private List<String> entries= new ArrayList<>();
    @Builder.Default
    private List<String> exits = new ArrayList<>();
    @Builder.Default
    private List<VehicleType> vehicleTypeList = new ArrayList<>();

}
