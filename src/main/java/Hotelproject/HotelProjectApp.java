package Hotelproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelProjectApp extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Hotelbooking app");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("App.fxml"))));
        primaryStage.show();
    }
    
}
