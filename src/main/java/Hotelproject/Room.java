package Hotelproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Room {
    public static final List<String> HOTELCHAIN = List.of("Scandic", "Strawberry");
    public static final List<String> DESTINATION = List.of("Oslo", "Trondheim");
    public static final List<String> ROOMNUMBER = List.of("1", "2", "3", "4");

    private Scanner scanner; 
    private File file; 
    private boolean isItBooked;
    private boolean isVippsPaid = false;
    private boolean isCardPaid = false;


    public Room() throws IOException{
        this.file = new File("src/main/java/Hotelproject/HotelRoom.csv");                      
        this.isItBooked = false;
    }

    public boolean isVippsPaid() {
        return isVippsPaid;
    }

    public boolean isCardPaid() {
        return isCardPaid;
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
                    if (!availability) {
                        price = roomPrice;
                    }
                    break;
                }
            }
        }
        return price;
    }

    public File getFile(){
        return file;
    }

    public boolean getIsItBooked(){
        return isItBooked;
    }

    //Hovedmetode for booking
    public void booking(String chain, String destination, String roomNr) throws IOException{
        if(!HOTELCHAIN.contains(chain)){
            throw new IllegalArgumentException("The provided hotel chain does not exist.");
        }
        if(!DESTINATION.contains(destination)){
            throw new IllegalArgumentException("The provided destintaion does not exist.");
        }
        if(!ROOMNUMBER.contains(roomNr)){
            throw new IllegalArgumentException("The provided room number does not exist.");
        }
        if(isItBooked){
            throw new IllegalStateException("Another room is already booked. Cancel it before booking another room.");
        }
        if(!isRoomBooked(chain, destination, roomNr)){
            throw new IOException("Room is already booked");
        }
        StringBuilder content = checkAvailability(chain, destination, roomNr);
        reWrite(content);
        isItBooked = true;
    }
    
    //Hovedmetode for å avbestille booking
    public void cancelBooking(String chain, String destination, String roomNr) throws IOException{
        if(!HOTELCHAIN.contains(chain)){
            throw new IllegalArgumentException("The provided hotel chain does not exist.");
        }
        if(!DESTINATION.contains(destination)){
            throw new IllegalArgumentException("The provided destintaion does not exist.");
        }
        if(!ROOMNUMBER.contains(roomNr)){
            throw new IllegalArgumentException("The provided room number does not exist.");
        }
        if(isRoomBooked(chain, destination, roomNr)){
            throw new IOException("Room is not booked");
        }
        StringBuilder content = checkToCancelBooking(chain, destination, roomNr);
        reWrite(content);
        isItBooked = false;
        isVippsPaid = false;
        isCardPaid = false;

    }

    /*Metode: Sjekke om spesifikk info om hotellet er tilgjengelig for booking*/
    public StringBuilder checkAvailability(String chain, String destination, String roomNr) throws IOException{   
        this.scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while(scanner.hasNextLine()){                                           
            String[] newRoomListe = scanner.nextLine().split(";");       

            String hotelchain = newRoomListe[0].trim();                         
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); 
            int price = Integer.parseInt(newRoomListe[4].trim());

            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                if(availability){
                    System.out.println("Booking information:\n Choosen hotel: " + chain + "\n Destination: "+ destination + "\n Room nr: " + roomNr);
                    availability = false; 
                    System.out.println("This room is available.");             
                    System.out.println("Your room has been successfully booked!"); 
                }
            }
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), String.valueOf(price));
            content.append(updatedLine).append("\n");                       
        }
        scanner.close();
        return content;
    }

    //Metode: Sjekke om spesifikk info om hotellet er sant, dermed avbestill. 
    public StringBuilder checkToCancelBooking(String chain, String destination, String roomNr) throws IOException{
        this.scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();
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
            }
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), String.valueOf(price));
            content.append(updatedLine).append("\n");
        }
        scanner.close();
        return content;
    }


    //Metode (generelt): Kode som skriver om alt i csv. 
    public void reWrite(StringBuilder x) throws IOException{
        FileWriter writer = new FileWriter(file);           
        writer.write(x.toString());                        
        writer.close(); 
    }

    //Metode: Sjekke om rommet er booket fra før.
    public boolean isRoomBooked(String chain, String destination, String roomNr) throws FileNotFoundException{
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String [] parts = scanner.nextLine().split(";");
                String hotelchain = parts[0];
                String place = parts[1];
                String room = parts[2];
                boolean availability = Boolean.parseBoolean(parts[3]);
                if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                    return availability;
                }
            }
        }
        return false;
    }

    //Metode: sjekke om vipps har blitt betalt fra før
    public void handleVippsPayment() throws IOException {
        if (isVippsPaid) {
            throw new IOException("You have already paid for the booking.");
        }
        isVippsPaid = true;
    }

    //Metode: Sjekke om card har blitt betalt fra før
    public void handleCardPayment() throws IOException {
        if (isCardPaid) {
            throw new IOException("You have already paid for the booking.");
        }
        isCardPaid = true;
    }
}