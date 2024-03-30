package Hotelproject;

import java.util.Date;

/*Tester litt ut mens jeg går gjennom booking.java og room.java.
 * 
 * Status: Holder på
 */
public class Booking extends Room {
    private Room room;
    private Date startDate, endDate; 
    private int antallPersoner;
    private String name, phoneNumber, bookingID, sted, epost;
    

    public Booking(Date startDate, Date endDate, String sted, int antallPersoner){
        this.startDate = startDate;
        this.endDate = endDate;
        this.sted = sted;
        this.antallPersoner = antallPersoner;
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
        
    }


}
