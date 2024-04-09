package Hotelproject;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class HotelProjectController {
    private Room room;
    private boolean isBooked = false;

    @FXML
    private ChoiceBox<String> hotelchainChoiceBox;

    @FXML
    private ChoiceBox<String> destinationChoiceBox;

    @FXML
    private ChoiceBox<String> roomNumberChoiceBox;

    @FXML
    private TextField guestTextField;

    @FXML
    private TextArea bookingInformationTextArea;

    @FXML   
    private void initialize() throws IOException{
        this.room = new Room();
        
        this.hotelchainChoiceBox.getItems().addAll(Room.HOTELCHAIN);
        
        this.destinationChoiceBox.getItems().addAll(Room.DESTINATION);

        this.roomNumberChoiceBox.getItems().addAll(Room.ROOMNUMBER);

        this.guestTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    
    }

    private void refreshBookingInfo() {
        String hotelchain = hotelchainChoiceBox.getValue();
        String destination = destinationChoiceBox.getValue();
        String roomNumber = roomNumberChoiceBox.getValue();
        int guestCount = Integer.parseInt(guestTextField.getText());
        LocalDate date = LocalDate.now();
    
        StringBuilder builder = new StringBuilder("Booking Information:\n\n");
        builder.append("Date: ").append(date).append("\n\n");
        builder.append("Hotel Chain: ").append(hotelchain).append("\n");
        builder.append("Destination: ").append(destination).append("\n");
        builder.append("Room Number: ").append(roomNumber).append("\n");
        builder.append("Amount of Guests: ").append(guestCount).append("\n");
        //builder.append("\n Total price: ").append(totalprice).append("\n");

        this.bookingInformationTextArea.setText(builder.toString());
    }

    @FXML
    private void handleBook() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();
        String guestInteger = this.guestTextField.getText();
        //Integer guestInput = Integer.parseInt(guestTextField.getText()); gjør den om til string istedet for å sjekke om det er noe der i det hele tatt, så int etterpå fir å sjekke om det er valid tall
        try{
            if(isBooked){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cannot book twice");
                alert.setContentText("You have already booked a room. You cannot book twice.");
                alert.showAndWait();
                return;
            }
        
            String wrong = validateBooking(chain, destination, roomNr, guestInteger); 
            if(wrong != null){
                throw new IOException(wrong);
            }
            //int guestCount = Integer.parseInt(guestInteger);
            this.room.booking(chain, destination, roomNr);
            this.refreshBookingInfo();
        
            // if(wrong != null){
            //     Alert alert = new Alert(Alert.AlertType.ERROR);
            //     alert.setTitle("Error");
            //     alert.setHeaderText("Couldn't book");
            //     alert.setContentText(wrong);
            //     alert.showAndWait();
            //     return;
            // }
            
    
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You have successfully booked!");
            alert.setContentText("Booking information is updated.");
            alert.showAndWait();

            isBooked = true;

        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Booking failed");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private String validateBooking(String chain, String destination, String roomNr, String guestInteger){
        if(chain == null){
            return "You have to choose a hotelchain";
        }else if(destination == null){
            return "You have to choose a destination";
        }else if(roomNr == null){
            return "You have to choose a room number";
        }else if(guestInteger.isEmpty()){
            return "You have to choose the amount of guest!";
        }else {
            try {
                int guestInput = Integer.parseInt(guestTextField.getText());
                if (guestInput <= 0) {
                    return "Invalid number of guests.";
                }
            } catch (NumberFormatException e) {
                return "Invalid number of guests";
            }
        }
        return null;
    }


    @FXML
    private void handleCancelBook() throws IOException{
        try{
            String chain = this.hotelchainChoiceBox.getValue();
            String destination = this.destinationChoiceBox.getValue();
            String roomNr = this.roomNumberChoiceBox.getValue();
            
            this.room.cancelBooking(chain, destination, roomNr);
            isBooked = false; 

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cancellation");
            alert.setHeaderText("You have successfully cancelled the booking!");
            alert.setContentText("You can now book another room! \nHave a nice day");
            alert.showAndWait(); 
        }catch(IOException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cancellation failed");
            alert.setContentText("An error occurred while canceling the booking: " +e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML 
    private void handleVippsPayment() throws IOException{

        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();
        String guestInteger = this.guestTextField.getText();

        System.out.println("Selected chain: " + chain);
        System.out.println("Selected destination: " + destination);
        System.out.println("Selected room number: " + roomNr);
        System.out.println("Guests: " + guestInteger);

        int price = room.getPrice(chain, destination, roomNr);

        System.out.println("Price per room: " + price);

        int guestInput = Integer.parseInt(guestInteger);
        int totalPrice = Betaling.calculateTotalPrice(price, guestInput);

        vippsBetaling payment = new vippsBetaling();
        payment.gjennomforbetalingvipps(price, guestInput, totalPrice);
    }
    
    @FXML
    private void handleCardPayment() throws IOException{

        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();
        String guestInteger = this.guestTextField.getText();

        System.out.println("Selected chain: " + chain);
        System.out.println("Selected destination: " + destination);
        System.out.println("Selected room number: " + roomNr);
        System.out.println("Guests: " + guestInteger);

        int price = room.getPrice(chain, destination, roomNr);

        System.out.println("Price per room: " + price);

        int guestInput = Integer.parseInt(guestInteger);
        int totalPrice = Betaling.calculateTotalPrice(price, guestInput);

        cardBetaling payment = new cardBetaling();
        payment.gjennomforbetalingcard(price, guestInput, totalPrice);
    }
    
}
