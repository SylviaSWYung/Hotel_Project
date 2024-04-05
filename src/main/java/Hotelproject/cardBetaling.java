package Hotelproject;

public class cardBetaling implements Betaling {

    @Override
    public void gjennomforBetaling(double belop) {
        System.out.println("Payment on " + belop + " kr was successfully paid by card.");
    }
}