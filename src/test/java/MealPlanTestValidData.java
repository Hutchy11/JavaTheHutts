import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.MealPlan;

import java.time.LocalDate;

public class MealPlanTestValidData extends MealPlanTestSetup {

    @Test
    void testCreateMealPlanWithValidData() {
        MealPlan mealPlan = controller.createMealPlan("Pancakes", LocalDate.of(2023, 10, 1));
        assertNotNull(mealPlan);
        assertNotNull(mealPlan.getMealPlanId());
        assertEquals("", mealPlan.getStaffId());
        assertEquals("2023-10-01", mealPlan.getDate());
        assertEquals("1", mealPlan.getRecipeId("monday", "breakfast"));
    }
}