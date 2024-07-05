package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;


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

        ////////////////////      set sql save


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


        if (firstNameTextFieldEdit.getText().length() == 0 || lastNameTextFieldEdit.getText().length() == 0 || emailTextFieldEdit.getText().length() == 0 || additionalTextFieldEdit.getText().length() == 0 || userNameTextFieldEdit.getText().length() == 0 || !isJobTypeSelected) {
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
            userName = Client.user.getID();


            if (!GeneralMethods.validPass(passWord) && passWord.length() != 0) {
                statusEditInfoLabel.setText("Invalid Password");
            } else if (!sameRepeatedPass()) {
                statusEditInfoLabel.setText("RepPass is Wrong");
            } else if (!GeneralMethods.isValidEmail(email)) {
                statusEditInfoLabel.setText("Invalid Email");
            } else if (editInfoStatus() == 1) {
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
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("logo");
    }


    /////////////////////////////////////////////////////
    @FXML
    public void backToProfilePressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        userName = Client.user.getID();
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


    public boolean sameRepeatedPass() {
        if (passWord.equals(repPassWord)) {
            return true;
        }
        return false;
    }


    public int editInfoStatus() throws IOException {


        JSONObject json = new JSONObject();

        json.put("id", Client.user.getID());
        json.put("email", email);
        json.put("first_name", firstName);
        json.put("last_name", lastName);
        json.put("password", passWord);
        json.put("additional_name", additoinalName);
        json.put("city", city);
        json.put("country", country);
//        ///////////////////////////////////////////////profession


        json.put("profession", "null");
        json.put("work_type", workType);

//        System.out.println(json.toString());


        URL url1 = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "creating1");
        HttpURLConnection tempConnection1 = (HttpURLConnection) url1.openConnection();
        tempConnection1.setRequestMethod("GET");
        tempConnection1.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection1, json.toString());

        if (tempConnection1.getResponseCode() == 200) {//go to home page
            System.out.println("Accepted id and email");
        } else if (tempConnection1.getResponseCode() == 402) {
            statusEditInfoLabel.setText("Email Is Used!");
            return 0;
        } else if (tempConnection1.getResponseCode() == 400) {
            System.out.println("Wrong input");
            return 0;
        }


        URL url2 = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "updating/");
        HttpURLConnection tempConnection2 = (HttpURLConnection) url2.openConnection();
        tempConnection2.setRequestMethod("GET");
        tempConnection2.setDoOutput(true);
        GeneralMethods.sendResponse(tempConnection2, json.toString());


        if (tempConnection2.getResponseCode() == 200) {
            System.out.println("Info Changed");
            return 1;
        } else if (tempConnection2.getResponseCode() != 200) {
            System.out.println("Info Not Changed");
            return 0;
        }

        return 1;
    }

    @FXML
    public void deleteAccount(ActionEvent event) throws IOException {


        //it goes to start scene

        URL url = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "deleteMyAccount/" + Client.user.getID() + "/" + Client.user.getPassWord());
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setDoOutput(true);
        System.out.println(url.toString());


        if (tempConnection.getResponseCode() == 200) {
            System.out.println("Account deleted :(");
            System.out.println(GeneralMethods.getResponse(tempConnection));
        } else if (tempConnection.getResponseCode() != 200) {
            System.out.println(GeneralMethods.getResponse(tempConnection));

        }

        Client.user = null;

        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
