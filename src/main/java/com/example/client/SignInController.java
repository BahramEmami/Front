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

public class SignInController {
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

    private String firstName;
    private String lastName;
    private String email;
    private String passWord;
    private String repeatPassword;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void signInPressed(ActionEvent event){
        try {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            email = emailTextField.getText();
            passWord = passWordPassField.getText();
            repeatPassword = repeatPasswordPassField.getText();

            if(firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || passWord.length() == 0 || repeatPassword.length() == 0){
                statusLabel.setText("Please fill all fields!!");
            }
            else if(!validEmail()){
                statusLabel.setText("Enter a valid email!!");
            }
            else if(!sameRepeatedPass()){
                statusLabel.setText("Repeated Pass is wrong!! ");
            }
            else if(!emailIsUnique()){
                statusLabel.setText("Your email has already added!!");
            }
            else if(!validName()){
                statusLabel.setText("Enter a valid firstname!!");
            }
            else if(!validLastName()){
                statusLabel.setText("Enter a valid lastname");
            }
            else if(!validPass(passWord)){
                statusLabel.setText("Enter a valid pass");
            }
            else{
                statusLabel.setText("Success!!");
                Parent root = FXMLLoader.load(getClass().getResource("CompleteProfileFXML.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        }
        catch (Exception e){
            e.printStackTrace();
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

    public boolean validEmail(){
        return true;
    }

    public boolean sameRepeatedPass(){
        return true;
    }
    public boolean emailIsUnique(){
        return true;
    }
    public boolean validName(){
    return true;
    }
    public boolean validLastName(){
        return true;
    }
    public boolean validPass(String passWord){
        /*if(passWord.length()>= 8 && passWord.contains("1")){
            return true;
        }*/
        //return false;
        return true;
    }


}
