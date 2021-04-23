package v01.de.fhdo.puls.domain.v01.Booking.gen;

public interface BlockchainDataParkAndChargeBookingGen {

    long getBookingId();

    void setBookingId(long bookingId);

    long getBookerId();

    void setBookerId(long bookerId);

    String getParkingSpaceId();

    void setParkingSpaceId(String parkingSpaceId);

    String getStartOfBooking();

    void setStartOfBooking(String startOfBooking);

    String getEndOfBooking();

    void setEndOfBooking(String endOfBooking);

    float getBookingPriceTotal();

    void setBookingPriceTotal(float bookingPriceTotal);

    int getVerifyCode();

    void setVerifyCode(int verifyCode);
}
