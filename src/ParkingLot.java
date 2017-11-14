import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

    private List<ParkingSlot> slots;
    private List<ParkingLotFullObserver> fullObservers;
    private List<ParkingLotAvailabilityObserver> availableObservers;

    public ParkingLot(List<ParkingSlot> slots) {
        this.slots = slots;
        this.fullObservers = new ArrayList<>();
        this.availableObservers = new ArrayList<>();
    }

    public String park(Car car) {
        if(isFull())
            throw new NoParkingSpaceException();
        Optional<ParkingSlot> first = slots.stream().filter(s -> s.isAvailable()).findFirst();
        String parkingSpace = first.get().park(car);

        if(isFull())
            this.fullObservers.stream().forEach(o->o.notifySlotIsFull());

        return parkingSpace;

    }

    public boolean unpark(String slot) {
        boolean isFull = isFull();

        ParkingSlot parkedSpot = slots.stream().filter(s -> s.isSameLot(slot)).findFirst().get();
        boolean unParked = parkedSpot.unPark();

        if(isFull)
            this.availableObservers.stream().forEach(o->o.notifyAvailability());

        return unParked;

    }

    public boolean isFull(){
        return !slots.stream().filter(s -> s.isAvailable()).findAny().isPresent();
    }

    public void addObserver(ParkingLotFullObserver parkingLotFullObserver) {
        fullObservers.add(parkingLotFullObserver);
    }

    public void addObserver(ParkingLotAvailabilityObserver parkingLotAvailabilityObserver) {
        availableObservers.add(parkingLotAvailabilityObserver);
    }
}
