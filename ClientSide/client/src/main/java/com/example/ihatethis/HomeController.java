package com.example.ihatethis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HomeController {

    @FXML
    private Text welcomeMessage = new Text();

    public void displayWelcome(String username) {
        welcomeMessage.setText("Welcome back, " + username);
    }

}
