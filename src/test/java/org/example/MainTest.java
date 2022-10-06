package org.example;

import org.example.DTO.ParkingLotTest;
import org.example.service.ConfigureSystemService;
import org.example.service.ConfigureSystemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MainTest {

    @Test
    void when_main_given_valid_input() {
        ConfigureSystemService configureSystemService = Mockito.mock(ConfigureSystemServiceImpl.class);
        Mockito.when(configureSystemService.configureSystem()).thenReturn(ParkingLotTest.buildParkingLot());
        Main.setConfigureSystemService(configureSystemService);
        Main.main(new String[0]);
    }

    @Test
    void when_main_given_exception_failure() {
        ConfigureSystemService configureSystemService = Mockito.mock(ConfigureSystemServiceImpl.class);
        Mockito.when(configureSystemService.configureSystem()).thenThrow(Exception.class);
        Main.setConfigureSystemService(configureSystemService);
        Main.main(new String[0]);
    }
}