package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    private String vehicleNumber;
    private String vehicleType;
    private boolean isParked;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private String floor;
    private String entry;
    private String exit;
    private int amount;
    private String paymentType;
    private boolean isPaid;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", isParked=" + (isParked ? " Yes" : " No") +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", floor='" + floor + '\'' +
                ", entry='" + entry + '\'' +
                ", exit='" + exit + '\'' +
                ", amount=" + amount +
                ", paymentType='" + paymentType + '\'' +
                ", isPaid=" + (isPaid ? " Yes" : " No") +
                '}';
    }
}
