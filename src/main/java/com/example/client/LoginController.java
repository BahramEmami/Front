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

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public TextField userNameTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button logInButton;
    @FXML
    public Button signInButton;
    @FXML
    public Label statusLabel;
    @FXML
    private Button backButton;

    //private String emailOrUsername;

    private String password;

    private String userName = "";

    private String email = "";

   /* @FXML
    public void getEmailOrUsername(ActionEvent event){
        try{
            emailOrUsername = userNameTextField.getText();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void getPassword(ActionEvent event) {
        try {
            password = passwordField.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    public void logIn(ActionEvent event) {
        try{

            //puts input in email or username
            //userNameOrEmail(emailOrUsername);



            userName = userNameTextField.getText();
            password = passwordField.getText();
            //emailOrUsername = getEmailOrUsername();
            if(password.length() == 0 || userName.length() == 0){
                statusLabel.setText("Please fill all fields!");
            }
            else if(!validUserName(userName)){
                statusLabel.setText("Invalid username format!");
            }
            else if(!validPass(password)){
                statusLabel.setText("Invalid password format!");
            }
            else if(logInStatus() == 1){
                statusLabel.setText("Successfully entered!");
                //System.out.println(emailOrUsername + " " + password);
            }
            else{
                statusLabel.setText("Wrong password or email or userID");
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    public boolean validUserName(String username){
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '.' || c == '_')) {
                return false;
            }
        }
        return true;
    }

    public boolean validPass(String password){
        if (password.length() < 8) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasNumber = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }

            // If both conditions are met, no need to check further
            if (hasLetter && hasNumber) {
                return true;
            }
        }

        return hasLetter && hasNumber;
    }


    public void userNameOrEmail(String input){
        if(input.contains("@")){
            email = input;
        }
        else{
            userName = input;
        }
    }



    public int logInStatus(){
        return 1;
    }









}
