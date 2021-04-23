package v01.de.fhdo.puls.domain.v01.Booking.gen;

import v01.de.fhdo.puls.domain.v01.Booking.ParkBookingAggregate;

public interface ParkBookingRepositoryGen {

    ParkBookingAggregate getParkBookingAggregate();

    void setParkBookingAggregate(ParkBookingAggregate parkBookingAggregate);

    long getBookingId();

    void setBookingId(long bookingId);
}
