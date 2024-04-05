package Hotelproject;

public class cashBetaling implements Betaling {

    @Override
    public void gjennomforBetaling(double belop) {
        System.out.println("Payment on " + belop + " kr was successfully paid by cash.");
    }
}