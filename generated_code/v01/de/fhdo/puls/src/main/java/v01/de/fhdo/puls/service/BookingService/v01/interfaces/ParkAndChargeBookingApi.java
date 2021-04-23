package v01.de.fhdo.puls.service.BookingService.v01.interfaces;

import v01.de.fhdo.puls.domain.v01.Booking.BlockchainDataParkAndChargeBooking;
import v01.de.fhdo.puls.domain.v01.Booking.ParkAndChargeBookingAggregate;
import v01.de.fhdo.puls.service.BookingService.v01.interfaces.gen.ParkAndChargeBookingApiGen;

public class ParkAndChargeBookingApi implements ParkAndChargeBookingApiGen {

    @Override
    public void createParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        checkRequiredParametersOfCreateParkAndChargeBooking(parkAndChargeBooking);
    }

    private void checkRequiredParametersOfCreateParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        if (parkAndChargeBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkAndChargeBooking\" must not be null");
    }

    @Override
    public ParkAndChargeBookingAggregate readParkAndChargeBooking(Long bookingId) {
        checkRequiredParametersOfReadParkAndChargeBooking(bookingId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadParkAndChargeBooking(Long bookingId) {
        if (bookingId == null)
            throw new IllegalArgumentException("Required parameter \"bookingId\" must not be null");
    }

    @Override
    public ParkAndChargeBookingAggregate readAllParkAndChargeBookings() {
        /* FIXME If you safely want to implement this method, create an extension interface called 
ParkAndChargeBookingApiExt in the same folder as this class file and run the code 
generator again. Otherwise, this file and all your changes to it will probably get overwritten 
the next time the code generator is executed. */
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BlockchainDataParkAndChargeBooking readParkAndParkBookingBlockchainData(Long booking) {
        checkRequiredParametersOfReadParkAndParkBookingBlockchainData(booking);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadParkAndParkBookingBlockchainData(Long booking) {
        if (booking == null)
            throw new IllegalArgumentException("Required parameter \"booking\" must not be null");
    }

    @Override
    public ParkAndChargeBookingAggregate readCurrentBookingsOfParkingSpace(Long parkingSpaceId) {
        checkRequiredParametersOfReadCurrentBookingsOfParkingSpace(parkingSpaceId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadCurrentBookingsOfParkingSpace(Long parkingSpaceId) {
        if (parkingSpaceId == null)
            throw new IllegalArgumentException("Required parameter \"parkingSpaceId\" must not be null");
    }

    @Override
    public ParkAndChargeBookingAggregate readCanceledBookingsOfParkingSpace(Long parkingSpaceId) {
        checkRequiredParametersOfReadCanceledBookingsOfParkingSpace(parkingSpaceId);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void checkRequiredParametersOfReadCanceledBookingsOfParkingSpace(Long parkingSpaceId) {
        if (parkingSpaceId == null)
            throw new IllegalArgumentException("Required parameter \"parkingSpaceId\" must not be null");
    }

    @Override
    public void updateParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        checkRequiredParametersOfUpdateParkAndChargeBooking(parkAndChargeBooking);
    }

    private void checkRequiredParametersOfUpdateParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        if (parkAndChargeBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkAndChargeBooking\" must not be null");
    }

    @Override
    public void deletParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        checkRequiredParametersOfDeletParkAndChargeBooking(parkAndChargeBooking);
    }

    private void checkRequiredParametersOfDeletParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking) {
        if (parkAndChargeBooking == null)
            throw new IllegalArgumentException("Required parameter \"parkAndChargeBooking\" must not be null");
    }
}
