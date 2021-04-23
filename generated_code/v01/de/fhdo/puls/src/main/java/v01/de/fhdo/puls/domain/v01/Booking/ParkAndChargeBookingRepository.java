package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkAndChargeBookingRepositoryGen;

public class ParkAndChargeBookingRepository implements ParkAndChargeBookingRepositoryGen {

    public ParkAndChargeBookingRepository() {
    }

    private ParkAndChargeBookingAggregate parkBookingAggregate;

    @Override
    public ParkAndChargeBookingAggregate getParkBookingAggregate() {
        return parkBookingAggregate;
    }

    @Override
    public void setParkBookingAggregate(ParkAndChargeBookingAggregate parkBookingAggregate) {
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

    public ParkAndChargeBookingRepository(ParkAndChargeBookingAggregate parkBookingAggregate, long bookingId) {
        this.parkBookingAggregate = parkBookingAggregate;
        this.bookingId = bookingId;
    }
}
