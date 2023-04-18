package com.example.ihatethis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = FXMLLoader.load((getClass().getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Bevos & Noble");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            new Client().setUpNetworking();
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}