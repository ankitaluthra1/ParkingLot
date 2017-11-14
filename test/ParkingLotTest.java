import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void shouldParkCar()
    {
        Car car = new Car();
        ParkingLot parkingLot = ParkingLotFactory.create(1);
        assertEquals("1",parkingLot.park(car));
    }

    @Test
    public void shouldNotAllowIfSlotIsFull(){
        Car car = new Car();
        ParkingLot parkingLot = ParkingLotFactory.create(1);
        parkingLot.park(car);
        NoParkingSpaceException noParkingSpaceException = assertThrows(NoParkingSpaceException.class, () -> parkingLot.park(car), "");
        assertEquals("No Space Available",noParkingSpaceException.getMessage());
    }

    @Test
    public void shouldUnParkCar()
    {
        ParkingLot parkingLot = ParkingLotFactory.create(1);
        Car car = new Car(1);
        String parkSpace = parkingLot.park(car);

        assertTrue(parkingLot.isFull());

        assertTrue(parkingLot.unpark(parkSpace));

        assertFalse(parkingLot.isFull());
    }

    @Test
    public void shouldNotifyParkingLotObserverWhenParkingLotIsFull(){
        Car car = new Car(1);

        DummyFullObserver dummyFullObserver = new DummyFullObserver();

        ParkingLot parkingLot = ParkingLotFactory.create(1);

        parkingLot.addObserver(dummyFullObserver);

        parkingLot.park(car);

        assertTrue(dummyFullObserver.isNotified());

    }

    @Test
    public void shouldNotifyWhenParkingLotBecameAvailable() {

        Car car = new Car(1);

        DummyAvailableObserver dummyAvailableObserver = new DummyAvailableObserver();

        ParkingLot parkingLot = ParkingLotFactory.create(1);

        parkingLot.park(car);

        parkingLot.addObserver(dummyAvailableObserver);

        parkingLot.unpark("1");

        assertTrue(dummyAvailableObserver.isNotified());

    }

    @Test
    public void shouldNotNotifyAvailablilityObserverIfParkingLotWasAlreadyAvailable() {

        Car car = new Car(1);

        DummyAvailableObserver dummyAvailableObserver = new DummyAvailableObserver();

        ParkingLot parkingLot = ParkingLotFactory.create(2);

        parkingLot.park(car);

        parkingLot.addObserver(dummyAvailableObserver);

        parkingLot.unpark("1");

        assertFalse(dummyAvailableObserver.isNotified());

    }

    class DummyAvailableObserver implements ParkingLotAvailabilityObserver{

        public boolean notified = false;

        @Override
        public void notifyAvailability() {
            this.notified = true;
        }

        public boolean isNotified() {
            return notified;
        }
    }

    class DummyFullObserver implements ParkingLotFullObserver {

        private boolean notified = false;

        @Override
        public void notifySlotIsFull() {
            notified = true;
        }

        public boolean isNotified(){
            return this.notified;
        }
    }


}
