import com.example.demo.model.Carer;
import com.example.demo.model.ICarerDAO;
import com.example.demo.model.MockCarerDAO;
import com.example.demo.model.MockStaffDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildProfileFormControllerTest {

    private ICarerDAO carerDAO;

    @BeforeEach
    void setUp() {
        // Initialize mock DAOs for testing things
        carerDAO = new MockCarerDAO();
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
}
