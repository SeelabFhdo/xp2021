package v01.de.fhdo.puls.service.BookingService.v01.interfaces;

import v01.de.fhdo.puls.domain.v01.Booking.BlockchainDataParkBooking;
import v01.de.fhdo.puls.domain.v01.Booking.ParkBookingAggregate;
import v01.de.fhdo.puls.service.BookingService.v01.interfaces.gen.ParkBookingApiGen;

public class ParkBookingApi implements ParkBookingApiGen {

    @Override
    public void createParkBooking(ParkBookingAggregate parkBooking) {
        checkRequiredParametersOfCreateParkBooking(parkBooking);
    }

    private void checkRequiredParametersOfCreateParkBooking(ParkBookingAggregate parkBooking) {
        if (parkBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkBooking\" must not be null");
    }

    @Override
    public ParkBookingAggregate readParkBooking(Long bookingId) {
        checkRequiredParametersOfReadParkBooking(bookingId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadParkBooking(Long bookingId) {
        if (bookingId == null)
            throw new IllegalArgumentException("Required parameter \"bookingId\" must not be null");
    }

    @Override
    public ParkBookingAggregate readAllParkBookings() {
        /* FIXME If you safely want to implement this method, create an extension interface called 
ParkBookingApiExt in the same folder as this class file and run the code 
generator again. Otherwise, this file and all your changes to it will probably get overwritten 
the next time the code generator is executed. */
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BlockchainDataParkBooking readParkBookingBlockchainData(Long booking) {
        checkRequiredParametersOfReadParkBookingBlockchainData(booking);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadParkBookingBlockchainData(Long booking) {
        if (booking == null)
            throw new IllegalArgumentException("Required parameter \"booking\" must not be null");
    }

    @Override
    public ParkBookingAggregate readCurrentBookingsOfParkingSpace(Long parkingSpaceId) {
        checkRequiredParametersOfReadCurrentBookingsOfParkingSpace(parkingSpaceId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadCurrentBookingsOfParkingSpace(Long parkingSpaceId) {
        if (parkingSpaceId == null)
            throw new IllegalArgumentException("Required parameter \"parkingSpaceId\" must not be null");
    }

    @Override
    public ParkBookingAggregate readCanceledBookingsOfParkingSpace(Long parkingSpaceId) {
        checkRequiredParametersOfReadCanceledBookingsOfParkingSpace(parkingSpaceId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadCanceledBookingsOfParkingSpace(Long parkingSpaceId) {
        if (parkingSpaceId == null)
            throw new IllegalArgumentException("Required parameter \"parkingSpaceId\" must not be null");
    }

    @Override
    public void updateParkBooking(ParkBookingAggregate parkBooking) {
        checkRequiredParametersOfUpdateParkBooking(parkBooking);
    }

    private void checkRequiredParametersOfUpdateParkBooking(ParkBookingAggregate parkBooking) {
        if (parkBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkBooking\" must not be null");
    }

    @Override
    public void deletParkBooking(ParkBookingAggregate parkBooking) {
        checkRequiredParametersOfDeletParkBooking(parkBooking);
    }

    private void checkRequiredParametersOfDeletParkBooking(ParkBookingAggregate parkBooking) {
        if (parkBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkBooking\" must not be null");
    }
}
