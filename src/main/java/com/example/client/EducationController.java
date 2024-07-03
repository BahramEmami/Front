package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML
    private ImageView addEducationButton;
    @FXML
    private ImageView editEducationButton;

    private static String institute = "";
    private static String fieldOfStudy = "";
    private static String grade = "";
    private static String activitiesDone = "";
    private static String startDate = "";
    private static String finishDate = "";
    private static String description = "";

    @FXML
    private ImageView backToEducationButton;
    @FXML
    private TextField instituteTextEdit;
    @FXML
    private TextField fieldOfStudyTextEdit;
    @FXML
    private TextField gradeTextEdit;
    @FXML
    private TextField activitiesDoneTextEdit;
    @FXML
    private DatePicker startDateDatePicker;
    @FXML
    private DatePicker finishDateDatePicker;
    @FXML
    private TextArea descriptionTextEdit;
    @FXML
    private ImageView doneEditEducationButton;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView logoEdit;


    @FXML
    private ImageView backToEducationButtonAdd;
    @FXML
    private TextField instituteTextAdd;
    @FXML
    private TextField fieldOfStudyTextAdd;
    @FXML
    private TextField gradeTextAdd;
    @FXML
    private TextField activitiesDoneTextAdd;
    @FXML
    private DatePicker startDateDatePickerAdd;
    @FXML
    private DatePicker finishDateDatePickerAdd;
    @FXML
    private TextArea descriptionTextAdd;
    @FXML
    private ImageView doneAddEducation;
    @FXML
    private ImageView logoEducationAdd;

    private static String instituteAdd= "";
    private static String fieldOfStudyAdd = "";
    private static String gradeAdd = "";
    private static String activitiesDoneAdd = "";
    private static String startDateAdd = "";
    private static String finishDateAdd = "";
    private static String descriptionAdd = "";




    @FXML
    public void logoPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void backToProfilePressed(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationEdit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        instituteTextEdit.setText(institute);
        fieldOfStudyTextEdit.setText(fieldOfStudy);
        gradeTextEdit.setText(grade);
        activitiesDoneTextEdit.setText(activitiesDone);
        descriptionTextEdit.setText(description);



    }

    @FXML
    public void addPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationAddFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void doneEditPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        institute= instituteTextEdit.getText();
        instituteText.setText(institute);

        fieldOfStudy=fieldOfStudyTextEdit.getText();
        fieldOfStudyText.setText(fieldOfStudy);

        grade = gradeTextEdit.getText();
        gradeText.setText(grade);

        activitiesDone = activitiesDoneTextEdit.getText();
        activitiesDoneText.setText(activitiesDone);

        try{
            startDate = startDateDatePicker.getValue().toString();
        }
        catch (Exception e){
            startDate = "";
        }
        startDateText.setText(startDate);
        try {
            finishDate = finishDateDatePicker.getValue().toString();
        }
        catch (Exception e){
            finishDate = "";
        }
        finishDateText.setText(finishDate);

        description = descriptionTextEdit.getText();
        descriptionText.setText(description);
    }

    @FXML
    public void backToEducationPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void doneAddPressed(ActionEvent event){
        instituteAdd = instituteTextAdd.getText();
        fieldOfStudyAdd=fieldOfStudyTextAdd.getText();
        gradeAdd = gradeTextAdd.getText();
        activitiesDoneAdd = activitiesDoneTextAdd.getText();
        try {
            startDateAdd = startDateDatePickerAdd.getValue().toString();
        }
        catch (Exception e){
            startDateAdd = "";
        }
        try {
            finishDateAdd = finishDateDatePickerAdd.getValue().toString();
        } catch (Exception e) {
            finishDateAdd = "";
        }
        descriptionAdd = descriptionTextAdd.getText();

        /////////then go to add scene
    }
}
