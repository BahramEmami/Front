package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PostingController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button backToProfileButtonPost;
    @FXML
    private Button doneButton;
    @FXML
    private Button logoPostButton;
    @FXML
    private Button selectPostImageButton;
    @FXML
    private TextArea textPostTextArea;
    @FXML
    private ImageView postImageImageView;
    @FXML
    private Label statusLabel;
    @FXML
    private Button imageDeleteButton;

    private static Image postImage;
    private static String postText = "";

    @FXML
    public void backToProfilePressed(ActionEvent event) {
        /*Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        System.out.println("back to profile");
    }

    @FXML
    public void logoPressed(ActionEvent event) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        System.out.println("logo");
    }

    @FXML
    public void donePressed(ActionEvent event) throws IOException {
        boolean isPostImage = false;
        try {
            postImage.equals(null);
            isPostImage = true;
        } catch (Exception e) {
            isPostImage = false;
        }
        if (!isPostImage && textPostTextArea.getText().length() == 0) {
            statusLabel.setText("fill at least one field");
        } else {
            statusLabel.setText("");
            if (textPostTextArea.getText().length() != 0) {
                postText = textPostTextArea.getText();
                System.out.println(postText);
            } else {
                postText = "";
            }
            /*Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
*/
        }
    }

    @FXML
    public void postImageSelectPressed(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            postImage = new Image(new FileInputStream(file));
            postImageImageView.setImage(postImage);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        } catch (Exception e) {
        }
    }

    @FXML
    public void imageDeletePressed(ActionEvent event) {
        postImageImageView.setImage(null);
        postImage = null;
    }
}
