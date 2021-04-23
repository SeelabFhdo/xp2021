package v01.de.fhdo.puls.domain.v01.Booking;

import java.util.Date;
import v01.de.fhdo.puls.domain.v01.Booking.gen.TimeSlotGen;

public class TimeSlot implements TimeSlotGen {

    public TimeSlot() {
    }

    private Date startOfBooking;

    @Override
    public Date getStartOfBooking() {
        return startOfBooking;
    }

    @Override
    public void setStartOfBooking(Date startOfBooking) {
        this.startOfBooking = startOfBooking;
    }

    private Date endOfBooking;

    @Override
    public Date getEndOfBooking() {
        return endOfBooking;
    }

    @Override
    public void setEndOfBooking(Date endOfBooking) {
        this.endOfBooking = endOfBooking;
    }

    public TimeSlot(Date startOfBooking, Date endOfBooking) {
        this.startOfBooking = startOfBooking;
        this.endOfBooking = endOfBooking;
    }
}
