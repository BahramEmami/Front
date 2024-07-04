package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Button backButton;


    private String password = "";

    private String userName = "";

    private String email = "";


    @FXML
    public void logIn(ActionEvent event) {
        try {

            //puts input in email or username
            //userNameOrEmail(emailOrUsername);

            if (!(userNameTextField.getText().contains("@"))) {
                userName = userNameTextField.getText();
            } else if (userNameTextField.getText().contains("@")) {
                email = userNameTextField.getText();
            }
            password = passwordField.getText();


            if (!userName.contains("@")) {

                if (password.length() == 0 || userName.length() == 0) {
                    statusLabel.setText("Please fill all fields!");
                } else if (!GeneralMethods.validUserName(userName)) {
                    statusLabel.setText("Invalid username format!");
                } else if (!GeneralMethods.validPass(password)) {
                    statusLabel.setText("Invalid password format!");
                } else {
                    int returnLogInStatusId = logInStatus(userName, password);
                    if (returnLogInStatusId == 1) {
                        statusLabel.setText("Successfully entered!");

                        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else if (returnLogInStatusId == -1) {
                        statusLabel.setText("Wrong password!");
                    } else if (returnLogInStatusId == -2) {
                        statusLabel.setText("Username not found!!");
                    } else if (returnLogInStatusId == 0) {
                        statusLabel.setText("Error!!!");
                    }
                }
            }
            if (email.contains("@")) {
                if (password.length() == 0 || email.length() == 0) {
                    statusLabel.setText("Please fill all fields!");
                } else if (!GeneralMethods.isValidEmail(email)) {
                    statusLabel.setText("Invalid email format!");
                } else if (!GeneralMethods.validPass(password)) {
                    statusLabel.setText("Invalid password format!");
                } else {
                    int returnLogInStatusEmail = logInStatusEmail(email, password);
                    if (returnLogInStatusEmail == 1) {
//                        statusLabel.setText("Successfully entered!");
                        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else if (returnLogInStatusEmail == -1) {
                        statusLabel.setText("Wrong password!");
                    } else if (returnLogInStatusEmail == -2) {
                        statusLabel.setText("Email not found!!");
                    } else if (returnLogInStatusEmail == 0) {
                        statusLabel.setText("Error!!!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error 404");
        }
    }

    @FXML
    public void signInPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SigninFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public int logInStatus(String userName, String password) throws IOException {

        //"http://localhost:8080"/login/email/password
        URL url = new URL(GeneralMethods.getFirstOfUrl() + "login/" + userName + "/" + password);
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        if (tempConnection.getResponseCode() == 200) {
            String token = "";
            try {
                String response = GeneralMethods.getResponse(tempConnection);
                JSONObject jsonObject = new JSONObject(response);
                token = tempConnection.getHeaderField("LKN");

                String email = jsonObject.isNull("email") ? null : jsonObject.getString("email");
                String firstName = jsonObject.isNull("first_name") ? null : jsonObject.getString("first_name");
                String lastName = jsonObject.isNull("last_name") ? null : jsonObject.getString("last_name");
                String additionalName = jsonObject.isNull("additional_name") ? null : jsonObject.getString("additional_name");
                String workType = jsonObject.isNull("workType") ? null : jsonObject.getString("workType");

                GeneralMethods.saveUser(userName, email, password, firstName, lastName, additionalName, workType, token);

            } catch (Exception e) {
                System.out.println("Error JSON");
                e.printStackTrace();
            }

//            try {
//                File file = new File("src/main/resources/assets/token.txt");
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write(token);
//                fileWriter.close();
//            } catch (IOException e) {
//                System.out.println("ERROR in saving token");
//                e.printStackTrace();
//            }

        }


        if (tempConnection.getResponseCode() == 200) {//go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 401) {
            return -1;
        } else if (tempConnection.getResponseCode() == 402) {
            return -2;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else// nothing
            return 10;
    }

    public int logInStatusEmail(String email, String password) throws IOException {

        //"http://localhost:8080"/login/email/password
        URL url = new URL(GeneralMethods.getFirstOfUrl() + "login/" + email + "/" + password);
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");


        if (tempConnection.getResponseCode() == 200) {
            String token = "";
            try {
                String response = GeneralMethods.getResponse(tempConnection);
                JSONObject jsonObject = new JSONObject(response);
                token = tempConnection.getHeaderField("LKN");

                String id = jsonObject.isNull("id") ? null : jsonObject.getString("id");
                String firstName = jsonObject.isNull("first_name") ? null : jsonObject.getString("first_name");
                String lastName = jsonObject.isNull("last_name") ? null : jsonObject.getString("last_name");
                String additionalName = jsonObject.isNull("additional_name") ? null : jsonObject.getString("additional_name");
                String workType = jsonObject.isNull("workType") ? null : jsonObject.getString("workType");

                GeneralMethods.saveUser(id, email, password, firstName, lastName, additionalName, workType, token);

            } catch (Exception e) {
                System.out.println("Error JSON");
                e.printStackTrace();
            }

//            try {
//                File file = new File("src/main/resources/assets/token.txt");
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write(token);
//                fileWriter.close();
//            } catch (IOException e) {
//                System.out.println("ERROR in saving token");
//                e.printStackTrace();
//            }

        }
        if (tempConnection.getResponseCode() == 200) {//go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 401) {
            return -1;
        } else if (tempConnection.getResponseCode() == 402) {
            return -2;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else // nothing
            return 9;
    }

}