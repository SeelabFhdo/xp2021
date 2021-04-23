package v01.de.fhdo.puls.domain.v01.Booking.gen;

import v01.de.fhdo.puls.domain.v01.Booking.ParkContractAggregate;

public interface ParkContractRepositoryGen {

    ParkContractAggregate getParkContractAggregate();

    void setParkContractAggregate(ParkContractAggregate parkContractAggregate);

    String getSmartContractAddress();

    void setSmartContractAddress(String smartContractAddress);
}
