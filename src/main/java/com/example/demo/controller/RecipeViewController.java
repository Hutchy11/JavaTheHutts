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

    public void setRecipeDetails(String recipeName) {
        Recipe recipe = recipeDAO.getRecipeByName(recipeName);
        if (recipe != null) {
            recipeNameLabel.setText(recipe.getRecipeName());
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
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText("Recipe Name: " + recipeNameLabel.getText());
                    contentStream.endText();

                    Recipe recipe = recipeDAO.getRecipeByName(recipeNameLabel.getText());
                    if (recipe != null && recipe.getRecipeImage() != null) {
                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, recipe.getRecipeImage(), "recipeImage");
                        contentStream.drawImage(pdImage, 100, 500, 200, 150);
                    }

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 450);
                    contentStream.showText("Ingredients:");
                    contentStream.endText();

                    String[] ingredientsLines = ingredientsTextArea.getText().split("\n");
                    float yOffset = 435;
                    for (String line : ingredientsLines) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(100, yOffset);
                        contentStream.showText(line.replaceAll("[^\\x00-\\x7F]", "")); // Remove unsupported characters
                        contentStream.endText();
                        yOffset -= 15;
                    }

                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yOffset - 15);
                    contentStream.showText("Instructions:");
                    contentStream.endText();

                    yOffset -= 30;
                    String[] instructionsLines = instructionsTextArea.getText().split("\n");
                    for (String line : instructionsLines) {
                        yOffset = addWrappedText(contentStream, line, 100, yOffset, 12, 500);
                    }
                }

                document.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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