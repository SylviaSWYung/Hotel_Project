package Hotelproject;

import java.util.Date;

public class Booking {
    private Date startDate; 
    private Date endDate; 
    private String sted; 
    private int antallPersoner;
    private String bookingID; 

    public Booking(Date startDate, Date endDate, String sted, int antallPersoner){
        this.startDate = startDate;
        this.endDate = endDate;
        this.sted = sted;
        this.antallPersoner = antallPersoner;
    }


}
