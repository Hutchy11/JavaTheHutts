import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe(null, "Pancakes", "Flour, Eggs, Milk", "Mix and cook", "Breakfast", null);
    }

    @Test
    public void testGenerateUUID() {
        Recipe newRecipe = new Recipe(null, "Waffles", "Flour, Eggs, Milk", "Mix and cook", "Breakfast", null);
        assertNotNull(newRecipe.getRecipeId());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Pancakes", recipe.getRecipeName());
        assertEquals("Flour, Eggs, Milk", recipe.getIngredients());
        assertEquals("Mix and cook", recipe.getInstructions());
        assertEquals("Breakfast", recipe.getMealType());
        assertNull(recipe.getRecipeImage());
    }


}