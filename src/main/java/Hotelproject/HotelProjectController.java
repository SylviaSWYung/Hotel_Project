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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class HotelProjectController {
    private Room room;

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
    private TextArea bookingConfirmationTextArea;

    @FXML   
    private void initialize() throws IOException{
        this.room = new Room();
        
        this.hotelchainChoiceBox.getItems().addAll(Room.HOTELCHAIN);
        
        this.destinationChoiceBox.getItems().addAll(Room.DESTINATION);

        this.roomNumberChoiceBox.getItems().addAll(Room.ROOMNUMBER);

        this.guestTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        //this.refreshRoomInfo();
    
    }

    // private void refreshRoomInfo() {
    //     this.expenseTableView.getItems().setAll(this.manager.getExpenses());

    //     StringBuilder builder = new StringBuilder("Statistikk:\n");
    //     builder.append("Totalt: ").append(this.manager.getTotal()).append("\n");

    //     for (String category : Expense.CATEGORIES) {
    //         double total = this.manager.getTotalInCategory(category);
    //         builder.append(category).append(": ").append(total).append("\n");
    //     }

    //     this.bookingInformationTextArea.setText(builder.toString());
    // }

    @FXML
    private void handleBook() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();

        String wrong = null; 
        if(chain == null){
            wrong = "You have to choose a hotelchain";
        }else if(destination == null){
            wrong = "You have to choose a destination";
        }else if(roomNr == null){
            wrong = "You have to choose a room number";
        }

        if(wrong != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Couldn't book");
            alert.setContentText(wrong);
            alert.showAndWait();
            return;
        }
        this.room.booking(chain, destination, roomNr);
    }


    @FXML
    private void handleCancelBooking() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();

        this.room.cancelBooking(chain, destination, roomNr);
    }

    private void handleBook(){
        String hotel = hotelchainChoiceBox.getValue();
        String destination = destinationChoiceBox.getValue();
        String roomNumber = roomNumberChoiceBox.getValue();
        int guestCount = Integer.parseInt(guestTextField.getText());

        try {
            StringBuilder content = room.checkAvailability(hotel, destination, roomNumber);
            if (room.getRoomFound()) {
                room.calculateTotalPrice(guestCount, guestCount);
                bookingInformationTextArea.setText(content.toString());
            } else {
                bookingInformationTextArea.setText("Room was not found, no room was booked.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            bookingInformationTextArea.setText("An error occured: " + e.getMessage());
        }
        //this.refreshRoomInfo();
    }

    @FXML 
    private void handleVippsPayment(){
        // String totalPrice = getTotalPrice();

        // try {
        //     this.room.saveExpenses(new CSVBookingConfStorage(file));

        //     Alert alert = new Alert(AlertType.CONFIRMATION);
        //     alert.setTitle("Saved");
        //     alert.setHeaderText("You have successfully paid" + totalPrice + " kr by Vipps.");
        //     alert.setContentText("Changes have been saved");
            
        //     alert.showAndWait();
        // } catch (IOException e) {
        //     e.printStackTrace();

        //     Alert alert = new Alert(Alert.AlertType.ERROR);
        //     alert.setTitle("Error");
        //     alert.setHeaderText("Could not pay the amount " + totalPrice + " by Vipps.");
        //     alert.setContentText("An error occurred while trying to pay: " + e.getMessage());
        //     alert.showAndWait();
        // }

    }
    
    @FXML
    private void handleCardPayment(){

    }

    
}
