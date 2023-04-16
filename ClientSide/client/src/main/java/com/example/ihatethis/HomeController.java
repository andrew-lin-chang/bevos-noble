package com.example.ihatethis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private MenuBar navBar;
    @FXML
    private MenuItem logout;
    @FXML
    private Text welcomeMessage = new Text();

    public void displayWelcome(String username) {
        welcomeMessage.setText("Welcome back, " + username);
    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("login.fxml")));
        Parent root = loader.load();
        Stage window = (Stage) navBar.getScene().getWindow();
        window.setScene(new Scene(root));
    }



}
