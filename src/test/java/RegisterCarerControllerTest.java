import com.example.demo.model.Carer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterCarerControllerTest {

    private Carer carer;

    @BeforeEach
    public void setUp() {
        carer = new Carer("123e4567-f89b-12d3-a456-426614174000", "Jane", "Doe", "jane.doe@carerexample.com", "password123", "0412345678", "123 Main St, Brisbane");
    }

    @Test
    public void testGenerateUUID() {
        Carer newStaff = new Carer(null, "Jane", "Doe", "jane.doe@carerexample.com", "password123", "0412345678", "123 Main St, Brisbane");
        assertNull(newStaff.getCarerId());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Jane", carer.getFirstName());
        assertEquals("Doe", carer.getLastName());
        assertEquals("jane.doe@carerexample.com", carer.getEmail());
        assertEquals("password123", carer.getPassword());
        assertEquals("0412345678", carer.getPhone());
        assertEquals("123 Main St, Brisbane", carer.getAddress());
    }


}