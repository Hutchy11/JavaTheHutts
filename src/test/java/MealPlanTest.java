import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.controller.CreateMealPlanController;
import com.example.demo.model.MealPlan;

import java.time.LocalDate;

class MealPlanTest {
    CreateMealPlanController controller;

    @BeforeEach
    void setUp() {
        controller = new CreateMealPlanController();
    }

    @Test
    void testCreateMealPlanWithValidData() {
        MealPlan mealPlan = controller.createMealPlan("Pancakes", LocalDate.of(2023, 10, 1));
        assertNotNull(mealPlan);
        assertNotNull(mealPlan.getMealPlanId());
        assertEquals("", mealPlan.getStaffId());
        assertEquals("2023-10-01", mealPlan.getDate());
        assertEquals("1", mealPlan.getRecipeId("monday", "breakfast"));
    }

    @Test
    void testCreateMealPlanWithInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            controller.createMealPlan("Pancakes", LocalDate.of(2025, 10, 1));
        });
    }

    @Test
    void testCreateMealPlanWithNullRecipe() {
        assertThrows(NullPointerException.class, () -> {
            controller.createMealPlan(null, LocalDate.of(2023, 10, 1));
        });
    }
}