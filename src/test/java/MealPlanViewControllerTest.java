import com.example.demo.controller.MealPlanViewController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled ("Test class is disabled for now")
public class MealPlanViewControllerTest {

    private MealPlanViewController controller;

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Initialize the JavaFX toolkit
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> latch.countDown());
        latch.await();

        // Initialize the controller
        controller = new MealPlanViewController();

        // Initialize Label fields in the JavaFX thread
        CountDownLatch labelLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            controller.mondayBreakfastLabel = new Label();
            controller.tuesdayBreakfastLabel = new Label();
            controller.wednesdayBreakfastLabel = new Label();
            controller.thursdayBreakfastLabel = new Label();
            controller.fridayBreakfastLabel = new Label();
            labelLatch.countDown();  // Signal that labels are initialized
        });

        // Wait for labels to be initialized
        labelLatch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSetMealPlanDetails() throws InterruptedException {
        String date = "2023-10-01";

        // Run the method that updates the labels in the JavaFX thread
        CountDownLatch methodLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Call the method to set meal plan details
            controller.setMealPlanDetails(date);
            methodLatch.countDown();  // Signal that the method has completed
        });

        // Wait for the method to complete
        methodLatch.await(5, TimeUnit.SECONDS);

        // Perform assertions after JavaFX updates are completed
        assertEquals("Recipe1", controller.mondayBreakfastLabel.getText());
        assertEquals("Recipe2", controller.tuesdayBreakfastLabel.getText());
        assertEquals("Recipe3", controller.wednesdayBreakfastLabel.getText());
        assertEquals("Recipe4", controller.thursdayBreakfastLabel.getText());
        assertEquals("Recipe5", controller.fridayBreakfastLabel.getText());
    }
}