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
    private ImageView logo;
    @FXML
    private ImageView backToProfileButton;
    @FXML
    private ImageView editContactButton;
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

    private static final String id = "";
    private static String profileURL = "";
    private static String shareEmail = "";
    private static String phoneNumber = "";
    private static String phoneType = "";
    private static String birthdate = "";
    private static String birthdatePolicy = "";
    private static String address = "";
    private static String instantMessage = "";
    /////////////
    @FXML
    private ImageView backToContactViewButton;
    @FXML
    private ImageView doneEditButton;
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

        profileURLTexFieldEdit.setText(profileURL);
        shareEmailTexFieldEdit.setText(shareEmail);
        phoneNumberTexFieldEdit.setText(phoneNumber);
        //////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///possible error
        //birthDateTexFieldEdit.setValue(LocalDate.parse(birthdate));
        ///////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////

        addressTexFieldEdit.setText(address);
        instantMessageTextFieldEdit.setText(instantMessage);

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
            profileURLTexField.setText(profileURL);

            shareEmail = shareEmailTexFieldEdit.getText();

            shareEmailTexField.setText(shareEmail);

            phoneNumber = phoneNumberTexFieldEdit.getText();

            phoneNumberTexField.setText(phoneNumber);

            if (homeRadioButton.isSelected()) {
                phoneType = "home";
                phoneTypeTexField.setText(phoneType);
                isPhoneTypeSelected = true;
            } else if (workRadioButton.isSelected()) {
                phoneType = "work";
                phoneTypeTexField.setText(phoneType);
                isPhoneTypeSelected = true;
            } else if (mobileRadioButton.isSelected()) {
                phoneType = "mobile";
                phoneTypeTexField.setText(phoneType);
                isPhoneTypeSelected = true;
            }

            if(justMeRadioButton.isSelected()){
                birthdatePolicy = "me";
            }
            else if(myContactsRadioButton.isSelected()){
                birthdatePolicy = "contacts";
            }

            else if(everyOneRadioButton.isSelected()){
                birthdatePolicy = "everyone";
            }

            instantMessage = instantMessageTextFieldEdit.getText();
            instantMessageTextField.setText(instantMessage);


            address = addressTexFieldEdit.getText();
            addressTexField.setText(address);

            try {
                birthdate = birthDateTexFieldEdit.getValue().toString();
                isBirhdaySelected = true;
            } catch (Exception e) {
                isBirhdaySelected = false;
            }
            birthDateTexField.setText(birthdate);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("error123123");
        }
    }


    public int editContact() throws IOException {

        //"http://localhost:8080"/login/email/password

        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("profile_url", profileURL);
        json.put("email", shareEmail);
        json.put("phone_number", phoneNumber);
        json.put("phone_type", phoneType);
        json.put("birth_date", birthdate);
        json.put("birthday_policy", birthdatePolicy);
        json.put("address", address);
        json.put("instant_message", instantMessage);

        URL url = new URL("http://localhost:8080/" + "contact/" + "edit");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setDoOutput(true);
        tempConnection.getOutputStream().write(json.toString().getBytes());
        tempConnection.getOutputStream().close();

        if (tempConnection.getResponseCode() == 200) {//go to home page
            return 1;
        } else if (tempConnection.getResponseCode() == 400) {
            return 0;
        } else if (tempConnection.getResponseCode() == 404) {
            return -1;
        } else// nothing
            return 10;
    }
}
