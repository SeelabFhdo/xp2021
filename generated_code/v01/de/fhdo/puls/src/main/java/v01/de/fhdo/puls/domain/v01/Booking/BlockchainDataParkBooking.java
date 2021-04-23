package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.BlockchainDataParkBookingGen;

public class BlockchainDataParkBooking implements BlockchainDataParkBookingGen {

    public BlockchainDataParkBooking() {
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

    private long bookerId;

    @Override
    public long getBookerId() {
        return bookerId;
    }

    @Override
    public void setBookerId(long bookerId) {
        this.bookerId = bookerId;
    }

    private String parkingSpaceId;

    @Override
    public String getParkingSpaceId() {
        return parkingSpaceId;
    }

    @Override
    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    private String startOfBooking;

    @Override
    public String getStartOfBooking() {
        return startOfBooking;
    }

    @Override
    public void setStartOfBooking(String startOfBooking) {
        this.startOfBooking = startOfBooking;
    }

    private String endOfBooking;

    @Override
    public String getEndOfBooking() {
        return endOfBooking;
    }

    @Override
    public void setEndOfBooking(String endOfBooking) {
        this.endOfBooking = endOfBooking;
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

    public BlockchainDataParkBooking(long bookingId, long bookerId, String parkingSpaceId, String startOfBooking, String endOfBooking, float bookingPriceTotal) {
        this.bookingId = bookingId;
        this.bookerId = bookerId;
        this.parkingSpaceId = parkingSpaceId;
        this.startOfBooking = startOfBooking;
        this.endOfBooking = endOfBooking;
        this.bookingPriceTotal = bookingPriceTotal;
    }
}
