package Hotelproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/*
1. Her skal man sjekke om bestemt hotel, sted og rom tilsvarer i csv filen. 
2. Deretter skal man velge om man vil book eller ikke basert på om den er tilgjengelig.
3. Muligheten til å avbestille. 
4. Redigere fil
5. Utløse unntak --> (påbegynt) må gjøres ved feil tilstand, eller input.

Muligendring: Implementere BookingID?
Status: Påbegynt
*/

public class Room {
    public static final List<String> HOTELCHAIN = List.of("Scandic", "Strawberry");
    public static final List<String> DESTINATION = List.of("Oslo", "Trondheim");
    public static final List<String> ROOMNUMBER = List.of("1", "2", "3", "4");

    private Scanner scanner; 
    private StringBuilder content;
    private File file; 
    //private boolean roomFound;


    public Room() throws IOException{
        this.file = new File("HotelRoom.csv");                      //Henter filen
        this.scanner = new Scanner(file);                                    //Henter info fra HotelRoom.csv, vha Scanner 
        this.content = new StringBuilder();                                  //Benytter Stringbuilder for senere anledning, når vi skal redigere filen. 
        //this.roomFound = false; 
        
    }

    public int getPrice(String chain, String destination, String roomNr) throws IOException {
        int price = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                String hotelchain = parts[0];
                String place = parts[1];
                String room = parts[2];
                boolean availability = Boolean.parseBoolean(parts[3]);
                int roomPrice = Integer.parseInt(parts[4]);

                if (hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equals(roomNr)) {
                    if (availability) {
                        price = roomPrice;
                    }
                    break;
                }
            }
        }
        return price;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public StringBuilder getContent(){
        return content;
    }

    public File getFile(){
        return file;
    }

    // public boolean getRoomFound(){
    //     return roomFound;
    // }

    //Hovedmetode for booking
    public void booking(String chain, String destination, String roomNr) throws IOException{
       StringBuilder content = checkAvailability(chain, destination, roomNr);
       reWrite(content);
    }
    
    //Hovedmetode for å avbestille booking
    public void cancelBooking(String chain, String destination, String roomNr) throws IOException{
        StringBuilder content = checkToCancelBooking(chain, destination, roomNr);
        reWrite(content);
    }

    // //metode for å spørre brukeren hvor mange gjester og lagre til variabel
    // public int guestAmount(int amountguests) {
    //     System.out.println("Please write the amount of guests from 1-5: "); 
    //     @SuppressWarnings("resource")
    //     Scanner scan1 = new Scanner(System.in);
    //     amountguests = Integer.parseInt(scan1.nextLine().trim());
    //     return amountguests;
    // }

    //metode for å regne ut total pris på reisen (ganger pris på valgt hotell/rom/sted ganget med antall gjester)
    // public void calculateTotalPrice(int price, int numGuests) throws IOException {
    //     int totalPrice = price * numGuests;
    //     System.out.println("Total price for booking: " + totalPrice + " kr.");
    //     reWrite(content); 
    // }

    /*Metode: Sjekke om spesifikk info om hotellet er tilgjengelig for booking*/
    public StringBuilder checkAvailability(String chain, String destination, String roomNr) throws IOException{    //Kaster fileNotFoundException for å benytte scanner
        while(scanner.hasNextLine()){                                           //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); //Endrer til newRoomListe til en boolean
            int price = Integer.parseInt(newRoomListe[4].trim());

            //Dersom hotelchian = chain, place = destination, room = roomNr. 
            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                System.out.println("Booking information:\n Choosen hotel: " + chain + "\n Destination: "+ destination + "\n Room nr: " + roomNr);
                availability = false; 
                System.out.println("This room is available.");             
                System.out.println("Your room has been successfully booked!");
            }
            //ny variabel, alle parameter join sammen med ; i midten, til en string. 
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), String.valueOf(price));
            content.append(updatedLine).append("\n");                       //content som er stringbuilder, legg updatedLine med \n inn.
        }
        //roomNotFound();                                                         //Dersom rommet ikke er funnet i HotelRoom.csv.                    
        //scanner.close();
        return content;
    }

    //Metode: Sjekke om spesifikk info om hotellet er sant, dermed avbestill. 
    public StringBuilder checkToCancelBooking(String chain, String destination, String roomNr) throws IOException{
        while(scanner.hasNextLine()){                                           
            String[] newRoomListe = scanner.nextLine().split(";");        

            String hotelchain = newRoomListe[0].trim();                        
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); 
            int price = Integer.parseInt(newRoomListe[4].trim());

            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                System.out.println("Booking Information: \n You choose to cancel booking for hotel " + chain + "\nat destination " + destination + "\nwith specific roomnr " + roomNr);
                availability = true;
                System.out.println("Your room has successfully been cancelled.\nWe hope to see you again!");
                // }else{
                //     System.out.println("There is nothing to cancel."); 
                
            }
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), String.valueOf(price));
            content.append(updatedLine).append("\n");
        }
        //roomNotFound();
        //scanner.close();
        return content;
    }


    //Metode (generelt): Kode som skriver om alt i csv. 
    public void reWrite(StringBuilder x) throws IOException{
        FileWriter writer = new FileWriter(file);           //Skriv i csv filen
        writer.write(x.toString());                         //skriv content til string
        writer.close(); 
    }

    public static void main(String[] args) throws IOException{
        //Room room1 = new Room();
        Room room2 = new Room();
        //Room room3 = new Room();
        //room2.booking("Scandic", "Oslo", "4");
        //room3.booking("Scandic", "Oslo", "2");
        //room1.booking("Scandic", "Oslo", "3");
        room2.cancelBooking("Scandic", "oslo", "4");

    }
}