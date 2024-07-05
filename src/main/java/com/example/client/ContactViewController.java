package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;

public class ContactViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button logo;
    @FXML
    private Button backToProfileButton;
    @FXML
    private Button editContactButton;
    @FXML
    TextField profileURLTexField;
    @FXML
    TextField shareEmailTexField;
    @FXML
    TextField phoneNumberTexField;
    @FXML
    TextField phoneTypeTexField;
    @FXML
    TextField birthDateTexField;
    @FXML
    TextField addressTexField;
    @FXML
    TextField instantMessageTextField;

    private static String profileURL = "";
    private static String shareEmail = "";
    private static String phoneNumber = "";
    private static String phoneType = "mobile";
    private static String birthdate = "";
    private static LocalDate localDateBirthDate;/////////////////////////
    private static String birthdatePolicy = "me";
    private static String address = "";
    private static String instantMessage = "";
    /////////////
    @FXML
    private Button backToContactViewButton;
    @FXML
    private Button doneEditButton;
    @FXML
    private Button logoEdit;
    @FXML
    private TextField profileURLTexFieldEdit;
    @FXML
    private TextField shareEmailTexFieldEdit;
    @FXML
    private TextField phoneNumberTexFieldEdit;
    @FXML
    private RadioButton mobileRadioButton;
    @FXML
    private RadioButton homeRadioButton;
    @FXML
    private RadioButton workRadioButton;


    private static String profileURLEdit = " ";
    private static String shareEmailEdit = " ";
    private static String phoneNumberEdit = " ";
    private static String phoneTypeEdit = "mobile";
    private static String birthdateEdit = " ";
    private static String birthdatePolicyEdit = "me";
    private static String addressEdit = " ";
    private static String instantMessageEdit = " ";
    ///////////////////////////
    //////////////////////////
    ////////////////////////////
    @FXML
    private DatePicker birthDateDatePickerEdit;
    @FXML
    private TextField addressTexFieldEdit;
    @FXML
    private TextField instantMessageTextFieldEdit;
    @FXML
    private RadioButton justMeRadioButton;
    @FXML
    private RadioButton myContactsRadioButton;

    @FXML
    private RadioButton everyOneRadioButton;


    private static boolean isPhoneTypeSelected = false;
    private static boolean isBirthdatePolicy = false;
    private static boolean isBirhdaySelected = false;

    @FXML
    public void logoPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToProfilePressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editContactPressed(ActionEvent event) throws IOException {
        profileURL = profileURLTexField.getText();
        shareEmail = shareEmailTexField.getText();
        phoneNumber = phoneNumberTexField.getText();
        phoneType = phoneTypeTexField.getText();
        birthdate = birthDateTexField.getText();
        address = addressTexField.getText();
        instantMessage = instantMessageTextField.getText();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactEditFXML.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        ContactViewController controller = loader.getController();

        // Ensure fields are not null
        if (controller.profileURLTexFieldEdit == null ||
                controller.shareEmailTexFieldEdit == null ||
                controller.phoneNumberTexFieldEdit == null ||
                controller.mobileRadioButton == null ||
                controller.workRadioButton == null ||
                controller.homeRadioButton == null ||
                controller.birthDateDatePickerEdit == null ||
                controller.addressTexFieldEdit == null ||
                controller.instantMessageTextFieldEdit == null ||
                controller.justMeRadioButton == null ||
                controller.myContactsRadioButton == null ||
                controller.everyOneRadioButton == null) {
            System.out.println("One or more fields are not initialized!");
            return;
        }

        // Initialize the stage and scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        // Set the fields with existing data
        controller.profileURLTexFieldEdit.setText(profileURL);
        controller.shareEmailTexFieldEdit.setText(shareEmail);
        controller.phoneNumberTexFieldEdit.setText(phoneNumber);
        if (phoneType.equals("mobile")) {
            controller.mobileRadioButton.fire();
        } else if (phoneType.equals("home")) {
            controller.homeRadioButton.fire();
        } else if (phoneType.equals("work")) {
            controller.workRadioButton.fire();
        }
        controller.addressTexFieldEdit.setText(address);
        controller.instantMessageTextFieldEdit.setText(instantMessage);
        if (birthdatePolicy.equals("me")) {
            controller.justMeRadioButton.fire();
        } else if (birthdatePolicy.equals("contacts")) {
            controller.myContactsRadioButton.fire();
        } else if (birthdatePolicy.equals("everyone")) {
            controller.everyOneRadioButton.fire();
        }

    }


    @FXML
    private void backToContactViewPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ContactViewFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ////////////////////////
