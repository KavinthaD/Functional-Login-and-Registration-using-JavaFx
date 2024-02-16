package com.example.tut1_des;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoginController {

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private void SignInButtonClick() throws IOException {
        System.out.println("sign in button clicked.");




        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (validateCredentials(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSuccess.fxml"));
            Parent loginSuccessRoot = loader.load();
            Scene scene = new Scene(loginSuccessRoot);

            Stage stage = new Stage();
            stage.setTitle("Login Success");
            stage.setScene(scene);
            stage.show();
            System.out.println("Login successful!");
            // Proceed to the next scene or perform other actions
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFail.fxml"));
            Parent loginSuccessRoot = loader.load();
            Scene scene = new Scene(loginSuccessRoot);

            Stage stage = new Stage();
            stage.setTitle("Login Failed");
            stage.setScene(scene);
            stage.show();
            System.out.println("Invalid username or password.");
            //Show an error message or perform other actions
        }
    }
    @FXML
    private void SignUpButtonClick() throws IOException {
        System.out.println("sign up button clicked.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent signupRoot = loader.load();
        Scene scene = new Scene(signupRoot);

        Stage signUpStage = new Stage();
        signUpStage.setTitle("sign up page");
        signUpStage.setScene(scene);
        signUpStage.show();

        com.example.tut1_des.SignupController otherController = loader.getController();
        otherController.setSignUpStage(signUpStage);
    }
    private boolean validateCredentials(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_inputs.txt"))) {
            String line;
            String existingPassword = null;
            String existingUsername = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    existingUsername = line.substring("Username: ".length()).trim();
                } else if (line.startsWith("Password: ")) {
                    existingPassword = line.substring("Password: ".length()).trim();
                }
            }
            assert existingPassword != null;
            if (existingPassword.equals(password)) {
                assert existingUsername != null;
                if (existingUsername.equals(username)) {
                    return true; // match with existing username


                }
            }
        }
        return false; // Credentials not found or error occurred
    }
}