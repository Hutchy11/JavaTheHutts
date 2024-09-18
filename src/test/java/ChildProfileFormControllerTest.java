import com.example.demo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChildProfileFormControllerTest {

    private IChildDAO childDAO;
    private ICarerDAO carerDAO;

    @BeforeEach
    void setUp() {
        // Initialize mock DAOs for testing things
        carerDAO = new MockCarerDAO();
        childDAO = new MockChildDAO();
    }

    // Carer Tests - getAllCarers functionality
    @Test
    void testGetAllCarersReturnsCorrectSize() {
        List<Carer> carers = carerDAO.getAllCarers();
        // Check that the number of carers returned matches the mock data
        assertEquals(3, carers.size());
    }

    @Test
    void testGetAllCarersContainsCorrectDetails() {
        List<Carer> carers = carerDAO.getAllCarers();

        // Check first carer details
        Carer firstCarer = carers.get(0);
        assertEquals("1", firstCarer.getCarerId());
        assertEquals("Jane", firstCarer.getFirstName());
        assertEquals("Doe", firstCarer.getLastName());
        assertEquals("carer1@example.com", firstCarer.getEmail());
        assertEquals("password1", firstCarer.getPassword());
        assertEquals("123456789", firstCarer.getPhone());
        assertEquals("123 Street", firstCarer.getAddress());

        // Check second carer details
        Carer secondCarer = carers.get(1);
        assertEquals("2", secondCarer.getCarerId());
        assertEquals("John", secondCarer.getFirstName());
        assertEquals("Smith", secondCarer.getLastName());
        assertEquals("carer2@example.com", secondCarer.getEmail());
        assertEquals("password2", secondCarer.getPassword());
        assertEquals("987654321", secondCarer.getPhone());
        assertEquals("456 Avenue", secondCarer.getAddress());

        // Check third carer details
        Carer thirdCarer = carers.get(2);
        assertEquals("3", thirdCarer.getCarerId());
        assertEquals("Mary", thirdCarer.getFirstName());
        assertEquals("Johnson", thirdCarer.getLastName());
        assertEquals("carer3@example.com", thirdCarer.getEmail());
        assertEquals("password3", thirdCarer.getPassword());
        assertEquals("1122334455", thirdCarer.getPhone());
        assertEquals("789 Boulevard", thirdCarer.getAddress());
    }

    @Test
    void testAddChildWithAllRequiredFields() {
        Child child = new Child("1", "1", "Emily", "Brown", "2020-03-15", "", "", "987654321");
        childDAO.insertChild(child);

        // Retrieve the child from the mock DAO and check values
        assertEquals(1, childDAO.getAllChildren().size());
        Child insertedChild = childDAO.getAllChildren().get(0);

        assertEquals("Emily", insertedChild.getFirstName());
        assertEquals("Brown", insertedChild.getLastName());
        assertEquals("2020-03-15", insertedChild.getDateOfBirth());
        assertEquals("987654321", insertedChild.getEmergencyContact());
        assertTrue(insertedChild.getAllergies().isEmpty()); // Allergies can be empty
        assertTrue(insertedChild.getDietaryRequirements().isEmpty()); // Dietary Requirements can be empty
    }

    @Test
    void testAddChildMissingRequiredFields() {
        // Test missing First Name
        Child missingFirstName = new Child("2", "1", "", "Doe", "2020-04-01", "", "", "987654321");
        assertThrows(IllegalArgumentException.class, () -> childDAO.insertChild(missingFirstName));

        // Test missing Last Name
        Child missingLastName = new Child("3", "1", "John", "", "2020-04-01", "", "", "987654321");
        assertThrows(IllegalArgumentException.class, () -> childDAO.insertChild(missingLastName));

        // Test missing Date of Birth
        Child missingDOB = new Child("4", "1", "John", "Doe", "", "", "", "987654321");
        assertThrows(IllegalArgumentException.class, () -> childDAO.insertChild(missingDOB));

        // Test missing Emergency Contact
        Child missingEmergencyContact = new Child("5", "1", "John", "Doe", "2020-04-01", "", "", "");
        assertThrows(IllegalArgumentException.class, () -> childDAO.insertChild(missingEmergencyContact));
    }
}
