package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ParkContractAggregateGen;

public class ParkContractAggregate implements ParkContractAggregateGen {

    public ParkContractAggregate() {
    }

    private long smartContractId;

    @Override
    public long getSmartContractId() {
        return smartContractId;
    }

    @Override
    public void setSmartContractId(long smartContractId) {
        this.smartContractId = smartContractId;
    }

    private String contractAddress;

    @Override
    public String getContractAddress() {
        return contractAddress;
    }

    @Override
    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public ParkContractAggregate(long smartContractId, String contractAddress) {
        this.smartContractId = smartContractId;
        this.contractAddress = contractAddress;
    }
}
