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
    private TextField profileURLTexField;
    @FXML
    private TextField shareEmailTexField;
    @FXML
    private TextField phoneNumberTexField;
    @FXML
    private TextField phoneTypeTexField;
    @FXML
    private TextField birthDateTexField;
    @FXML
    private TextField addressTexField;
    @FXML
    private TextField instantMessageTextField;

    private static String profileURL = " ";
    private static String shareEmail = " ";
    private static String phoneNumber = " ";
    private static String phoneType = "mobile";
    private static String birthdate  ;
    private static String birthdatePolicy = "me";
    private static String address = " ";
    private static String instantMessage = " ";
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
    @FXML
    private DatePicker birthDateTexFieldEdit;
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
        Parent root = FXMLLoader.load(getClass().getResource("ContactEditFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//
//        profileURLTexFieldEdit.setText(profileURL);
//        shareEmailTexFieldEdit.setText(shareEmail);
//        phoneNumberTexFieldEdit.setText(phoneNumber);
//
//        //////////////////////////////////////////////////////////////////////////////////////////////
//        //////////////////////////////////////////////////////////////////////////////////////////////
//        ///////////////////////////////////////////////////////////////////////////////////////////////
//        ///possible error
//        //birthDateTexFieldEdit.setValue(LocalDate.parse(birthdate));
//        ///////////////////////////////////////////////////////////////////////////////
//        /////////////////////////////////////////////////////////////////////////////////
//        /////////////////////////////////////////////////////////////////////////////
//
//        addressTexFieldEdit.setText(address);
//        instantMessageTextFieldEdit.setText(instantMessage);

    }


    @FXML
    private void backToContactViewPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ContactViewFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void doneEditPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ContactViewFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        try {


            if (profileURLTexFieldEdit.getText() != null) {
                profileURL = profileURLTexFieldEdit.getText();

//            profileURLTexField.setText(profileURL);
            }
            if (shareEmailTexFieldEdit.getText() != null) {
                shareEmail = shareEmailTexFieldEdit.getText();

//            shareEmailTexField.setText(shareEmail);
            }
            if (phoneNumberTexFieldEdit.getText() != null) {
                phoneNumber = phoneNumberTexFieldEdit.getText();

//            phoneNumberTexField.setText(phoneNumber);
            }
//            if (homeRadioButton.isSelected()) {
//                phoneType = "home";
//                phoneTypeTexField.setText(phoneType);
//                isPhoneTypeSelected = true;
//            } else if (workRadioButton.isSelected()) {
//                phoneType = "work";
//                phoneTypeTexField.setText(phoneType);
//                isPhoneTypeSelected = true;
//            } else if (mobileRadioButton.isSelected()) {
//                phoneType = "mobile";
//                phoneTypeTexField.setText(phoneType);
//                isPhoneTypeSelected = true;
//            }
//
//            if (justMeRadioButton.isSelected()) {
//                birthdatePolicy = "me";
//                isBirthdatePolicy = true;
//            } else if (myContactsRadioButton.isSelected()) {
//                birthdatePolicy = "contacts";
//                isBirthdatePolicy = true;
//            } else if (everyOneRadioButton.isSelected()) {
//                birthdatePolicy = "everyone";
//                isBirthdatePolicy = true;
//            }

            if (instantMessageTextFieldEdit.getText() != null) {
                instantMessage = instantMessageTextFieldEdit.getText();
//                instantMessageTextField.setText(instantMessage);
            }
            if (addressTexFieldEdit.getText() != null) {
                address = addressTexFieldEdit.getText();
//                addressTexField.setText(address);
            }


            try {
                birthdate = birthDateTexFieldEdit.getValue().toString();
                System.out.println(1);
                isBirhdaySelected = true;
            java.sql.Date newBirthDate = birthdate== null ? null : java.sql.Date.valueOf(birthdate);
            System.out.println(newBirthDate);
            } catch (Exception e) {
                e.printStackTrace();
                isBirhdaySelected = false;
            }
            if (editContact() == 1) {
                System.out.println("Done");
            } else if (editContact() == 0) {
                System.out.println("Wrong input");
            } else if (editContact() == -1) {
                System.out.println("Error back");
            } else if (editContact() == -9) {
                System.out.println("Error JSON");
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error123");
        }
    }


    public int editContact() throws IOException {

        //"http://localhost:8080"/contact/edit

        JSONObject json = new JSONObject();
        json.put("id", Client.user.getID());
        json.put("profile_url", profileURL);
        json.put("email", shareEmail);
        json.put("phone_number", phoneNumber);
        json.put("phone_type", phoneType);
        json.put("birth_date", Date.valueOf(birthdate));
        json.put("birthday_policy", birthdatePolicy);
        json.put("address", address);
        json.put("instant_message", instantMessage);

        URL url = new URL(GeneralMethods.getFirstOfUrl() + "contact/" + "edit");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setRequestProperty("LKN", Client.user.getToken());
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


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "contact/" + "view");
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
