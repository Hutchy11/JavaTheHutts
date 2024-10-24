import com.example.demo.controller.CreateMealPlanController;
import org.junit.jupiter.api.BeforeEach;

public class MealPlanTestSetup {
    protected CreateMealPlanController controller;

    @BeforeEach
    void setUp() {
        controller = new CreateMealPlanController();
    }
}