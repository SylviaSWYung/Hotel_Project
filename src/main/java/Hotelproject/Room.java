package Hotelproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private Scanner scanner; 
    private StringBuilder content;
    private File file; 
    private boolean roomFound;


    public Room() throws IOException{
        this.file = new File("HotelRoom.csv");                      //Henter filen
        this.scanner = new Scanner(file);                                    //Henter info fra HotelRoom.csv, vha Scanner 
        this.content = new StringBuilder();                                  //Benytter Stringbuilder for senere anledning, når vi skal redigere filen. 
        this.roomFound = false; 
    }

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
                roomFound = true;                                                //Funnet spesifikt room, true
                System.out.println("Booking information:\n Choosen hotel: " + chain + "\n Destination: "+ destination + "\n Room nr: " + roomNr);
                if (availability) {                                              //Dersom availability som er index 4 i string, er true. Fortsett
                    System.out.println("This room is available.");              //Rommet er tilgjengelig
                    availability = bookOpinion();                                  //Henter mer information fra bookOpinion()
                    if(!availability){    
                        int numGuests = amountOfGuests();
                        calculateTotalPrice(price, numGuests);                                       //Dersom den er false altså kunde ønsker å booke, gjør så dette
                        System.out.println("Your room has been successfully booked!");
                    }else{                                                        //Dersom kunde ikke ønsker å booke
                        System.out.println("You did not book this room.");
                    }
                }else{                                                          //Dersom rommet fra før er false. 
                    System.out.println("This room is unavailable. Try another room or date");
                }
            }
            //ny variabel, alle parameter join sammen med ; i midten, til en string. 
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), String.valueOf(price));
            content.append(updatedLine).append("\n");                       //content som er stringbuilder, legg updatedLine med \n inn.
        }
        roomNotFound();                                                         //Dersom rommet ikke er funnet i HotelRoom.csv.                    
        scanner.close();
        return content;
    }

    //Metode: Implementeres i checkAvailability(). Muligheten til å svare om man ønsker å booke rommet eller ikke.
    public boolean bookOpinion(){
        System.out.println("Do you wish to book the room? Yes/No: ");
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String answer = scan.next().trim().toLowerCase();
        if(answer.equalsIgnoreCase("yes")){
            return false;                                                       //Dersom de ønsker å booke, vil tilgjengelig endres til false for neste mann.
        }else{
            return true;
        }
    }

    //metode som ber brukeren om antall gjester og lagrer det til en variabel for senere bruk
    public int amountOfGuests() {
        System.out.println("Please write the amount of guests from 1-5: "); 
        @SuppressWarnings("resource")
        Scanner scan1 = new Scanner(System.in);
        int guestamount = Integer.parseInt(scan1.nextLine().trim());
        return guestamount;
    }

    //metode for regning av total pris for turen (ganger pris på hotellrom med antall gjester lagret i variabelen over)
    public void calculateTotalPrice(int price, int numGuests) {
        int totalPrice = price * numGuests;
        System.out.println("Total price for booking: " + totalPrice + " kr.");
    }

    //Metode: Sjekke om spesifikk info om hotellet er sant, dermed avbestill. 
    public StringBuilder checkToCancelBooking(String chain, String destination, String roomNr) throws IOException{
        while(scanner.hasNextLine()){                                           
            String[] newRoomListe = scanner.nextLine().split(";");        

            String hotelchain = newRoomListe[0].trim();                        
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); 
            String price = newRoomListe[4].trim();

            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                roomFound = true; 
                System.out.println("Booking Information: \n You choose to cancel booking for hotel " + chain + "\nat destination " + destination + "\nwith specific roomnr " + roomNr);
                if (!availability) {                //Dersom availability fra csv er false --> indikerer at d r booket.
                    availability = cancelOpinion();
                    if (availability) {
                        System.out.println("Your room has successfully been cancelled.\nWe hope to see you again!");
                    }else{
                        System.out.println("You did not cancel your room. \n We are exicted to meet you!");
                    }
                }else{
                    System.out.println("There is nothing to cancel. Have you registered the right booking ID?"); //Implementere booking id??
                }
            }
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), price);
            content.append(updatedLine).append("\n");
        }
        roomNotFound();
        scanner.close();
        return content;
    }

    //Metode: Implementeres i checkToCancelBooking(). Muligheten til å svare om man ønsker å avbestille rommet eller ikke. 
    public boolean cancelOpinion(){
        System.out.println("Do you wish to cancel your room? Yes/No: ");
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String answer = scan.next().trim().toLowerCase();
        if(answer.equalsIgnoreCase("yes")){
            return true;                                                       
        }else{
            return false;
        }
    }
 
    //Metode (generelt): Dersom roomet ikke er funnet. Print rommet ble ikke funnet.  
    public void roomNotFound(){
        if(!roomFound){                             
            System.out.println("Your room couldn't be found!");
        }
    }

    //Metode (generelt): Kode som skriver om alt i csv. 
    public void reWrite(StringBuilder x) throws IOException{
        FileWriter writer = new FileWriter(file);           //Skriv i csv filen
        writer.write(x.toString());                         //skriv content til string
        writer.close();  
    }
    public static void main(String[] args) throws IOException{
        Room room1 = new Room();
        //Room room2 = new Room();
        //Room room3 = new Room();
        room1.booking("Strawberry", "Trondheim", "2");
        //room2.booking("Strawberry", "Trondheim", "2");
        //room3.booking("Scandic", "Trondheim", "3");
        //room1.cancelBooking("strawberry", "trondheim", "2");

    }

   
}
