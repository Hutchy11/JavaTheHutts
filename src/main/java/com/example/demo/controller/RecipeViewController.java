package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
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

public class RecipeViewController {

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

    public void setRecipeDetails(String recipeId) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            recipeNameLabel.setText(recipe.getRecipeName());
            ingredientsTextArea.setText(recipe.getIngredients());
            instructionsTextArea.setText(recipe.getInstructions());
            if (recipe.getRecipeImage() != null) {
                javafx.scene.image.Image image = new javafx.scene.image.Image(new ByteArrayInputStream(recipe.getRecipeImage()));
                recipeImageView.setImage(image);
            }
        }
    }

    @FXML
    private void initialize() {
        rootVBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node source = (Node) event.getTarget();
                if (!rootVBox.equals(source) && !rootVBox.getChildren().contains(source)) {
                    Stage stage = (Stage) rootVBox.getScene().getWindow();
                    stage.close();
                }
            }
        });
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

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 650);
                    contentStream.showText("Ingredients:");
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText(ingredientsTextArea.getText());
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Instructions:");
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText(instructionsTextArea.getText());
                    contentStream.endText();

                    if (recipeImageView.getImage() != null) {
                        PDImageXObject pdImage = PDImageXObject.createFromFileByContent(new File(recipeImageView.getImage().getUrl()), document);
                        contentStream.drawImage(pdImage, 100, 400);
                    }
                }

                document.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCloseButtonClick() {
        Stage stage = (Stage) recipeNameLabel.getScene().getWindow();
        stage.close();
    }
}