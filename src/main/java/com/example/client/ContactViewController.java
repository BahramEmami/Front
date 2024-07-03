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

import java.io.IOException;
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

    private static String profileURL = "";
    private static String shareEmail = "";
    private static String phoneNumber = "";
    private static String phoneType = "";
    private static String birthdate = "";
    private static String address = "";
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


    private static boolean isPhoneTypeSelected = false;
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
        birthDateTexFieldEdit.setValue(LocalDate.parse(birthdate));
        ///////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////

        addressTexFieldEdit.setText(address);

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

        if(profileURLTexFieldEdit.getText().length() != 0){
            profileURL = profileURLTexFieldEdit.getText();
        }
        profileURLTexField.setText(profileURL);
        if(shareEmailTexFieldEdit.getText().length() != 0){
            shareEmail = shareEmailTexFieldEdit.getText();
        }
        shareEmailTexField.setText(shareEmail);
        shareEmailTexField.setText(shareEmail);
        if(phoneNumberTexFieldEdit.getText().length() != 0){
            phoneNumber = phoneNumberTexFieldEdit.getText();
        }
        phoneNumberTexField.setText(phoneNumber);
        if(homeRadioButton.isSelected()){
            phoneType = "home";
            phoneTypeTexField.setText(phoneType);
            isPhoneTypeSelected = true;
        }
        else if (workRadioButton.isSelected()) {
            phoneType = "work";
            phoneTypeTexField.setText(phoneType);
            isPhoneTypeSelected = true;
        }
        else if (mobileRadioButton.isSelected()) {
            phoneType = "mobile";
            phoneTypeTexField.setText(phoneType);
            isPhoneTypeSelected = true;
        }
        if(addressTexFieldEdit.getText().length() != 0){
            address = addressTexFieldEdit.getText();
        }
        addressTexField.setText(address);

        try {
            birthdate = birthDateTexFieldEdit.getValue().toString();
            isBirhdaySelected = true;
        }
        catch (Exception e){
            isBirhdaySelected = false;
        }
        birthDateTexField.setText(birthdate);
    }
}
