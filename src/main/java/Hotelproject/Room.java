package Hotelproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/* Status: under arbeid*/

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

    /*Først hente ut informasjon fra HotelRoom.java*/
    public StringBuilder checkAvailability(String chain, String destination, String roomNr) throws IOException{    //Kaster fileNotFoundException for å benytte scanner
        while(scanner.hasNextLine()){                                           //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); //Endrer til newRoomListe til en boolean
            String price = newRoomListe[4].trim();

            //Dersom hotelchian = chain, place = destination, room = roomNr. 
            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                roomFound = true;                                                //Funnet spesifikt room, true
                System.out.println("Booking information:\n Choosen hotel: " + chain + "\n Destination: "+ destination + "\n Room nr: " + roomNr);
                if (availability) {                                              //Dersom availability som er index 4 i string, er true. Fortsett
                    System.out.println("This room is available.");              //Rommet er tilgjengelig
                    availability = bookOpinion();                                  //Henter mer information fra bookOpinion()
                    if(!availability){                                           //Dersom den er false altså kunde ønsker å booke, gjør så dette
                        System.out.println("Your room has been successfully booked!");
                    }else{                                                        //Dersom kunde ikke ønsker å booke
                        System.out.println("You did not book this room.");
                    }
                }else{                                                          //Dersom rommet fra før er false. 
                    System.out.println("This room is unavailable. Try another room or date");
                }
            }
            //ny variabel, alle parameter join sammen med ; i midten, til en string. 
            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), price);
            content.append(updatedLine).append("\n");                       //content som er stringbuilder, legg updatedLine med \n inn.
        }
        roomNotFound();                                                         //Dersom rommet ikke er funnet i HotelRoom.csv.                    
        scanner.close();
        return content;
    }

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

    //Dersom roomet ikke er funnet. Print rommet ble ikke funnet.  
    public void roomNotFound(){
        if(!roomFound){                             
            System.out.println("Your room couldn't be found!");
        }
    }

    //Hoved metode for booking, og endring av fil.
    public void book(String chain, String destination, String roomNr) throws IOException{
       StringBuilder content = checkAvailability(chain, destination, roomNr);
       FileWriter writer = new FileWriter(file);           //Skriv i csv filen
       writer.write(content.toString());                   //skriv content til string
       writer.close();  
    }


    public static void main(String[] args) throws IOException{
        Room room1 = new Room();
        Room room2 = new Room();
        //Room room3 = new Room();
        room1.book("Strawberry", "Trondheim", "2");
        room2.book("Strawberry", "Trondheim", "2");
        //room3.book("Scandic", "Trondheim", "3");

    }

   
}
