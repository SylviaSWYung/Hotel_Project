package Hotelproject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class RoomTest {
    private Room room; 

    @BeforeEach
    public void setUp() throws IOException{
        try{
            room = new Room();
        }catch(IOException e){
            fail("Exception occurred during setup: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Booke engang")
    public void testBasicBooking(){
        try{
            room.booking("Scandic", "Oslo", "3");
            assertTrue(room.isRoomBooked("Scandic", "Oslo", "3"));
        }catch(IOException e){
            fail("Booking failed: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Cancel previous booking")
    public void testCancelBooking(){
        try{
            room.cancelBooking("Scandic", "Oslo", "3");
            assertFalse(room.isRoomBooked("Scandic", "Oslo", "3"));
        }catch(IOException e){
            fail("Cancel booking failed: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Booke to ganger, og få en feil melding")
    public void testDoubleBooking() throws IOException{
        room.booking("Strawberry", "Oslo", "4");
        assertTrue(room.isRoomBooked("Strawberry", "Oslo", "4"));

        room.booking("Scandic", "Oslo", "3");
        fail("Double booking should not be allowed");
    }

    @Test
    @DisplayName("Booke, men skrive feil verdier og ingen verdier")
    public void testIncorrectBookingValues() throws IOException{
        assertThrows(IllegalArgumentException.class, () -> {
            room.booking("InvalidChain", "InvalidDestination", "InvalidRoomNr");
        });        
    }

    @Test
    @DisplayName("Cancel, to ganger men få en feilmelding")
    public void testDoubleCancel() throws IOException{
        room.booking("Strawberry", "Oslo", "3");
        room.cancelBooking("Strawberry", "Oslo", "3"); 

        room.cancelBooking("Strawberry", "Oslo", "3");
        assertFalse(room.isRoomBooked("Strawberry", "Oslo", "3"));
    }

    @Test
    @DisplayName("Betale to ganger, men få en feilmelding")
    public void testDoublePayment() throws IOException {
        room.booking("Strawberry", "Oslo", "3");

        room.handleVippsPayment();
        assertTrue(room.isVippsPaid());

        assertThrows(IOException.class, () -> room.handleVippsPayment());
        fail("Double payment should not be allowed.");
    }
}
