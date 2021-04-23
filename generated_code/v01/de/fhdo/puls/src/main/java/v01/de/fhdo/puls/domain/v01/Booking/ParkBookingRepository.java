package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkBookingRepositoryGen;

public class ParkBookingRepository implements ParkBookingRepositoryGen {

    public ParkBookingRepository() {
    }

    private ParkBookingAggregate parkBookingAggregate;

    @Override
    public ParkBookingAggregate getParkBookingAggregate() {
        return parkBookingAggregate;
    }

    @Override
    public void setParkBookingAggregate(ParkBookingAggregate parkBookingAggregate) {
        this.parkBookingAggregate = parkBookingAggregate;
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

    public ParkBookingRepository(ParkBookingAggregate parkBookingAggregate, long bookingId) {
        this.parkBookingAggregate = parkBookingAggregate;
        this.bookingId = bookingId;
    }
}
