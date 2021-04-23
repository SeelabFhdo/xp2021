package v01.de.fhdo.puls.service.BookingService.v01.interfaces.gen;

import v01.de.fhdo.puls.domain.v01.Booking.BlockchainDataParkBooking;
import v01.de.fhdo.puls.domain.v01.Booking.ParkBookingAggregate;

public interface ParkBookingApiGen {

    void createParkBooking(ParkBookingAggregate parkBooking);

    ParkBookingAggregate readParkBooking(Long bookingId);

    ParkBookingAggregate readAllParkBookings();

    BlockchainDataParkBooking readParkBookingBlockchainData(Long booking);

    ParkBookingAggregate readCurrentBookingsOfParkingSpace(Long parkingSpaceId);

    ParkBookingAggregate readCanceledBookingsOfParkingSpace(Long parkingSpaceId);

    void updateParkBooking(ParkBookingAggregate parkBooking);

    void deletParkBooking(ParkBookingAggregate parkBooking);
}
