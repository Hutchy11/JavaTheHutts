import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MealPlanTestInvalidDate extends MealPlanTestSetup {

    @Test
    void testCreateMealPlanWithInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            controller.createMealPlan("Pancakes", LocalDate.of(2025, 10, 1));
        });
    }
}