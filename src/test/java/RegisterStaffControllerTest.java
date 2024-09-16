import com.example.demo.model.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterStaffControllerTest {

    private Staff staff;

    @BeforeEach
    public void setUp() {
        staff = new Staff("123e4567-e89b-12d3-a456-426614174000", "John", "Doe", "john.doe@staffexample.com", "password123", "0412345678", "Teacher", "2024-09-07");
    }

    @Test
    public void testGenerateUUID() {
        Staff newStaff = new Staff(null, "John", "Doe", "john.doe@staffexample.com", "password123", "0412345678", "Teacher", "2024-09-07");
        assertNull(newStaff.getStaffId());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("John", staff.getFirstName());
        assertEquals("Doe", staff.getLastName());
        assertEquals("john.doe@staffexample.com", staff.getEmail());
        assertEquals("password123", staff.getPassword());
        assertEquals("0412345678", staff.getPhone());
        assertEquals("Teacher", staff.getRole());
        assertEquals("2024-09-07", staff.getHireDate());
    }


}