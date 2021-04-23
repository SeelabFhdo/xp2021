package v01.de.fhdo.puls.domain.v01.Booking.gen;

import v01.de.fhdo.puls.domain.v01.Booking.Booker;
import v01.de.fhdo.puls.domain.v01.Booking.ParkingSpace;
import v01.de.fhdo.puls.domain.v01.Booking.TimeSlot;

public interface ParkBookingAggregateGen {

    long getBookingId();

    void setBookingId(long bookingId);

    Booker getBooker();

    void setBooker(Booker booker);

    ParkingSpace getParkingSpace();

    void setParkingSpace(ParkingSpace parkingSpace);

    TimeSlot getBookinTimeSlot();

    void setBookinTimeSlot(TimeSlot bookinTimeSlot);

    float getBookingPriceTotal();

    void setBookingPriceTotal(float bookingPriceTotal);

    boolean getIsCanceled();

    void setIsCanceled(boolean isCanceled);
}
