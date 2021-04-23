package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkAndChargeBookingAggregateGen;

public class ParkAndChargeBookingAggregate implements ParkAndChargeBookingAggregateGen {

    public ParkAndChargeBookingAggregate() {
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

    private Booker bookerId;

    @Override
    public Booker getBookerId() {
        return bookerId;
    }

    @Override
    public void setBookerId(Booker bookerId) {
        this.bookerId = bookerId;
    }

    private ElectifiedParkingSpace electifiedParkingSpace;

    @Override
    public ElectifiedParkingSpace getElectifiedParkingSpace() {
        return electifiedParkingSpace;
    }

    @Override
    public void setElectifiedParkingSpace(ElectifiedParkingSpace electifiedParkingSpace) {
        this.electifiedParkingSpace = electifiedParkingSpace;
    }

    private TimeSlot bookingTimeSlot;

    @Override
    public TimeSlot getBookingTimeSlot() {
        return bookingTimeSlot;
    }

    @Override
    public void setBookingTimeSlot(TimeSlot bookingTimeSlot) {
        this.bookingTimeSlot = bookingTimeSlot;
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

    private int verifyCode;

    @Override
    public int getVerifyCode() {
        return verifyCode;
    }

    @Override
    public void setVerifyCode(int verifyCode) {
        this.verifyCode = verifyCode;
    }

    public ParkAndChargeBookingAggregate(long bookingId, Booker bookerId, ElectifiedParkingSpace electifiedParkingSpace, TimeSlot bookingTimeSlot, float bookingPriceTotal, boolean isCanceled, int verifyCode) {
        this.bookingId = bookingId;
        this.bookerId = bookerId;
        this.electifiedParkingSpace = electifiedParkingSpace;
        this.bookingTimeSlot = bookingTimeSlot;
        this.bookingPriceTotal = bookingPriceTotal;
        this.isCanceled = isCanceled;
        this.verifyCode = verifyCode;
    }
}
