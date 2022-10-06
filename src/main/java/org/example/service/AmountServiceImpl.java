package org.example.service;

import org.example.DTO.Vehicle;

import java.time.temporal.ChronoUnit;

public class AmountServiceImpl implements AmountService{
    @Override
    public int calculateTotalAmount(Vehicle vehicle) {
        long minutes = vehicle.getInTime().until(vehicle.getOutTime(), ChronoUnit.MINUTES);
        int amount = 0;
        int count = 1;
        while (minutes > 0){
            if(count == 1){
                minutes = minutes -1;
                amount += 40;
            }else if(count == 2 || count == 3){
                minutes = minutes -1;
                amount+=30;
            }else{
                minutes = minutes-1;
                amount+=20;
            }
            count++;
        }
     return amount;
    }
}
