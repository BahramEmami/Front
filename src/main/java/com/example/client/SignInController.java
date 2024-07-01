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
            else if(!isValidEmail(email)){
                statusLabel.setText("Enter a valid email!!");
            }
            else if(!sameRepeatedPass()){
                statusLabel.setText("Repeated Pass is wrong!! ");
            }
            /*else if(!emailIsUnique()){
                statusLabel.setText("Your email has already added!!");
            }*/
            /*else if(!validName()){
                statusLabel.setText("Enter a valid firstname!!");
            }*/
            /*else if(!validLastName()){
                statusLabel.setText("Enter a valid lastname");
            }*/
            else if(!validPass(passWord)){
                statusLabel.setText("Enter a valid pass");
            }
            else{
                statusLabel.setText("Success!!");
                wait(1500);
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

    public static boolean isValidEmail(String email) {
        // List of recognized email domains
        String[] validDomains = {
                "gmail", "yahoo", "hotmail", "outlook", "aol", "icloud", "mail", "yandex", "zoho", "protonmail",
                "gmx", "lycos", "comcast", "verizon", "att", "sbcglobal", "live", "msn", "me", "mac",
                "mailinator", "hushmail", "runbox", "lavabit", "fastmail", "tutanota", "inbox", "mail.com"
        };

        // List of valid top-level domains (TLDs)
        String[] validTLDs = {
                ".com", ".org", ".net", ".edu", ".gov", ".mil", ".int", ".us", ".uk", ".ca", ".de", ".fr", ".au", ".ir",
                ".io", ".tech", ".co", ".biz", ".info", ".mobi", ".site", ".online", ".xyz", ".club", ".space",
                ".store", ".blog", ".asia", ".africa", ".ru", ".cn", ".jp", ".br", ".mx", ".es", ".it", ".nl", ".se",
                ".no", ".fi"
        };

        // Check if the email contains '@'
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            return false;
        }

        // Check if the email contains '.' after '@'
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1) {
            return false;
        }

        // Check that '@' is not at the start or end
        if (atIndex == 0 || atIndex == email.length() - 1){
            return false;
        }

        // Check that '.' is not at the start or end
        if (dotIndex == 0 || dotIndex == email.length() - 1){
            return false;
        }

        // Ensure there's something between '@' and '.'
        if (dotIndex - atIndex < 2) {
            return false;
        }

        // Extract the domain part between '@' and '.'
        String domain = email.substring(atIndex + 1, dotIndex);

        // Check if the extracted domain is in the list of valid domains
        boolean domainValid = false;
        for (String validDomain : validDomains) {
            if (domain.equals(validDomain)) {
                domainValid = true;
                break;
            }
        }
        if (!domainValid) {
            return false;
        }

        // Check if the email ends with a valid TLD
        boolean tldValid = false;
        for (String validTLD : validTLDs) {
            if (email.endsWith(validTLD)) {
                tldValid = true;
                break;
            }
        }

        return tldValid;
    }

    public boolean sameRepeatedPass(){
        if(passWord.equals(repeatPassword)){
            return true;
        }
        return false;
    }


    /*public boolean emailIsUnique(){
        return true;
    }*/
    /*public boolean validName(){
    return true;
    }
    public boolean validLastName(){
        return true;
    }*/

    public boolean validPass(String password) {
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





}
