public class ParkingSlot {

    String id;
    boolean availability;

    public ParkingSlot(String id) {
        this.id = id;
        this.availability = true;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String park(Car car) {
        this.availability = false;
        return id;
    }

    public boolean isSameLot(String slot) {
        return this.id.equals(slot);
    }

    public boolean unPark() {
        this.availability = true;
        return availability;
    }
}
