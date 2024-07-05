package com.example.client;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class ProfileController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button homeButton;

    @FXML
    private Button myNetworkButton;

    @FXML
    private Button newPostButton;

    @FXML
    private Button jobsButton;

    @FXML
    private Button messageButton;

    @FXML
    private ImageView titleImageImageView;
    @FXML
    private ImageView profileImageImageview;
    @FXML
    private Button editProfileButton;

    @FXML
    private Button educationSeaMore;
    @FXML
    private Button connectWithMeSeaMore;
    @FXML
    private Button myJobsSeaMore;
    @FXML
    private Button settingButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button seeInfoButton;

    @FXML
    private Button editProfileImageButton;

    @FXML
    private Button editTitleImageButton;

    @FXML
    private Label statusLabel;

    private static Image profileImage = null;
    private static Image titleImage = null;

    private static String userName = "";

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////for showing infos in education and contact and info
    private static String eduInstituteView = "";
    private static String eduFieldOfStudeView = "";
    private static String eduGradeView = "";
    private static String eduActivitiesDoneView = "";
    private static String eduStartDateView = "";
    private static String eduFinishDateView = "";
    private static String eduDescriptionView = "";
    /*****************************************************/
    private static String conProfileUrlView = "";
    private static String conEmailView = "";
    private static String conPhoneNumberView = "";
    private static String conPhoneTypeView = "";
    private static String conBirthdateView = "";
    private static String conAddressView = "";
    private static String conInstantMessageView = "";
    /********************************************************/
    private static String infoFirstName = "";
    private static String infoLastName = "";
    private static String infoEmail = "";
    private static String infoWorkType = "";
    private static String infoCountry = "";
    private static String infoCity = "";
    private static String infoAdditionalName = "";
    private static String infoUserName = "";


    //////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////               username fix kon ba text fieldsh
    ///////////////////////////////////////////////////////////////

    @FXML
    public void homePressed(ActionEvent event) {
        // go home scene
    }

    @FXML
    public void myNetWorkPressed(ActionEvent event) {
        // go to MyNetwork scene
    }

    @FXML
    public void newPostPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PostingFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void jobsPressed(ActionEvent event) {
        // go to jobs scene
    }

    @FXML
    public void messagePressed(ActionEvent event) {
        // go to message scene
    }

    @FXML
    public void editProfilePressed(ActionEvent event) {
        //got to edit profile
    }

    @FXML
    public void myJobsSeaPressed(ActionEvent event) {
        //got to myJobsSeaPressed sea more
    }

    /////////////////
