package com.example.client;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button homeButton;

    @FXML
    private Button myNetworkButton;

    @FXML
    private Button newPostButton;

    @FXML
    private Button jobsButton;

    @FXML
    private Button messageButton;

    @FXML
    private ImageView titleImageImageView;
    @FXML
    private ImageView profileImageImageview;
    @FXML
    private Button editProfileButton;

    @FXML
    private Button educationSeaMore;
    @FXML
    private Button connectWithMeSeaMore;
    @FXML
    private Button myJobsSeaMore;
    @FXML
    private Button settingButton;

    @FXML
    private Button signOutButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button editProfileImageButton;

    @FXML
    private Button editTitleImageButton;

    @FXML
    private Label statusLabel;

    private static Image profileImage = null;
    private static Image titleImage = null;

    private static String userName;


    @FXML
    public void homePressed(ActionEvent  event){
        // go to home scene
    }

    @FXML
    public void myNetWorkPressed(ActionEvent event) {
        // go to MyNetwork scene
    }

    @FXML
    public void newPostPressed(ActionEvent event) {
        // go to newPost scene
    }

    @FXML
    public void jobsPressed(ActionEvent event) {
        // go to jobs scene
    }

    @FXML
    public void messagePressed(ActionEvent event) {
        // go to message scene
    }

    @FXML
    public void editProfilePressed(ActionEvent event){
    //got to edit profile
    }

    @FXML
    public void educationSeaMorePressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void connectWithMeSeaPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ContactViewFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void myJobsSeaPressed(ActionEvent event) {
        //got to myJobsSeaPressed sea more
    }

    @FXML
    public void signOutPressed(ActionEvent event){
        //sign out
    }
    @FXML
    public void settingPressed(ActionEvent event){
        //go to setting scene
    }

    @FXML
    public void editProfileImagePressed(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            profileImage = new Image(new FileInputStream(file));
            profileImageImageview.setImage(profileImage);
        } catch (FileNotFoundException fe) {
            statusLabel.setText("File not found!");
        }
    }

    @FXML
    public void editTitleImagePressed(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            titleImage = new Image(new FileInputStream(file));
            titleImageImageView.setImage(titleImage);
        } catch (FileNotFoundException fe) {
            statusLabel.setText("File not found!");
        }
    }

}
