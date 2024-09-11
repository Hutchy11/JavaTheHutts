import com.example.demo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginControllerTest {

    // Test the logic in the login controller using the MockDAOs
    private ICarerDAO carerDAO;
    private IStaffDAO staffDAO;

    @BeforeEach
    void setUp() {
        // Initialize mock DAOs for testing things
        carerDAO = new MockCarerDAO();
        staffDAO = new MockStaffDAO();
    }

    // Carer Tests
    @Test
    void testLoginWithValidCarer() {
        Carer carer = carerDAO.login("carer@example.com", "carerpassword");
        assertNotNull(carer);
        assertEquals("Jane", carer.getFirstName());
        assertEquals("Doe", carer.getLastName());
        assertEquals("carer@example.com", carer.getEmail());
    }

    @Test
    void testLoginWithInvalidCarerCredentials() {
        Carer carer = carerDAO.login("invalid@example.com", "wrongpassword");
        assertNull(carer);
    }

    @Test
    void testLoginWithNoCarerCredentials() {
        Carer carer = carerDAO.login("", "");
        assertNull(carer);
    }

    // Staff Tests
    @Test
    void testLoginWithValidStaff() {
        Staff staff = staffDAO.login("staff@example.com", "staffpassword");
        assertNotNull(staff);
        assertEquals("John", staff.getFirstName());
        assertEquals("Doe", staff.getLastName());
        assertEquals("staff@example.com", staff.getEmail());
    }

    @Test
    void testLoginWithInvalidStaffCredentials() {
        Staff staff = staffDAO.login("invalid@example.com", "wrongpassword");
        assertNull(staff);
    }

    @Test
    void testLoginWithNoStaffCredentials() {
        Staff staff = staffDAO.login("", "");
        assertNull(staff);
    }
}
