package v01.de.fhdo.puls.domain.v01.Booking.gen;

import v01.de.fhdo.puls.domain.v01.Booking.ParkAndChargeBookingAggregate;

public interface ParkAndChargeBookingRepositoryGen {

    ParkAndChargeBookingAggregate getParkBookingAggregate();

    void setParkBookingAggregate(ParkAndChargeBookingAggregate parkBookingAggregate);

    long getBookingId();

    void setBookingId(long bookingId);
}
