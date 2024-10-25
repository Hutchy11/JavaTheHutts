import com.example.demo.model.MockRecipeDAO;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RecipeControllerTest {

    private MockRecipeDAO mockRecipe;

    private static final String RECIPE_ID = "cd3ce395-bf3a-414a-a545-c6f07c50999e";
    private static final String RECIPE_NAME = "Banana Muffin";
    private static final String INGREDIENTS = "Bananas";
    private static final String INSTRUCTIONS = "Peel bananas";
    private static final String MEAL_TYPE = "Snack";

    @BeforeEach
    public void setUp() {
        mockRecipe = new MockRecipeDAO();
    }

    @Test
    public void testGetRecipeIdByName() {
        ArrayList<String> allRecipeId = mockRecipe.getRecipeIdByName(RECIPE_NAME);
        assertEquals(RECIPE_ID, allRecipeId.get(0));
    }

    @Test
    public void testGetIngredientsByName() {
        ArrayList<String> allRecipeIngredients = mockRecipe.getIngredientsByName(RECIPE_NAME);
        assertEquals(INGREDIENTS, allRecipeIngredients.get(0));
    }

    @Test
    public void testGetInstructionsByName() {
        ArrayList<String> allRecipeInstructions = mockRecipe.getInstructionsByName(RECIPE_NAME);
        assertEquals(INSTRUCTIONS, allRecipeInstructions.get(0));
    }

    @Test
    public void testGetMealTypeByName() {
        ArrayList<String> allRecipeMealTypes = mockRecipe.getMealTypeByName(RECIPE_NAME);
        assertEquals(MEAL_TYPE, allRecipeMealTypes.get(0));
    }


}