//////////////////////////
/////////////////////////
/////////////////////////
////////////////////////
    @FXML
    private void doneEditPressed(ActionEvent event) throws IOException {

        ////////////////      set sql save


        profileURL = profileURLTexFieldEdit.getText();
        shareEmail = shareEmailTexFieldEdit.getText();
        phoneNumber = phoneNumberTexFieldEdit.getText();
        if (mobileRadioButton.isSelected()) {
            phoneType = "mobile";
        } else if (homeRadioButton.isSelected()) {
            phoneType = "home";
        } else if (workRadioButton.isSelected()) {
            phoneType = "work";
        }
//        birthdate = birthDateDatePickerEdit.getValue().toString();
        localDateBirthDate = birthDateDatePickerEdit.getValue();

        address = addressTexFieldEdit.getText();
        instantMessage = instantMessageTextFieldEdit.getText();


        if (editContact() == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactViewFXML.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the FXML file
            ContactViewController controller = loader.getController();

            // Ensure fields are not null
            if (controller.profileURLTexField == null ||
                    controller.shareEmailTexField == null ||
                    controller.phoneNumberTexField == null ||
                    controller.phoneTypeTexField == null ||
                    controller.birthDateTexField == null ||
                    controller.addressTexField == null ||
                    controller.instantMessageTextField == null) {
                System.out.println("One or more fields are not initialized!");
                return;
            }

            // Initialize the stage and scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            // Set the fields with existing data
            controller.profileURLTexField.setText(profileURL);
            controller.shareEmailTexField.setText(shareEmail);
            controller.phoneNumberTexField.setText(phoneNumber);
            if (mobileRadioButton.isSelected()) {
                controller.phoneTypeTexField.setText("mobile");
            } else if (homeRadioButton.isSelected()) {
                controller.phoneTypeTexField.setText("home");
            } else if (workRadioButton.isSelected()) {
                controller.phoneTypeTexField.setText("work");
            }
            controller.addressTexField.setText(address);
            controller.instantMessageTextField.setText(instantMessage);
            if (justMeRadioButton.isSelected()) {
                birthdatePolicy = "me";
            } else if (myContactsRadioButton.isSelected()) {
                birthdatePolicy = "contacts";
            } else if (everyOneRadioButton.isSelected()) {
                birthdatePolicy = "everyone";
            }
        } else if (editContact() == 0) {
            System.out.println("asdasasdasdasd");
        } else if (editContact() == -9) {
            System.out.println("asdasasdasdasdsde5353435354");

        } else if (editContact() == -1) {
            System.out.println("asdasasdasdasdsde5353435354sdfwt3w35w;ergjj;wierjpgjerg");

        } else if (editContact() == 10) {

        }
        /////بقیه شرظای لازم از ظرف دیتا بیس رو بزن
    }

    /////////////////////////////
    /////////////////////////////
    ////////////////////////////
    /////////////////////////


    public int editContact() throws IOException {

        //"http://localhost:8080"/contact/edit

        JSONObject json = new JSONObject();

        json.put("id", Client.user.getID());
        json.put("profile_url", profileURL);
        json.put("email", shareEmail);
        json.put("phone_number", phoneNumber);
        json.put("phone_type", phoneType);
        json.put("birthday_policy", birthdatePolicy);
        json.put("address", address);
        json.put("instant_message", instantMessage);
        if (birthDateDatePickerEdit.getValue() == null) {
            json.put("birth_date", Date.valueOf(LocalDate.now()));
        }else if (birthDateDatePickerEdit.getValue() != null) {
            json.put("birth_date", Date.valueOf(localDateBirthDate));
        }


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "contact/" + "edit/" + Client.user.getID());
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setRequestProperty("LKN", Client.user.getID());
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


    public int viewContact() throws IOException {

        //"http://localhost:8080"/contact/view


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "contact/" + "view/" + Client.user.getID());
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        String token = "";
        if (tempConnection.getResponseCode() == 200) {
            String response = GeneralMethods.getResponse(tempConnection);
            JSONObject jsonObject = new JSONObject(response);
            token = tempConnection.getHeaderField("LKN");

            String id = jsonObject.isNull("id") ? null : jsonObject.getString("id");
            String profile_url = jsonObject.isNull("profile_url") ? null : jsonObject.getString("profile_url");
            String email = jsonObject.isNull("email") ? null : jsonObject.getString("email");
            String phone_number = jsonObject.isNull("phone_number") ? null : jsonObject.getString("phone_number");
            String phone_type = jsonObject.isNull("phone_type") ? "mobile" : jsonObject.getString("phone_type");
            String birth_date = jsonObject.isNull("birth_date") ? null : jsonObject.getString("birth_date");
            String birthday_policy = jsonObject.isNull("birthday_policy") ? "me" : jsonObject.getString("birthday_policy");
            String address = jsonObject.isNull("address") ? null : jsonObject.getString("address");
            String instant_message = jsonObject.isNull("instant_message") ? null : jsonObject.getString("instant_message");
        }


        if (tempConnection.getResponseCode() == 200) {      //go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else if (tempConnection.getResponseCode() == 404) {
            return -1;
        } else// nothing
            return 10;
    }
}
