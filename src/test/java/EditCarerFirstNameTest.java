import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EditCarerFirstNameTest extends EditUsersTestSetup {

    @Test
    public void testEditCarerFirstName() {
        String newFirstName = "John";
        carer.setFirstName(newFirstName);
        assertEquals(newFirstName, carer.getFirstName());
    }
}