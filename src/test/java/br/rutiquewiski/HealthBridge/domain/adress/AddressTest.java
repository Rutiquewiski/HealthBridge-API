package br.rutiquewiski.HealthBridge.domain.adress;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    public void testAdressConstructorWithDTO() {
        AddressDTO addressDTO = new AddressDTO("123 Street", "Neighborhood", "12345678", "42", "Apt 101", "City", "State");
        Address address = new Address(addressDTO);

        assertEquals("123 Street", address.getStreet_address());
        assertEquals("Neighborhood", address.getNeighborhood());
        assertEquals("12345678", address.getPostal_code());
        assertEquals("42", address.getNumber());
        assertEquals("Apt 101", address.getComplement());
        assertEquals("City", address.getCity());
        assertEquals("State", address.getState());
    }

    @Test
    public void testAdressSetterAndGetters() {
        Address address = new Address();
        address.setStreet_address("123 Street");
        address.setNeighborhood("Neighborhood");
        address.setPostal_code("12345678");
        address.setNumber("42");
        address.setComplement("Apt 101");
        address.setCity("City");
        address.setState("State");

        assertEquals("123 Street", address.getStreet_address());
        assertEquals("Neighborhood", address.getNeighborhood());
        assertEquals("12345678", address.getPostal_code());
        assertEquals("42", address.getNumber());
        assertEquals("Apt 101", address.getComplement());
        assertEquals("City", address.getCity());
        assertEquals("State", address.getState());
    }

    // You can add more tests for other methods or edge cases as needed
}

