package v01.de.fhdo.puls.service.BookingService.v01.interfaces.gen;

import v01.de.fhdo.puls.domain.v01.Booking.BlockchainDataParkAndChargeBooking;
import v01.de.fhdo.puls.domain.v01.Booking.ParkAndChargeBookingAggregate;

public interface ParkAndChargeBookingApiGen {

    void createParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking);

    ParkAndChargeBookingAggregate readParkAndChargeBooking(Long bookingId);

    ParkAndChargeBookingAggregate readAllParkAndChargeBookings();

    BlockchainDataParkAndChargeBooking readParkAndParkBookingBlockchainData(Long booking);

    ParkAndChargeBookingAggregate readCurrentBookingsOfParkingSpace(Long parkingSpaceId);

    ParkAndChargeBookingAggregate readCanceledBookingsOfParkingSpace(Long parkingSpaceId);

    void updateParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking);

    void deletParkAndChargeBooking(ParkAndChargeBookingAggregate parkAndChargeBooking);
}
