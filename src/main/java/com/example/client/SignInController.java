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
import java.sql.Date;
import java.time.LocalDate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
            } else if (!isValidEmail(email)) {
                statusLabel.setText("Enter a valid email!!");
            } else if (!sameRepeatedPass()) {
                statusLabel.setText("Repeated Pass is wrong!! ");
            } else if (!validPass(passWord)) {
                statusLabel.setText("Enter a valid pass");
            } else if (!validUserNameComplete(userName)) {
                statusLabelComplete.setText("Invalid username format!");
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

    public boolean sameRepeatedPass() {
        if (passWord.equals(repeatPassword)) {
            return true;
        }
        return false;
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
                return true;
            }
        }

        return hasLetter && hasNumber;
    }

    public int signInStatus() throws IOException {

        //"http://localhost:8080"/login/email/password

        JSONObject json = new JSONObject();
        json.put("id", userName);
        json.put("email", email);

        URL url = new URL("http://localhost:8080/" + "user/" + "creating1");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setDoOutput(true);
        tempConnection.getOutputStream().write(json.toString().getBytes());
        tempConnection.getOutputStream().close();


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
            }

            else {
                int signInStatusCompleted1 = signInStatusCompleted();
                if (signInStatusCompleted1 == 1) {
                    statusLabelComplete.setText("Account Successfully Created!");
//

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

    public boolean validUserNameComplete(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '.' || c == '_')) {
                return false;
            }
        }
        return true;
    }


    public int signInStatusCompleted() throws IOException {

        //"http://localhost:8080"/login/email/password
        JSONObject json = new JSONObject();
        json.put("id", userName);
        json.put("email", email);
        json.put("first_name", firstName);
        json.put("last_name", lastName);
        json.put("password", passWord);
        json.put("additional_name", additionalNameComplete);
        json.put("join_date", Date.valueOf(LocalDate.now()));
        json.put("work_type", jobTypeComplete);

        URL url = new URL("http://localhost:8080/" + "user/" + "creating2");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setDoOutput(true);
        tempConnection.getOutputStream().write(json.toString().getBytes());
        tempConnection.getOutputStream().close();

        if (tempConnection.getResponseCode() == 200) {//go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else// nothing
            return 10;
    }


}
