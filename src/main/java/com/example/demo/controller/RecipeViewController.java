package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.layout.VBox;

import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Recipe View.
 * Handles the display and interaction with recipes.
 */
public class RecipeViewController {

    @FXML
    private ChoiceBox<String> recipeChoiceBox;

    @FXML
    private Label recipeNameLabel;

    @FXML
    private ImageView recipeImageView;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextArea instructionsTextArea;

    @FXML
    private VBox rootVBox;

    private RecipeDAO recipeDAO = new RecipeDAO();

    /**
     * Initializes the controller class.
     * Populates the recipe choice box with available recipe names and sets up listeners.
     */
    @FXML
    public void initialize() {
        List<String> recipeNames = recipeDAO.getAllRecipeNames();
        recipeChoiceBox.getItems().addAll(recipeNames);
        recipeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setRecipeDetails(newValue);
            }
        });
    }

    /**
     * Sets the recipe details for the selected recipe name.
     *
     * @param recipeName the selected recipe name
     */
    public void setRecipeDetails(String recipeName) {
        Recipe recipe = recipeDAO.getRecipeByName(recipeName);
        if (recipe != null) {
            //recipeNameLabel.setText(recipe.getRecipeName());
            ingredientsTextArea.setText(recipe.getIngredients());
            instructionsTextArea.setText(recipe.getInstructions());
            if (recipe.getRecipeImage() != null) {
                Image image = new Image(new ByteArrayInputStream(recipe.getRecipeImage()));
                recipeImageView.setImage(image);
            } else {
                recipeImageView.setImage(null);
            }
        }
    }

    /**
     * Handles the download button click event.
     * Generates a PDF file with the recipe details and allows the user to save it.
     */
    @FXML
    private void onDownloadButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(recipeNameLabel.getScene().getWindow());

        if (file != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    float pageWidth = page.getMediaBox().getWidth();
                    float margin = 50;
                    float contentWidth = pageWidth - 2 * margin;
                    float startX = margin;

                    // Adjust the y-coordinate to reduce the top margin
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(startX, 730); // Adjusted from 700 to 710
                    contentStream.showText(recipeChoiceBox.getSelectionModel().getSelectedItem());
                    contentStream.endText();

                    String selectedRecipeName = recipeChoiceBox.getSelectionModel().getSelectedItem();
                    Recipe recipe = recipeDAO.getRecipeByName(selectedRecipeName);
                    if (recipe != null && recipe.getRecipeImage() != null) {
                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, recipe.getRecipeImage(), "recipeImage");
                        contentStream.drawImage(pdImage, startX, 560, 200, 150);
                    }

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(startX, 530); // Adjusted from 450 to 530
                    contentStream.showText("Ingredients:");
                    contentStream.endText();

                    String[] ingredientsLines = ingredientsTextArea.getText().split("\n");
                    float yOffset = 515; // Adjusted from 435 to 515
                    for (String line : ingredientsLines) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(startX, yOffset);
                        contentStream.showText(line.replaceAll("[^\\x00-\\x7F]", "")); // Remove unsupported characters
                        contentStream.endText();
                        yOffset -= 15;
                    }

                    contentStream.beginText();
                    contentStream.newLineAtOffset(startX, yOffset - 15);
                    contentStream.showText("Instructions:");
                    contentStream.endText();

                    yOffset -= 30;
                    String[] instructionsLines = instructionsTextArea.getText().split("\n");
                    for (String line : instructionsLines) {
                        yOffset = addWrappedText(contentStream, line, startX, yOffset, 12, contentWidth);
                    }
                }

                document.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds wrapped text to the PDF content stream.
     *
     * @param contentStream the content stream to write to
     * @param text the text to wrap and add
     * @param x the x-coordinate to start the text
     * @param y the y-coordinate to start the text
     * @param fontSize the font size of the text
     * @param maxWidth the maximum width of the text before wrapping
     * @return the y-coordinate after adding the text
     * @throws IOException if an I/O error occurs
     */
    private float addWrappedText(PDPageContentStream contentStream, String text, float x, float y, int fontSize, float maxWidth) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            float lineWidth = PDType1Font.HELVETICA.getStringWidth(line + word) / 1000 * fontSize;
            if (lineWidth > maxWidth) {
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText(line.toString());
                contentStream.endText();
                line = new StringBuilder(word + " ");
                y -= fontSize + 2;
            } else {
                line.append(word).append(" ");
            }
        }
        if (line.length() > 0) {
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(line.toString());
            contentStream.endText();
            y -= fontSize + 2;
        }
        return y;
    }
}