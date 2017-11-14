import java.util.List;

public class ParkingAttendant {
    private List<ParkingLot> parkingLots;
    public ParkingAttendant(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
    }

    public String doPark(Car car) {
       return this.parkingLots.stream().filter(p->!p.isFull()).findFirst().get().park(car);
    }
}
