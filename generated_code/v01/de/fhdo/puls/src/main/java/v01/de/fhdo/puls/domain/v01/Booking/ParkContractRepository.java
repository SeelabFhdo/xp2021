package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkContractRepositoryGen;

public class ParkContractRepository implements ParkContractRepositoryGen {

    public ParkContractRepository() {
    }

    private ParkContractAggregate parkContractAggregate;

    @Override
    public ParkContractAggregate getParkContractAggregate() {
        return parkContractAggregate;
    }

    @Override
    public void setParkContractAggregate(ParkContractAggregate parkContractAggregate) {
        this.parkContractAggregate = parkContractAggregate;
    }

    private String smartContractAddress;

    @Override
    public String getSmartContractAddress() {
        return smartContractAddress;
    }

    @Override
    public void setSmartContractAddress(String smartContractAddress) {
        this.smartContractAddress = smartContractAddress;
    }

    public ParkContractRepository(ParkContractAggregate parkContractAggregate, String smartContractAddress) {
        this.parkContractAggregate = parkContractAggregate;
        this.smartContractAddress = smartContractAddress;
    }
}
