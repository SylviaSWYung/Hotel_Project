// package Hotelproject;

// // import java.io.File;
// import java.io.IOException;
// // import java.util.Scanner;
// import java.util.Scanner;

// /*Flere koder:
//  * 1. Avbestille --> endre fra false til true på HotelRoom.csv
//  * 
//  * Status: Påbegynt!
//  */

// public class RoomChange extends Room{

//     public RoomChange() throws IOException {
//         super();
//     }

//     public StringBuilder cancelBooking(String chain, String destination, String roomNr) throws IOException{
//         while(scanner.hasNextLine()){                                           
//             String[] newRoomListe = scanner.nextLine().split(";");        

//             String hotelchain = newRoomListe[0].trim();                        
//             String place = newRoomListe[1].trim();
//             String room = newRoomListe[2].trim();
//             Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); 
//             String price = newRoomListe[4].trim();

//             if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
//                 roomFound = true; 
//                 System.out.println("Booking Information: \n You choose to cancel booking for hotel " + chain + "\nat destination " + destination + "\n with specific roomnr " + roomNr);
//                 if (!availability) {                //Dersom availability fra csv er false --> indikerer at d r booket.
//                     availability = cancelOpinion();
//                     if (availability) {
//                         System.out.println("Your room has successfully been cancelled.\nWe hope to see you again!");
//                     }else{
//                         System.out.println("You did not cancel your room. \n We are exicted to meet you!");
//                     }
//                 }else{
//                     System.out.println("There is nothing to cancel. Have you registered the right booking ID?"); //Implementere booking id??
//                 }
//             }
//             String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), price);
//             content.append(updatedLine).append("\n");
//         }
//         roomNotFound();
//         scanner.close();
//         return content;
//     }

//     public boolean cancelOpinion(){
//         System.out.println("Do you wish to cancel your room? Yes/No: ");
//         @SuppressWarnings("resource")
//         Scanner scan = new Scanner(System.in);
//         String answer = scan.next().trim().toLowerCase();
//         if(answer.equalsIgnoreCase("yes")){
//             return true;                                                       
//         }else{
//             return false;
//         }
//     }
    
// }
