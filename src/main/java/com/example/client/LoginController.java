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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        try {

            //puts input in email or username
            //userNameOrEmail(emailOrUsername);

            if (!(userNameTextField.getText().contains("@"))) {
                userName = userNameTextField.getText();
            } else if (userNameTextField.getText().contains("@")) {
                email = userNameTextField.getText();
            }
            password = passwordField.getText();


            if (userName != null) {
                int returnLogInStatusId = logInStatus(userName, password);
                if (password.length() == 0 || userName.length() == 0) {
                    statusLabel.setText("Please fill all fields!");
                } else if (!validUserName(userName)) {
                    statusLabel.setText("Invalid username format!");
                } else if (!validPass(password)) {
                    statusLabel.setText("Invalid password format!");
                } else if (returnLogInStatusId == 1) {
                    statusLabel.setText("Successfully entered!");
                    /**
                     *
                     *
                     * method next into profile
                     *
                     *
                     */
                } else if (returnLogInStatusId == -1) {
                    statusLabel.setText("Wrong password");
                } else if (returnLogInStatusId == -2) {
                    statusLabel.setText("Username not found");
                } else if (returnLogInStatusId == 0) {
                    statusLabel.setText("Error");
                }
            }
            if (email != null) {
                int returnLogInStatusEmail = logInStatusEmail(email, password);
                if (password.length() == 0 || email.length() == 0) {
                    statusLabel.setText("Please fill all fields!");
                } else if (!isValidEmail(email)) {
                    statusLabel.setText("Invalid email format!");
                } else if (!validPass(password)) {
                    statusLabel.setText("Invalid password format!");
                }
                else if (returnLogInStatusEmail == 1) {
                    statusLabel.setText("Successfully entered!\n    GOOD LUCK ;)");
                    /**
                     *
                     *
                     * method next into profile
                     *
                     *
                     */
                } else if (returnLogInStatusEmail == -1) {
                    statusLabel.setText("Wrong password!");
                } else if (returnLogInStatusEmail == -2) {
                    statusLabel.setText("Email not found!!");
                } else if (returnLogInStatusEmail == 0) {
                    statusLabel.setText("Error!!!");
                }
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


    public boolean validUserName(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '.' || c == '_')) {
                return false;
            }
        }
        return true;
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
        if (atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        // Check that '.' is not at the start or end
        if (dotIndex == 0 || dotIndex == email.length() - 1) {
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
                return hasLetter && hasNumber;
            }
        }

        return hasLetter && hasNumber;
    }


    public void userNameOrEmail(String input) {
        if (input.contains("@")) {
            email = input;
        } else {
            userName = input;
        }
    }


    public int logInStatus(String userName, String password) throws IOException {

        //"http://localhost:8080"/login/email/password
        URL url = new URL("http://localhost:8080/" + "login/" + userName + "/" + password);
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();

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
        URL url = new URL("http://localhost:8080/" + "login/" + email + "/" + password);
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();

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