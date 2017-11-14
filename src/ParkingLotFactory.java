import java.util.ArrayList;
import java.util.List;

public class ParkingLotFactory {

    public static ParkingLot create(int numberOfSlots) {
        List<ParkingSlot> slots = new ArrayList<>(numberOfSlots);

        for(int i=1;i<=numberOfSlots;i++) {
            slots.add(new ParkingSlot(i+""));
        }

        ParkingLot parkingLot = new ParkingLot(slots);

        return parkingLot;

    }


}
