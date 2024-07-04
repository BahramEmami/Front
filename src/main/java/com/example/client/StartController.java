package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Button ContinueButton;
    @FXML
    private Button goToSignInButton;
    @FXML
    private Button goToLoginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void loginPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogInFXML.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signInPressed(ActionEvent event)throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SigninFXML.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
