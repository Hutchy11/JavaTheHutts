import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Carer;
import com.example.demo.model.Staff;
import com.example.demo.model.Child;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditUsersTest {

    private Carer carer;
    private Staff staff;
    private Child child;

    @BeforeEach
    public void setUp() {
        carer = new Carer("1", "Jane", "Doe", "jane.doe@example.com", "password", "1234567890", "123 Street");
        staff = new Staff("1", "Alice", "Johnson", "alice.johnson@example.com", "password", "0987654321", "Teacher", "2023-10-01");
        child = new Child("1", "2", "Tom", "Hanks", "2010-01-01", "None", "None", "1234567890");
    }

    @Test
    public void testEditCarerFirstName() {
        String newFirstName = "John";
        carer.setFirstName(newFirstName);
        assertEquals(newFirstName, carer.getFirstName());
    }

    @Test
    public void testEditCarerLastName() {
        String newLastName = "Smith";
        carer.setLastName(newLastName);
        assertEquals(newLastName, carer.getLastName());
    }

    @Test
    public void testEditStaffFirstName() {
        String newFirstName = "Bob";
        staff.setFirstName(newFirstName);
        assertEquals(newFirstName, staff.getFirstName());
    }

    @Test
    public void testEditStaffLastName() {
        String newLastName = "Williams";
        staff.setLastName(newLastName);
        assertEquals(newLastName, staff.getLastName());
    }

    @Test
    public void testEditChildFirstName() {
        String newFirstName = "Jerry";
        child.setFirstName(newFirstName);
        assertEquals(newFirstName, child.getFirstName());
    }

    @Test
    public void testEditChildLastName() {
        String newLastName = "Seinfeld";
        child.setLastName(newLastName);
        assertEquals(newLastName, child.getLastName());
    }
}