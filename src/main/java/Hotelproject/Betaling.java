package Hotelproject;

import java.io.IOException;

public interface Betaling {

    public void calculateTotalPrice(int price, int guestInput) {
        int totalPrice = price * guestInput;
        System.out.println("Total price for booking: " + totalPrice + " kr.");
    }

}