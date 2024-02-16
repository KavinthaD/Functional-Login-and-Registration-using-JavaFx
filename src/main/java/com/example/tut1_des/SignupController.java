package com.example.tut1_des;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private ComboBox<String> ageChoose;

    @FXML
    private ComboBox<String> genderChoose;

    @FXML
    private ComboBox<String> civilChoose;

    private Stage signUpStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ComboBox content
        initializeComboBoxContent();
    }

    public void setSignUpStage(Stage signUpStage) {
        this.signUpStage = signUpStage;
    }

    private void initializeComboBoxContent() {
        // Initialize age choices
        for (int i = 1; i <= 150; i++) {
            ageChoose.getItems().add(Integer.toString(i)); // Example age choices

            // Initialize gender choices
            genderChoose.getItems().addAll("Male", "Female", "Other"); // Example gender choices

            // Initialize civil status choices
            civilChoose.getItems().addAll("Single", "Married", "Divorced", "Widowed"); // Example civil status choices
        }
    }

    private boolean isUsernameUnique(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_inputs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String existingUsername = line.substring("Username: ".length()).trim();
                    if (existingUsername.equals(username)) {
                        return false; // Username already exists
                    }
                }
            }
        }
        return true; // Username is unique
    }
    @FXML
    private void submitButtonClick() throws IOException {
        System.out.println("submit button clicked.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent submitRoot = loader.load();
        Scene scene = new Scene(submitRoot);

        try (FileWriter writer = new FileWriter("user_inputs.txt",true)) {
            writer.write("First Name: " + firstNameTextField.getText() + "\n");
            writer.write("Last Name: " + lastNameTextField.getText() + "\n");
            writer.write("Age: " + ageChoose.getValue() + "\n");
            writer.write("Birthday: " + birthdayDatePicker.getValue() + "\n");
            writer.write("Gender: " + genderChoose.getValue() + "\n");
            writer.write("Civil Status: " + civilChoose.getValue() + "\n");
            writer.write("Country: " + countryTextField.getText() + "\n");
            writer.write("Email: " + emailTextField.getText() + "\n");
            writer.write("Phone Number: " + phoneNumberTextField.getText() + "\n");
            writer.write("Username: " + usernameTextField.getText() + "\n");
            writer.write("Password: " + passwordTextField.getText() + "\n");
            // You can write additional fields if needed

            String username = usernameTextField.getText();

            if (!isUsernameUnique(username)) {
                // Username already exists, handle accordingly (show message, etc.)
                System.out.println("Username already exists!");
                return; // Exit method without appending data
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        signUpStage.close();

    }

    @FXML
    private void clearButtonClick() throws IOException {
        System.out.println("clear button clicked.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent clearRoot = loader.load();
        Scene scene = new Scene(clearRoot);

        firstNameTextField.clear();
        lastNameTextField.clear();
        ageChoose.getSelectionModel().clearSelection();
        birthdayDatePicker.getEditor().clear();
        genderChoose.getSelectionModel().clearSelection();
        civilChoose.getSelectionModel().clearSelection();
        countryTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
    }
}
