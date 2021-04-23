package v01.de.fhdo.puls.domain.v01.Booking;

import v01.de.fhdo.puls.domain.v01.Booking.gen.BookerGen;

public class Booker implements BookerGen {

    public Booker() {
    }

    private long bookerId;

    @Override
    public long getBookerId() {
        return bookerId;
    }

    @Override
    public void setBookerId(long bookerId) {
        this.bookerId = bookerId;
    }

    private String firstName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String lastName;

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Booker(long bookerId, String firstName, String lastName) {
        this.bookerId = bookerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
