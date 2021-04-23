package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.ElectifiedParkingSpaceGen;

public class ElectifiedParkingSpace implements ElectifiedParkingSpaceGen {

    public ElectifiedParkingSpace() {
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

    private String city;

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    private int postcode;

    @Override
    public int getPostcode() {
        return postcode;
    }

    @Override
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    private String street;

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    private String streetNumber;

    @Override
    public String getStreetNumber() {
        return streetNumber;
    }

    @Override
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    private float bookingPricePerHour;

    @Override
    public float getBookingPricePerHour() {
        return bookingPricePerHour;
    }

    @Override
    public void setBookingPricePerHour(float bookingPricePerHour) {
        this.bookingPricePerHour = bookingPricePerHour;
    }

    private float chargingPricePerKwh;

    @Override
    public float getChargingPricePerKwh() {
        return chargingPricePerKwh;
    }

    @Override
    public void setChargingPricePerKwh(float chargingPricePerKwh) {
        this.chargingPricePerKwh = chargingPricePerKwh;
    }

    public ElectifiedParkingSpace(String parkingSpaceId, String city, int postcode, String street, String streetNumber, float bookingPricePerHour, float chargingPricePerKwh) {
        this.parkingSpaceId = parkingSpaceId;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.bookingPricePerHour = bookingPricePerHour;
        this.chargingPricePerKwh = chargingPricePerKwh;
    }
}
