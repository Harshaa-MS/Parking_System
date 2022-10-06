package org.example;

import org.example.DTO.ParkingLot;
import org.example.service.ConfigureSystemService;
import org.example.service.ConfigureSystemServiceImpl;

public class Main {

    private static ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl();


    public static void main(String[] args) {
        try{
        ParkingLot parkingLot = configureSystemService.configureSystem();
        configureSystemService.startSystem(parkingLot,false);}
        catch (Exception e){
            System.out.println("Please enter correct input type");
        }


    }

    public static void setConfigureSystemService(ConfigureSystemService configureSystemService) {
        Main.configureSystemService = configureSystemService;
    }
}