////////////////

    @FXML
    public void educationSeaMorePressed(ActionEvent event) throws IOException {


        //////////////////////////////////   get sql show


        FXMLLoader loader = new FXMLLoader(getClass().getResource("EducationFXML.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        EducationController controller = loader.getController();

        // Ensure fields are not null
        if (controller.instituteText == null ||
                controller.fieldOfStudyText == null ||
                controller.gradeText == null ||
                controller.activitiesDoneText == null ||
                controller.startDateText == null ||
                controller.finishDateText == null ||
                controller.descriptionText == null) {
            System.out.println("One or more fields are not initialized!");
            return;
        }

        // Initialize the stage and scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        controller.instituteText.setText(eduInstituteView);
        controller.fieldOfStudyText.setText(eduFieldOfStudeView);
        controller.gradeText.setText(eduGradeView);
        controller.activitiesDoneText.setText(eduActivitiesDoneView);
        controller.startDateText.setText(eduStartDateView);
        controller.finishDateText.setText(eduFinishDateView);
        controller.descriptionText.setText(eduDescriptionView);
    }

    /////////////////
////////////////
    @FXML
    public void connectWithMeSeaPressed(ActionEvent event) throws IOException {

        //////////////////////      get sql show
        if (viewContact() == 1) {
            System.out.println("Done");
        } else if (viewContact() == -1) {
            System.out.println("Error");
        } else if (viewContact() == 0) {
            System.out.println("First Time");
        } else if (viewContact() == 10) {
            System.out.println(10);
        }


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

        controller.profileURLTexField.setText(conProfileUrlView);
        controller.shareEmailTexField.setText(conEmailView);
        controller.phoneNumberTexField.setText(conPhoneNumberView);
        controller.phoneTypeTexField.setText(conPhoneTypeView);
        controller.birthDateTexField.setText(conBirthdateView);
        controller.addressTexField.setText(conAddressView);
        controller.instantMessageTextField.setText(conInstantMessageView);
    }

    public int viewContact() throws IOException {

        //"http://localhost:8080"/contact/view


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "contact/" + "view");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setRequestProperty("LKN", Client.user.getToken());
        tempConnection.setDoOutput(true);

        if (tempConnection.getResponseCode() == 200) {

            try {

                String response = GeneralMethods.getResponse(tempConnection);
//                System.out.println(response);
                JSONObject jsonObject = new JSONObject(response);

//                userName = jsonObject.getString("id");
//                conProfileUrlView = jsonObject.getString("profileURL");
//                conEmailView = jsonObject.getString("shareEmail");
//                conPhoneNumberView = jsonObject.getString("phoneNumber");
//                conPhoneTypeView = jsonObject.getString("numberType");
//                conBirthdateView = jsonObject.getString("birthdate");
//                conAddressView = jsonObject.getString("address");
//                conInstantMessageView = jsonObject.getString("instantMassaging");
                userName =  jsonObject.getString("id");
                conProfileUrlView = jsonObject.isNull("profileURL") ? null : jsonObject.getString("profileURL");
                conEmailView = jsonObject.isNull("shareEmail") ? null : jsonObject.getString("shareEmail");
                conPhoneNumberView = jsonObject.isNull("phoneNumber") ? null : jsonObject.getString("phoneNumber");
                conPhoneTypeView = jsonObject.getString("numberType");
                conBirthdateView = jsonObject.isNull("birthdate") ? null : jsonObject.getString("birthdate");
                conAddressView = jsonObject.isNull("address") ? null : jsonObject.getString("address");
                conInstantMessageView = jsonObject.isNull("instantMassaging") ? null : jsonObject.getString("instantMassaging");
            } catch (Exception e) {
                System.out.println("Error JSON");
                e.printStackTrace();
            }
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

    @FXML
    public void seeInfoPressed(ActionEvent event) throws IOException {


        if (viewInfo() == 1) {
            System.out.println("Done");
        } else if (viewInfo() == -1) {
            System.out.println("Error");
        } else if (viewInfo() == 0) {
            System.out.println("First Time");
        } else if (viewInfo() == 10) {
            System.out.println(10);
        }


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

        controller.firstNameTextFieldView.setText(infoFirstName);
        controller.lastNameTextFieldView.setText(infoLastName);
        controller.emailTextFieldView.setText(infoEmail);
        controller.workTypeTextFieldView.setText(infoWorkType);
        controller.countryTextFieldView.setText(infoCountry);
        controller.cityTextFieldView.setText(infoCity);
        //////////////////////////////////////////////////////profession
//        controller.professionTextFieldView.setText(infoProfession);
        controller.additionalTextFieldView.setText(infoAdditionalName);
        controller.userNameTextFieldView.setText(infoUserName);


    }

    public int viewInfo() throws IOException {

        //"http://localhost:8080"/contact/view


        URL url = new URL(GeneralMethods.getFirstOfUrl() + "user/" + "view");
        HttpURLConnection tempConnection = (HttpURLConnection) url.openConnection();
        tempConnection.setRequestMethod("GET");
        tempConnection.setRequestProperty("LKN", Client.user.getToken());
        tempConnection.setDoOutput(true);

        if (tempConnection.getResponseCode() == 200) {

            try {

                String response = GeneralMethods.getResponse(tempConnection);
//                System.out.println(response);
                JSONObject jsonObject = new JSONObject(response);


                infoFirstName = jsonObject.getString("firstName");
                infoLastName = jsonObject.getString("lastName");
                infoEmail = jsonObject.getString("email");
                infoWorkType = jsonObject.getString("workType");


                infoCountry = jsonObject.isNull("country") ? null : jsonObject.getString("country");
                infoCity = jsonObject.isNull("city") ? null : jsonObject.getString("city");


//                infoProfession = jsonObject.isNull("profession") ? null : jsonObject.getString("profession");


                //////////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////


//                if (jsonObject.getString("profession") != null) {
//                    profession = jsonObject.getString("profession");
//                }

                infoAdditionalName = jsonObject.getString("additionalName");
                infoUserName = jsonObject.getString("id");
            } catch (Exception e) {
                System.out.println("Error JSON");
                e.printStackTrace();
            }
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

    @FXML
    public void signOutPressed(ActionEvent event) throws IOException {
        Client.user = null;

        Parent root = FXMLLoader.load(getClass().getResource("LogInFXML.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void settingPressed(ActionEvent event) {
        //go to setting scene
    }

    @FXML
    public void editProfileImagePressed(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            profileImage = new Image(new FileInputStream(file));
            profileImageImageview.setImage(profileImage);
        } catch (FileNotFoundException fe) {
            statusLabel.setText("File not found!");
        }
    }

    @FXML
    public void editTitleImagePressed(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            titleImage = new Image(new FileInputStream(file));
            titleImageImageView.setImage(titleImage);
        } catch (FileNotFoundException fe) {
            statusLabel.setText("File not found!");
        }
    }


}
