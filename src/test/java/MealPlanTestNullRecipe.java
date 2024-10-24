import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MealPlanTestNullRecipe extends MealPlanTestSetup {

    @Test
    void testCreateMealPlanWithNullRecipe() {
        assertThrows(NullPointerException.class, () -> {
            controller.createMealPlan(null, LocalDate.of(2023, 10, 1));
        });
    }
}