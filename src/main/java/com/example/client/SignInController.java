package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SignInController {
    ////////////////////////////////////////// first signUp scene
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passWordPassField;
    @FXML
    private PasswordField repeatPasswordPassField;
    @FXML
    private Button backButton;
    @FXML
    private Button logInButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField userNameTextField;

    private static String firstName = "";
    private static String lastName = "";
    private static String email = "";
    private static String passWord = "";
    private static String repeatPassword = "";
    private static String userName = "";


    private Stage stage;
    private Scene scene;
    private Parent root;


    /////////////////////////////////////////////////////////////////////////Complete part
    @FXML
    private TextField additionalNameTextFieldComplete;
    @FXML
    private Button logInButtonComplete;
    @FXML
    private Button backButtonComplete;
    @FXML
    private Button signUpButtonComplete;
    @FXML
    private Label statusLabelComplete;

    @FXML
    private RadioButton wantToHireRadioComplete;
    @FXML
    private RadioButton lookingForJobRadioComplete;
    @FXML
    private RadioButton wantToProvideServiceRadioComplete;

    private static String additionalNameComplete = "";


    private static String jobTypeComplete = "";


    private static boolean isJobType = false;


    //////////////////////////////////////////////     First
    @FXML
    public void signInPressed(ActionEvent event) {
        try {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            email = emailTextField.getText();
            passWord = passWordPassField.getText();
            repeatPassword = repeatPasswordPassField.getText();
            userName = userNameTextField.getText();


            if (firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || passWord.length() == 0 || repeatPassword.length() == 0 || userName.length() == 0) {
                statusLabel.setText("Please fill all fields!!");
            } else if (!GeneralMethods.isValidEmail(email)) {
                statusLabel.setText("Enter a valid email!!");
            } else if (!sameRepeatedPass()) {
                statusLabel.setText("Repeated Pass is wrong!! ");
            } else if (!GeneralMethods.validPass(passWord)) {
                statusLabel.setText("Enter a valid pass");
            } else if (!GeneralMethods.validUserName(userName)) {
                statusLabel.setText("Invalid username format!");
            } else {
                int signInStatus1 = signInStatus();
                if (signInStatus1 == 1) {
                    statusLabel.setText("DONE ->");
//                    wait(3000);
                    Parent root = FXMLLoader.load(getClass().getResource("CompleteProfileFXML.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (signInStatus1 == -1) {
                    statusLabel.setText("Duplicated Id! TRY AGAIN");
                } else if (signInStatus1 == -2) {
                    statusLabel.setText("Duplicated Email!! TRY AGAIN");
                } else if (signInStatus1 == 0) {
                    statusLabel.setText("ERROR!!! TRY AGAIN");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error 404");
        }
    }

    @FXML
    public void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logInPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public boolean sameRepeatedPass() {
        if (passWord.equals(repeatPassword)) {
            return true;
        }
        return false;
    }


    public int signInStatus() throws IOException {

        //"http://localhost:8080"/user/creating1

        JSONObject json = new JSONObject();
        json.put("id", userName);
        json.put("email", email);

        URL url1 = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "checkingId");
        HttpURLConnection tempConnection1 = (HttpURLConnection) url1.openConnection();
        tempConnection1.setRequestMethod("GET");
        tempConnection1.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection1,json.toString());


        URL url2 = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "checkingEmail");
        HttpURLConnection tempConnection2 = (HttpURLConnection) url2.openConnection();
        tempConnection2.setRequestMethod("GET");
        tempConnection2.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection2, json.toString());


        if (tempConnection1.getResponseCode() == 200 && tempConnection2.getResponseCode() ==200) {//go to home page
            return 1;
        } else if (tempConnection1.getResponseCode() == 401) {
            return -1;
        } else if (tempConnection2.getResponseCode() == 402) {
            return -2;
        } else if (tempConnection1.getResponseCode() == 400 || tempConnection2.getResponseCode() == 400) {
            return 0;
        } else// nothing
            return 10;
    }


    //////////////////////////////////////////////////////////complete

    @FXML
    public void signUpPressedComplete(ActionEvent event) {
        try {

            additionalNameComplete = additionalNameTextFieldComplete.getText();


            if (wantToHireRadioComplete.isSelected()) {
                isJobType = true;
                jobTypeComplete = "want_to_hired";
            } else if (lookingForJobRadioComplete.isSelected()) {
                isJobType = true;
                jobTypeComplete = "looking_for_job";
            } else if (wantToProvideServiceRadioComplete.isSelected()) {
                isJobType = true;
                jobTypeComplete = "want_to_provide_service";
            } else {
                isJobType = false;
            }


            if (additionalNameComplete.length() == 0 || !isJobType) {
                statusLabelComplete.setText("Please fill completely!");
            } else {
                int signInStatusCompleted1 = signInStatusCompleted();
                if (signInStatusCompleted1 == 1) {
                    statusLabelComplete.setText("Account Successfully Created!");

                    Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else if (signInStatusCompleted1 == 0) {
                    statusLabelComplete.setText("Error!!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error 404");
        }
    }

    @FXML
    public void backPressedComplete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SigninFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logInPressedComplete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public int signInStatusCompleted() throws IOException {

        //"http://localhost:8080"/user/creating2

        JSONObject json = new JSONObject();

        json.put("id", userName);
        json.put("email", email);
        json.put("first_name", firstName);
        json.put("last_name", lastName);
        json.put("password", passWord);
        json.put("additional_name", additionalNameComplete);
        json.put("join_date", Date.valueOf(LocalDate.now()));
        json.put("work_type", jobTypeComplete);

        URL url = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "creating2");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection, json.toString());

        String response = GeneralMethods.getResponse(tempConnection);
        GeneralMethods.saveUser(userName, email, firstName, lastName, passWord, additionalNameComplete, jobTypeComplete, response);

        if (tempConnection.getResponseCode() == 200) {//go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else// nothing
            return 10;
    }


}
