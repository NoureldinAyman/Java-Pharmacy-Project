package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/com/example/project/Icons/appIcon.png"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(icon);
        stage.setTitle("Pharmacy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
