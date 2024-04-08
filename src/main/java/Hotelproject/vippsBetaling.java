package Hotelproject;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class vippsBetaling implements Betaling {

    public void gjennomforbetalingvipps(int price, int guestInput, int totalPrice) throws IOException {
        try {
            totalPrice = Betaling.calculateTotalPrice(price, guestInput);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to pay the total amount of " + totalPrice + " by using Vipps?");
            alert.setContentText("You have successfully paid. Booking information is updated.");
            
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not pay the amount " + totalPrice + "by using Vipps");
            alert.setContentText("A problem occurred while trying to pay: " + e.getMessage());
            
            alert.showAndWait();
        }
    }
}