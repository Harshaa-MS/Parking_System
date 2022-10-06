package org.example.service;

import org.example.DTO.ParkingLot;
import org.example.DTO.ParkingLotTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigureSystemServiceImplTest {


    @Test
    void when_configure_system_given_all_valid_inputs() {
        String data = "2 \n 2 \n 2 \n 1 \n bike \n 1";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl();
        ParkingLot parkingLot = configureSystemService.configureSystem();
        assertEquals(2, parkingLot.getFloors().size());
    }

    @Test
    void when_start_system_given_choice_one() {
        String data = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).removeVehicle(Mockito.any());
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }
    @Test
    void when_start_system_given_choice_two() {
        String data = "2";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).removeVehicle(Mockito.any());
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }

    @Test
    void when_start_system_given_choice_three() {
        String data = "3";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).getAllVehicleDetails();
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }
    @Test
    void when_start_system_given_choice_four() {
        String data = "4";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).getAllParkedVehicleDetails();
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }@Test
    void when_start_system_given_choice_five() {
        String data = "5";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).getAllExitedVehicle();
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }
    @Test
    void when_start_system_given_default_choice() {
        String data = "6";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleServiceImpl vehicleService = Mockito.mock(VehicleServiceImpl.class);
        Mockito.doNothing().when(vehicleService).removeVehicle(Mockito.any());
        ConfigureSystemService configureSystemService = new ConfigureSystemServiceImpl(vehicleService);
        configureSystemService.startSystem(ParkingLotTest.buildParkingLot(), true);
    }
}