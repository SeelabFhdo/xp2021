package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkBookingAggregateGen;

public class ParkBookingAggregate implements ParkBookingAggregateGen {

    public ParkBookingAggregate() {
    }

    private long bookingId;

    @Override
    public long getBookingId() {
        return bookingId;
    }

    @Override
    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    private Booker booker;

    @Override
    public Booker getBooker() {
        return booker;
    }

    @Override
    public void setBooker(Booker booker) {
        this.booker = booker;
    }

    private ParkingSpace parkingSpace;

    @Override
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    @Override
    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    private TimeSlot bookinTimeSlot;

    @Override
    public TimeSlot getBookinTimeSlot() {
        return bookinTimeSlot;
    }

    @Override
    public void setBookinTimeSlot(TimeSlot bookinTimeSlot) {
        this.bookinTimeSlot = bookinTimeSlot;
    }

    private float bookingPriceTotal;

    @Override
    public float getBookingPriceTotal() {
        return bookingPriceTotal;
    }

    @Override
    public void setBookingPriceTotal(float bookingPriceTotal) {
        this.bookingPriceTotal = bookingPriceTotal;
    }

    private boolean isCanceled;

    @Override
    public boolean getIsCanceled() {
        return isCanceled;
    }

    @Override
    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public ParkBookingAggregate(long bookingId, Booker booker, ParkingSpace parkingSpace, TimeSlot bookinTimeSlot, float bookingPriceTotal, boolean isCanceled) {
        this.bookingId = bookingId;
        this.booker = booker;
        this.parkingSpace = parkingSpace;
        this.bookinTimeSlot = bookinTimeSlot;
        this.bookingPriceTotal = bookingPriceTotal;
        this.isCanceled = isCanceled;
    }
}
