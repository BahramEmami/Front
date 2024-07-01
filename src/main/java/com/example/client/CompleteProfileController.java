package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class CompleteProfileController {
    @FXML
    private TextField additionalNameTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private Button logInButton;
    @FXML
    private Button backButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label statusLabel;

    private String additionalName = "";
    private String country = "";
    private String city = "";
    private String birthDate = "";

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean dateInputExist;

    @FXML
    public void signUpPressed(ActionEvent event){
        try {
             additionalName = additionalNameTextField.getText();
             country = countryTextField.getText();
             city = cityTextField.getText();

            try {
                birthDate = birthdayDatePicker.getValue().toString();
                dateInputExist = true;
            }
            catch (Exception e){
                statusLabel.setText("Please fill all fields!!");
                dateInputExist = false;

            }

             if(additionalName.length()== 0 || country.length()==0 || city.length()==0 || !dateInputExist){

                 statusLabel.setText("Please fill all fields!!");
             }
             else{
                 statusLabel.setText("Successful!!");
             }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SigninFXML.fxml"));
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

}
