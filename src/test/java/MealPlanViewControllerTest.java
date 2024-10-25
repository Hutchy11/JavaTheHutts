import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.MockMealPlanDAO;

import java.util.ArrayList;


class MealPlanViewControllerTest {

    private MockMealPlanDAO mockMeal;

    private static final String DATE_TEST = "2024-10-28";
    private static final String INVALID_DATE_TEST = "2020-10-28";
    private static final int TOTAL_RECIPE_COUNT = 4;
    private static final String FIRST_RECIPE_ID = "cdf60846-05ac-47d9-94e6-5c6c098c9e2a";
    private static final String LAST_RECIPE_ID = "02f5c1d7-56cf-4d45-b120-342e2b72f36b";
    private static final String INVALID_RECIPE_ID_TEST = "invalid recipe id";
    private static final String FIRST_RECIPE_NAME = "Basic Omelette";
    private static final String LAST_RECIPE_NAME = "Chicken and salad wrap";
    private static final String INVALID_RECIPE_NAME_TEST = "invalid recipe name";

    @BeforeEach
    public void setUp() {
        mockMeal = new MockMealPlanDAO();
    }

    @Test
    public void testGetAllRecipeIdByDate() {
        ArrayList<String> allRecipeId = mockMeal.getAllRecipeIdsByDate(DATE_TEST);
        assertEquals(TOTAL_RECIPE_COUNT, allRecipeId.size());
    }

    @Test
    public void testGetFirstRecipeIdByDate() {
        ArrayList<String> allRecipeId = mockMeal.getAllRecipeIdsByDate(DATE_TEST);
        assertEquals(FIRST_RECIPE_ID, allRecipeId.get(0));
    }

    @Test
    public void testEmptyListDate() {
        ArrayList<String> allRecipeId = mockMeal.getAllRecipeIdsByDate(INVALID_DATE_TEST);
        assertEquals(0, allRecipeId.size());
    }

    @Test
    public void testGetLastRecipeIdByDate() {
        ArrayList<String> allRecipeId = mockMeal.getAllRecipeIdsByDate(DATE_TEST);
        assertEquals(LAST_RECIPE_ID, allRecipeId.get(TOTAL_RECIPE_COUNT - 1));
    }

    @Test
    public void testGetFirstRecipeNameById() {
        String RecipeName = mockMeal.getRecipeNameById(FIRST_RECIPE_ID);
        assertEquals(FIRST_RECIPE_NAME, RecipeName);
    }

    @Test
    public void testGetLastRecipeNameById() {
        String RecipeName = mockMeal.getRecipeNameById(LAST_RECIPE_ID);
        assertEquals(LAST_RECIPE_NAME, RecipeName);
    }

    @Test
    public void testInvalidRecipeNameById() {
        String RecipeName = mockMeal.getRecipeNameById(INVALID_RECIPE_ID_TEST);
        assertEquals("", RecipeName);
    }

    @Test
    public void testInvalidRecipeIdByName() {
        String RecipeName = mockMeal.getRecipeNameById(INVALID_RECIPE_NAME_TEST);
        assertEquals("", RecipeName);
    }

}
