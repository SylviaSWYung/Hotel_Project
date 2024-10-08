package Hotelproject;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class cardBetaling implements Betaling {


    public void gjennomforbetalingcard(int price, int guestInput, int totalPrice) throws IOException {
        try {
            totalPrice = Betaling.calculateTotalPrice(price, guestInput);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You have successfully paid the total amount of " + totalPrice + "kr by Card.");
            alert.setContentText("Booking information is updated.");
            
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not pay the amount " + totalPrice + "kr by Card");
            alert.setContentText("A problem occurred while trying to pay: " + e.getMessage());
            
            alert.showAndWait();
        }
    }
}