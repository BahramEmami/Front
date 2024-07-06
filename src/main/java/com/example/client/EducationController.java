package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;

public class EducationController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button backToProfileButton;
    @FXML
     TextField instituteText;
    @FXML
     TextField fieldOfStudyText;
    @FXML
     TextField gradeText;
    @FXML
     TextField activitiesDoneText;
    @FXML
     TextField startDateText;
    @FXML
     TextField finishDateText;
    @FXML
     TextArea descriptionText;
    @FXML
     Button addEducationButton;
    @FXML
     Button editEducationButton;

    private static String institute = "";
    private static String fieldOfStudy = "";
    private static String grade = "";
    private static String activitiesDone = "";
    private static String startDate = "";
    private static String finishDate = "";
    private static String description = "";
    static LocalDate startDateLocal;
    static LocalDate finishDateLocal;
    private static String startDateString = "";
    private static String finishDateString = "";


    @FXML
    private Button backToEducationButton;
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
    private Button doneEditEducationButton;
    @FXML
    private Button logo;
    @FXML
    private Button logoEdit;


    @FXML
    private Button backToEducationButtonAdd;
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
    private Button doneAddEducation;
    @FXML
    private Button logoEducationAdd;

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
        institute = instituteText.getText();
        fieldOfStudy = fieldOfStudyText.getText();
        grade = gradeText.getText();
        activitiesDone = activitiesDoneText.getText();
        startDate = startDateText.getText();
        finishDate = finishDateText.getText();
        description = descriptionText.getText();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("EducationEdit.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        EducationController controller = loader.getController();

        // Ensure fields are not null
        if (controller.instituteTextEdit == null ||
                controller.fieldOfStudyTextEdit == null ||
                controller.gradeTextEdit == null ||
                controller.activitiesDoneTextEdit == null ||
                controller.startDateDatePicker == null ||
                controller.finishDateDatePicker == null ||
                controller.descriptionTextEdit == null) {
            System.out.println("One or more fields are not initialized!");
            return;
        }

        // Initialize the stage and scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        // Set the fields with existing data
        controller.instituteTextEdit.setText(institute);
        controller.fieldOfStudyTextEdit.setText(fieldOfStudy);
        controller.gradeTextEdit.setText(grade);
        controller.activitiesDoneTextEdit.setText(activitiesDone);
        controller.descriptionTextEdit.setText(description);


    }

    @FXML
    public void addPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EducationAddFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public int editEducation() throws IOException {

        //"http://localhost:8080"/education/edit

        JSONObject json = new JSONObject();

        json.put("id", Client.user.getID());
        json.put("institute", institute);
        json.put("field_study", fieldOfStudy);
        json.put("education_description", description);
        json.put("activity_description", activitiesDone);
        json.put("grade", grade);
        if (startDateDatePicker.getValue() == null) {
            json.put("start_date", Date.valueOf(LocalDate.of(1, 1 , 1)));
        }if (finishDateDatePicker.getValue() == null) {
            json.put("finish_date", Date.valueOf(LocalDate.of(1, 1 , 1)));
        } if (startDateDatePicker.getValue() != null) {
            json.put("start_date", Date.valueOf(startDateLocal));
        }
        if (finishDateDatePicker.getValue() != null) {
            json.put("finish_date", Date.valueOf(finishDateLocal));
        }


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "education/" + "edit");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setRequestProperty("LKN", Client.user.getToken());
        tempConnection.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection, json.toString());

        if (tempConnection.getResponseCode() == 200) {      //go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else if (tempConnection.getResponseCode() == 401) {
            return -9;
        } else if (tempConnection.getResponseCode() == 404) {
            return -1;
        } else// nothing
            return 10;
    }


    @FXML
    public void doneEditPressed(ActionEvent event) throws IOException {



        ////////////////////       set sql save




        institute = instituteTextEdit.getText();
        fieldOfStudy = fieldOfStudyTextEdit.getText();
        grade = gradeTextEdit.getText();
        activitiesDone = activitiesDoneTextEdit.getText();
        description = descriptionTextEdit.getText();
        try {
            startDate = startDateDatePicker.getValue().toString();
        }
        catch (Exception e){
            startDate = "";
        }
        try {
            finishDate = finishDateDatePicker.getValue().toString();
        } catch (Exception e) {
            finishDate = "";
        }
        startDateLocal = startDateDatePicker.getValue();
        finishDateLocal = startDateDatePicker.getValue();
        try{
            startDateString = startDateDatePicker.getValue().toString();
        }
        catch (Exception e){
            startDateString = "";
        }
        try {
            finishDateString = startDateDatePicker.getValue().toString();
        } catch (Exception e) {
            finishDateString = "";
        }


        if (editEducation() == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EducationFXML.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the FXML file
            EducationController controller = loader.getController();

            // Ensure fields are not null
            if (controller.instituteText == null ||
                    controller.fieldOfStudyText == null ||
                    controller.gradeText == null ||
                    controller.activitiesDoneText == null ||
                    controller.startDateText == null ||
                    controller.finishDateText == null ||
                    controller.descriptionText == null) {
                System.out.println("One or more fields are not initialized!");
                return;
            }

            // Initialize the stage and scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            // Set the fields with existing data
            controller.instituteText.setText(institute);
            controller.fieldOfStudyText.setText(fieldOfStudy);
            controller.gradeText.setText(grade);
            controller.activitiesDoneText.setText(activitiesDone);
            controller.startDateText.setText(startDate);
            controller.finishDateText.setText(finishDate);
            controller.descriptionText.setText(description);
        }  else if (editEducation() == 0) {
            System.out.println("error1");
        } else if (editEducation() == -9) {
            System.out.println("error2");

        } else if (editEducation() == -1) {
            System.out.println("error3");

        } else if (editEducation() == 10) {
            System.out.println("!!!");
        }
    }

    @FXML
    public void backToEducationPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EducationFXML.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        EducationController controller = loader.getController();

        // Ensure fields are not null
        if (controller.instituteText == null ||
                controller.fieldOfStudyText == null ||
                controller.gradeText == null ||
                controller.activitiesDoneText == null ||
                controller.startDateText == null ||
                controller.finishDateText == null ||
                controller.descriptionText == null) {
            System.out.println("One or more fields are not initialized!");
            return;
        }

        // Initialize the stage and scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        // Set the fields with existing data
        controller.instituteText.setText(institute);
        controller.fieldOfStudyText.setText(fieldOfStudy);
        controller.gradeText.setText(grade);
        controller.activitiesDoneText.setText(activitiesDone);
        controller.startDateText.setText(startDate);
        controller.finishDateText.setText(finishDate);
        controller.descriptionText.setText(description);
    }

    @FXML
    public void doneAddPressed(ActionEvent event){
        institute = instituteTextAdd.getText();
        fieldOfStudy =fieldOfStudyTextAdd.getText();
        grade = gradeTextAdd.getText();
        activitiesDone = activitiesDoneTextAdd.getText();
        try {
            startDate = startDateDatePickerAdd.getValue().toString();
        }
        catch (Exception e){
            startDate = "";
        }
        try {
            finishDate = finishDateDatePickerAdd.getValue().toString();
        } catch (Exception e) {
            finishDate = "";
        }
        description = descriptionTextAdd.getText();

        /////////then go to add scene
    }


}
