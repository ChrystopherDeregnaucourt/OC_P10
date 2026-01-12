package com.openclassrooms.bobapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BobappApplicationTests
{

    @Test
    void contextLoads()
    {
        // Vérifie que le contexte Spring démarre sans erreur
        Assertions.assertTrue(true);
    }

}
