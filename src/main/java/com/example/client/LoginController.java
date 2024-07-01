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

    private String emailOrUsername;

    private String password;

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
            emailOrUsername = userNameTextField.getText();
            password = passwordField.getText();
            //emailOrUsername = getEmailOrUsername();
            if(password.length() == 0 || emailOrUsername.length() == 0){
                statusLabel.setText("Please fill all fields!");
            }
            else if(logInStatus() == 1){
                statusLabel.setText("Successfully entered!");
                System.out.println(emailOrUsername + " " + password);
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
    /*public boolean validEmail(String emailOrUsername){

    }*/

    public int logInStatus(){
        return 1;
    }
}
