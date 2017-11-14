public class ParkingSlot {

    String id;
    boolean availability;
    Car carParked;

    public ParkingSlot(String id) {
        this.id = id;
        this.availability = true;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String park(Car car) {
        this.carParked = car;
        this.availability = false;
        return id;
    }

    public boolean isSameLot(String slot) {
        return this.id.equals(slot);
    }

    public Car unPark() {
        this.availability = true;
        Car carParked = this.carParked;
        this.carParked = null;
        return carParked;
    }
}
