package br.rutiquewiski.HealthBridge.domain.adress;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdressTest {

    @Test
    public void testAdressConstructorWithDTO() {
        AdressDTO adressDTO = new AdressDTO("123 Street", "Neighborhood", "12345678", "42", "Apt 101", "City", "State");
        Adress adress = new Adress(adressDTO);

        assertEquals("123 Street", adress.getStreet_address());
        assertEquals("Neighborhood", adress.getNeighborhood());
        assertEquals("12345678", adress.getPostal_code());
        assertEquals("42", adress.getNumber());
        assertEquals("Apt 101", adress.getComplement());
        assertEquals("City", adress.getCity());
        assertEquals("State", adress.getState());
    }

    @Test
    public void testAdressSetterAndGetters() {
        Adress adress = new Adress();
        adress.setStreet_address("123 Street");
        adress.setNeighborhood("Neighborhood");
        adress.setPostal_code("12345678");
        adress.setNumber("42");
        adress.setComplement("Apt 101");
        adress.setCity("City");
        adress.setState("State");

        assertEquals("123 Street", adress.getStreet_address());
        assertEquals("Neighborhood", adress.getNeighborhood());
        assertEquals("12345678", adress.getPostal_code());
        assertEquals("42", adress.getNumber());
        assertEquals("Apt 101", adress.getComplement());
        assertEquals("City", adress.getCity());
        assertEquals("State", adress.getState());
    }

    // You can add more tests for other methods or edge cases as needed
}

