package org.example.service;

import org.example.DTO.VehicleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountServiceImplTest {

    @Test
    void when_calculate_total_amount_given_in_and_out_time() {
        AmountService amountService = new AmountServiceImpl();
        int  actualAmount = amountService.calculateTotalAmount(VehicleTest.buildVehicleDTOWithInAndOutTime());
        assertEquals(240, actualAmount);
    }
}