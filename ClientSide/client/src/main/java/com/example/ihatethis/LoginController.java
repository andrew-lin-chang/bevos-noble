package com.example.ihatethis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    public void userLoggedIn(ActionEvent event) throws IOException {
        String username = nameTextField.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("home.fxml")));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.displayWelcome(username);

        Stage window = (Stage) loginBtn.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
