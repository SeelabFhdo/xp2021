package v01.de.fhdo.puls.domain.v01.Booking.gen;

import v01.de.fhdo.puls.domain.v01.Booking.Booker;
import v01.de.fhdo.puls.domain.v01.Booking.ElectifiedParkingSpace;
import v01.de.fhdo.puls.domain.v01.Booking.TimeSlot;

public interface ParkAndChargeBookingAggregateGen {

    long getBookingId();

    void setBookingId(long bookingId);

    Booker getBookerId();

    void setBookerId(Booker bookerId);

    ElectifiedParkingSpace getElectifiedParkingSpace();

    void setElectifiedParkingSpace(ElectifiedParkingSpace electifiedParkingSpace);

    TimeSlot getBookingTimeSlot();

    void setBookingTimeSlot(TimeSlot bookingTimeSlot);

    float getBookingPriceTotal();

    void setBookingPriceTotal(float bookingPriceTotal);

    boolean getIsCanceled();

    void setIsCanceled(boolean isCanceled);

    int getVerifyCode();

    void setVerifyCode(int verifyCode);
}
