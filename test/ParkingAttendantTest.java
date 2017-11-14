import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingAttendantTest {

    @Test
    public void attendantShouldAbleToParkCarOnAnyAvailableSlot(){

        ParkingLot parkingLot = ParkingLotFactory.create(20);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingAttendant attendant = new ParkingAttendant(parkingLots);
        Car car = new Car();
        assertEquals("1",attendant.doPark(car));
    }

    @Test
    public void shouldThrowExceptionWhenParkingAttendantIsNotAbleToPark(){
        ParkingLot firstParkingLot = ParkingLotFactory.create(1);
        ParkingLot secondParkingLot = ParkingLotFactory.create(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);

        Car firstCar = new Car(1);
        Car secondCar = new Car(2);
        Car thirdCar = new Car(3);

        firstParkingLot.park(firstCar);
        secondParkingLot.park(secondCar);

        assertThrows(RuntimeException.class, ()->parkingAttendant.doPark(thirdCar),"");
    }

}
