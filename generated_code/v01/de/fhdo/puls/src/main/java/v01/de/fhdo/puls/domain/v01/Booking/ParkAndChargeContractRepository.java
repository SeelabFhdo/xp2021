package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkAndChargeContractRepositoryGen;

public class ParkAndChargeContractRepository implements ParkAndChargeContractRepositoryGen {

    public ParkAndChargeContractRepository() {
    }

    private ParkAndChargeContractAggregate parkAndChargeContractAggregate;

    @Override
    public ParkAndChargeContractAggregate getParkAndChargeContractAggregate() {
        return parkAndChargeContractAggregate;
    }

    @Override
    public void setParkAndChargeContractAggregate(ParkAndChargeContractAggregate parkAndChargeContractAggregate) {
        this.parkAndChargeContractAggregate = parkAndChargeContractAggregate;
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

    public ParkAndChargeContractRepository(ParkAndChargeContractAggregate parkAndChargeContractAggregate, String smartContractAddress) {
        this.parkAndChargeContractAggregate = parkAndChargeContractAggregate;
        this.smartContractAddress = smartContractAddress;
    }
}
