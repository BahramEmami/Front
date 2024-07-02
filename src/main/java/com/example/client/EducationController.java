package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class EducationController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView backToProfileButton;
    @FXML
    private TextField instituteText;
    @FXML
    private TextField fieldOfStudyText;
    @FXML
    private TextField gradeText;
    @FXML
    private TextField activitiesDoneText;
    @FXML
    private TextField startDateText;
    @FXML
    private TextField finishDateText;
    @FXML
    private TextArea descriptionText;

    private static String institute;
    private static String fieldOfStudy;
    private static String grade;
    private static String activitiesDone;
    private static String startDate;
    private static String finishDate;
    private static String description;


    @FXML
    public void backToProfilePressed(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
