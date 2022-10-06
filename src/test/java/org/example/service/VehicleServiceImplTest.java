package org.example.service;

import org.example.DAO.VehicleDAO;
import org.example.DTO.ParkingLotTest;
import org.example.DTO.Vehicle;
import org.example.DTO.VehicleTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleServiceImplTest {

    @Test
    void when_add_vehicle_given_wrong_vehicle_type_failure() {
        String data = "4";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }
    @Test
    void when_add_vehicle_given_no_slot_failure() {
        String data = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.checkForSlotAvailability(Mockito.anyString(),Mockito.anyInt())).thenReturn(false);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_wrong_floor_details_failure() {
        String data = "1 \n KA123 \n 4";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_wrong_entry_details_failure() {
        String data = "1 \n KA123 \n 1 \n 4";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_paid_at_customer_info_with_cash() {
        String data = "1 \n KA123 \n 1 \n 1 \n 1 \n 1";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_paid_at_customer_info_with_card() {
        String data = "1 \n KA123 \n 1 \n 1 \n 1 \n 2";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_paid_at_customer_info_with_wrong_input_failure() {
        String data = "1 \n KA123 \n 1 \n 1 \n 1 \n 3";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_not_paid_at_customer_info() {
        String data = "1 \n KA123 \n 1 \n 1 \n 2";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_wrong_details_at_customer_info() {
        String data = "1 \n KA123 \n 1 \n 1 \n 3";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_add_vehicle_given_all_valid_input() {
        String data = "1 \n KA123 \n 1 \n 1 \n 2";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.checkForSlotAvailability(Mockito.anyString(),Mockito.anyInt())).thenReturn(true);
        Mockito.when(vehicleDAO.addVehicle(Mockito.any())).thenReturn(false);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        vehicleService.addVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_wrong_vehicle_number_failure() {
        String data = "1 ";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        VehicleService vehicleService = new VehicleServiceImpl(amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_valid_input() {
        String data = "ka123 \n 1 \n ";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        Mockito.when(vehicleDAO.isVehiclePresent(Mockito.anyString())).thenReturn(true);
        Mockito.when(vehicleDAO.getVehicle(Mockito.anyString())).thenReturn(VehicleTest.buildVehicleDTOWithPaidStatusTrue());
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO,amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_wrong_exit_gate_details_failure() {
        String data = "ka123 \n 4 \n ";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        Mockito.when(vehicleDAO.isVehiclePresent(Mockito.anyString())).thenReturn(true);
        Mockito.when(vehicleDAO.getVehicle(Mockito.anyString())).thenReturn(VehicleTest.buildVehicleDTO());
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO,amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_payment_at_exit_gate_with_cash() {
        String data = "ka123 \n 1\n  1";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        Mockito.when(vehicleDAO.isVehiclePresent(Mockito.anyString())).thenReturn(true);
        Mockito.when(vehicleDAO.getVehicle(Mockito.anyString())).thenReturn(VehicleTest.buildVehicleDTO());
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO,amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_payment_at_exit_gate_with_card() {
        String data = "ka123 \n 1\n  2";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        Mockito.when(vehicleDAO.isVehiclePresent(Mockito.anyString())).thenReturn(true);
        Mockito.when(vehicleDAO.getVehicle(Mockito.anyString())).thenReturn(VehicleTest.buildVehicleDTO());
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO,amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_remove_vehicle_given_payment_at_exit_gate_failure() {
        String data = "ka123 \n 1\n  3";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        AmountService amountService = Mockito.mock(AmountServiceImpl.class);
        Mockito.when(amountService.calculateTotalAmount(Mockito.any())).thenReturn(1000);
        Mockito.when(vehicleDAO.isVehiclePresent(Mockito.anyString())).thenReturn(true);
        Mockito.when(vehicleDAO.getVehicle(Mockito.anyString())).thenReturn(VehicleTest.buildVehicleDTO());
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO,amountService);
        vehicleService.removeVehicle(ParkingLotTest.buildParkingLot());
    }

    @Test
    void when_get_all_vehicle_details_given_valid_input() {
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.getAllVehicle()).thenReturn(new ArrayList<>(Collections.singletonList(new Vehicle())));
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        vehicleService.getAllVehicleDetails();
    }

    @Test
    void when_get_all_parked_vehicle_details_given_valid_input() {
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.getAllParkedVehicle()).thenReturn(new ArrayList<>(Collections.singletonList(new Vehicle())));
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        vehicleService.getAllParkedVehicleDetails();
    }

    @Test
    void when_get_all_exited_vehicle_given_valid_input() {
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.getAllExitedVehicle()).thenReturn(new ArrayList<>(Collections.singletonList(new Vehicle())));
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        vehicleService.getAllExitedVehicle();
    }

    @Test
    void when_available_slot_given_valid_input() {
        VehicleDAO vehicleDAO = Mockito.mock(VehicleDAO.class);
        Mockito.when(vehicleDAO.availableSlot(Mockito.anyString(),Mockito.anyInt())).thenReturn(5L);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleDAO);
        long actualResult = vehicleService.availableSlot(Mockito.anyString(),Mockito.anyInt());
        assertEquals(5,actualResult);
    }
}