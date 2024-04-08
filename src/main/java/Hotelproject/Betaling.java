package Hotelproject;

import java.io.IOException;

public interface Betaling {

    static int calculateTotalPrice(int price, int guestInput) throws IOException {
        return price * guestInput;
    }

}