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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView homeButton;
    @FXML
    private Label homeButtonL;
    @FXML
    private ImageView myNetworkButton;
    @FXML
    private Label myNetworkButtonL;
    @FXML
    private ImageView newPostButton;
    @FXML
    private Label newPostButtonL;
    @FXML
    private ImageView jobsButton;
    @FXML
    private Label jobsButtonL;
    @FXML
    private ImageView messageButton;
    @FXML
    private Label messageButtonL;
    @FXML
    private ImageView titleImageImageView;
    @FXML
    private ImageView profileImageImageview;
    @FXML
    private ImageView editProfileButton;
    @FXML
    private Label editProfileButtonL;
    @FXML
    private AnchorPane educationSeaMore;
    @FXML
    private AnchorPane connectWithMeSeaMore;
    @FXML
    private AnchorPane myJobsSeaMore;
    @FXML
    private ImageView settingButton;
    @FXML
    private Label settingButtonL;
    @FXML
    private ImageView signOutButton;
    @FXML
    private Label signOutButtonL;


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
    public void connectWithMeSeaPressed(ActionEvent event) {
        //got to connect with me sea more
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

}
