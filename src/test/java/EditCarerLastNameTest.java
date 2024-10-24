import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EditCarerLastNameTest extends EditUsersTestSetup {

    @Test
    public void testEditCarerLastName() {
        String newLastName = "Smith";
        carer.setLastName(newLastName);
        assertEquals(newLastName, carer.getLastName());
    }
}