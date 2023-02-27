package com.capitole.service.backend.inventory.manager.controller;

import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @InjectMocks
    private PriceController controller;

    @Mock
    private PriceValidateCommand command    ;

}
