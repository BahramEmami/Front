package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditInfoController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button backToInfoViewButton;
    @FXML
    private Button doneInfoEditButton;
    @FXML
    private Button logoInfoEdit;
    @FXML
    private TextField firstNameTextFieldEdit;
    @FXML
    private TextField lastNameTextFieldEdit;
    @FXML
    private TextField emailTextFieldEdit;
    @FXML
    private TextField countryTextFieldEdit;
    @FXML
    private TextField cityTextFieldEdit;
    @FXML
    private TextField additionalTextFieldEdit;
    @FXML
    private PasswordField passwordPassFieldEdit;
    @FXML
    private PasswordField repPassPassFieldEdit;
    @FXML
    private TextField userNameTextFieldEdit;
    @FXML
    private RadioButton wantToHireRadioButtonEdit;
    @FXML
    private RadioButton lookingForJobRadioButtonEdit;
    @FXML
    private RadioButton wantToProvideServiceRadioButtonEdit;
    @FXML
    private Label statusEditInfoLabel;

     static String firstName = "";
     static String lastName = "";
     static String email = "";
     static String workType = "";
     static String country = "";
     static String city = "";
     static String passWord = "";
     static String repPassWord = "";
     static String additoinalName = "";
     static String userName = "";

    private static boolean isJobTypeSelected = false;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @FXML
     Button backToProfileViewInfo;
    @FXML
     Button editInfoButton;
    @FXML
     Button logoInfoView;
    @FXML
     TextField firstNameTextFieldView;
    @FXML
     TextField lastNameTextFieldView;
    @FXML
     TextField emailTextFieldView;
    @FXML
     TextField workTypeTextFieldView;
    @FXML
     TextField countryTextFieldView;
    @FXML
     TextField cityTextFieldView;
    @FXML
     TextField additionalTextFieldView;
    @FXML
     TextField userNameTextFieldView;


    private static String firstName_View = "";
    private static String lastName_View = "";
    private static String email_View = "";
    private static String workType_View = "";
    private static String country_View = "";
    private static String city_View = "";
    private static String additoinalName_View = ".";
    private static String userName_View = "";


    @FXML
    public void doneEditPressed(ActionEvent event) throws IOException {

        if (wantToHireRadioButtonEdit.isSelected()) {
            workType = "want_to_hired";
            isJobTypeSelected = true;
        } else if (lookingForJobRadioButtonEdit.isSelected()) {
            workType = "looking_for_job";
            isJobTypeSelected = true;
        } else if (wantToProvideServiceRadioButtonEdit.isSelected()) {
            workType = "want_to_provide_service";
            isJobTypeSelected = true;
        }


        if (firstNameTextFieldEdit.getText().length() == 0 || lastNameTextFieldEdit.getText().length() == 0 || emailTextFieldEdit.getText().length() == 0 || countryTextFieldEdit.getText().length() == 0 || cityTextFieldEdit.getText().length() == 0 || additionalTextFieldEdit.getText().length() == 0 || userNameTextFieldEdit.getText().length() == 0 || !isJobTypeSelected) {
            statusEditInfoLabel.setText("Fill all fields");
        } else {
            firstName = firstNameTextFieldEdit.getText();
            lastName = lastNameTextFieldEdit.getText();
            email = emailTextFieldEdit.getText();
            country = countryTextFieldEdit.getText();
            city = cityTextFieldEdit.getText();
            passWord = passwordPassFieldEdit.getText();
            repPassWord = repPassPassFieldEdit.getText();
            additoinalName = additionalTextFieldEdit.getText();
            userName = userNameTextFieldEdit.getText();


            if (!isValidEmail(email) && email.length() != 0) {
                statusEditInfoLabel.setText("Invalid email");
            } else if (!validUserName(userName)) {
                statusEditInfoLabel.setText("Invalid username");
            } else if (!validPass(passWord)) {
                statusEditInfoLabel.setText("Invalid Password");
            } else if (!sameRepeatedPass()) {
                statusEditInfoLabel.setText("RepPass is Wrong");
            } else if (!checkUniqueEmail(email)) {
                statusEditInfoLabel.setText("Email exists");
            } else if (!checkUniqueUserName(userName)) {
                statusEditInfoLabel.setText("Username exists");
            }

            if (editInfoStatus() == 1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInfoFXML.fxml"));
                Parent root = loader.load();

                // Get the controller associated with the FXML file
                EditInfoController controller = loader.getController();

                // Ensure fields are not null
                if (controller.firstNameTextFieldView == null ||
                        controller.lastNameTextFieldView == null ||
                        controller.emailTextFieldView == null ||
                        controller.workTypeTextFieldView == null ||
                        controller.countryTextFieldView == null ||
                        controller.cityTextFieldView == null ||
                        controller.additionalTextFieldView == null ||
                        controller.userNameTextFieldView == null) {
                    System.out.println("One or more fields are not initialized!");
                    return;
                }

                // Initialize the stage and scene
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


                controller.firstNameTextFieldView.setText(firstName);
                controller.lastNameTextFieldView.setText(lastName);
                controller.emailTextFieldView.setText(email);
                if (wantToHireRadioButtonEdit.isSelected()) {
                    controller.workTypeTextFieldView.setText("Want to hire");
                } else if (lookingForJobRadioButtonEdit.isSelected()) {
                    controller.workTypeTextFieldView.setText("Looking for job");
                } else if (wantToProvideServiceRadioButtonEdit.isSelected()) {
                    controller.workTypeTextFieldView.setText("Want to provide service");
                }
                controller.countryTextFieldView.setText(country);
                controller.cityTextFieldView.setText(city);
                controller.additionalTextFieldView.setText(additoinalName);
                controller.userNameTextFieldView.setText(userName);

            }
        }


    }

    @FXML
    public void backToViewPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewInfoFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logoPressed(ActionEvent event) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        System.out.println("logo");
    }


    /////////////////////////////////////////////////////
    @FXML
    public void backToProfilePressed(ActionEvent event) {
        /*Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        System.out.println("back to profile");
    }


    @FXML
    public void editInfoPressed(ActionEvent event) throws IOException {
        // Load the FXML file and initialize the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditInfoFXML.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        EditInfoController controller = loader.getController();

        // Ensure fields are not null
        if (controller.firstNameTextFieldEdit == null ||
                controller.lastNameTextFieldEdit == null ||
                controller.emailTextFieldEdit == null ||
                controller.wantToProvideServiceRadioButtonEdit == null ||
                controller.wantToHireRadioButtonEdit == null ||
                controller.lookingForJobRadioButtonEdit == null ||
                controller.countryTextFieldEdit == null ||
                controller.cityTextFieldEdit == null ||
                controller.additionalTextFieldEdit == null ||
                controller.userNameTextFieldEdit == null) {
            System.out.println("One or more fields are not initialized!");
            return;
        }

        // Initialize the stage and scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        firstName = firstNameTextFieldView.getText();
        lastName = lastNameTextFieldView.getText();
        email = emailTextFieldView.getText();
        workType = workTypeTextFieldView.getText();
        country = countryTextFieldView.getText();
        city = cityTextFieldView.getText();
        additoinalName = additionalTextFieldView.getText();
        userName = userNameTextFieldView.getText();
        // Set the fields with existing data
        controller.firstNameTextFieldEdit.setText(firstName);
        controller.lastNameTextFieldEdit.setText(lastName);
        controller.emailTextFieldEdit.setText(email);
        if (workTypeTextFieldView.getText().equals("Want to hire")) {
            controller.wantToHireRadioButtonEdit.fire();
        } else if (workTypeTextFieldView.getText().equals("Looking for job")) {
            controller.lookingForJobRadioButtonEdit.fire();
        } else if (workTypeTextFieldView.getText().equals("Want to provide service")) {
            controller.wantToProvideServiceRadioButtonEdit.fire();
        }
        controller.countryTextFieldEdit.setText(country);
        controller.cityTextFieldEdit.setText(city);
        controller.additionalTextFieldEdit.setText(additoinalName);
        controller.userNameTextFieldEdit.setText(userName);
    }


    ///////////////////////////////
    public boolean validUserName(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '.' || c == '_')) {
                return false;
            }
        }
        return true;
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

    public boolean sameRepeatedPass() {
        if (passWord.equals(repPassWord)) {
            return true;
        }
        return false;
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


    public boolean checkUniqueEmail(String email) {
        return true;
    }

    public boolean checkUniqueUserName(String cUserName) {
        return true;
    }

    public int editInfoStatus() {
        return 1;
    }

    @FXML
    public void deleteAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //it goes to start scene
        //code to delete account
    }


}
