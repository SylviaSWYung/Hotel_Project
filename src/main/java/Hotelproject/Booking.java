package Hotelproject;

import java.util.Date;
import java.util.Scanner;

/*Tester litt ut mens jeg går gjennom booking.java og room.java.
 * 
 * Status: Holder på
 */
public class Booking extends Room {
    private Room room;
    private Date startDate, endDate; 
    private int antallPersoner;
    private String name, phoneNumber, bookingID, sted, epost;
    private Betaling betaling; //legger til betaling
    

    public Booking(Date startDate, Date endDate, String sted, int antallPersoner, Betaling betaling){
        this.startDate = startDate;
        this.endDate = endDate;
        this.sted = sted;
        this.antallPersoner = antallPersoner;
        this.betaling = betaling; //legger til betaling
    }

    public String getName(){
        return this.name; 
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getEPost(){
        return this.epost;
    }

    public void deleteOrder(){

    }

    public void changeOrder(){

    }

    public int totalPrice(){
        return 1;
    }

    public static void main(String[] args) {
        //her må vi dermed legge til:
        //String betalingsmetode = paymentOpinion();
        //Betaling betaling = choosePaymentMethod(betalingsmetode);
    }

    //legger til gjennomforBetaling-metoden:
    public void gjennomforBetaling(double belop) {
        betaling.gjennomforBetaling(belop);
    }

    //metode for at brukeren kan velge betalingsmetode:
    public static String paymentOpinion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose payment method, type in 'cash' or 'card': ");
        String betalingsmetode = scanner.nextLine().trim().toLowerCase();
        scanner.close();
        return betalingsmetode;
    }

    //metode for å sjekke hvilken betalingsmetode brukeren velger:
    public static Betaling choosePaymentMethod(String betalingsmetode) {
        if (betalingsmetode.equals("cash")) {
            return new cashBetaling();
        } else if (betalingsmetode.equals("card")) {
            return new cardBetaling();
        } else {
            System.out.println("Incorrect input..."); //burde lage en while loop slik at brukeren kan prøve på nytt
            return null;
        }
    }
   

}
