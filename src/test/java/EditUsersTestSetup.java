import com.example.demo.model.Carer;
import com.example.demo.model.Staff;
import com.example.demo.model.Child;
import org.junit.jupiter.api.BeforeEach;

public class EditUsersTestSetup {

    protected Carer carer;
    protected Staff staff;
    protected Child child;

    @BeforeEach
    public void setUp() {
        carer = new Carer("1", "Jane", "Doe", "jane.doe@example.com", "password", "1234567890", "123 Street");
        staff = new Staff("1", "Alice", "Johnson", "alice.johnson@example.com", "password", "0987654321", "Teacher", "2023-10-01");
        child = new Child("1", "2", "Tom", "Hanks", "2010-01-01", "None", "None", "1234567890");
    }
}