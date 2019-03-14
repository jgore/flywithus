package tech.lideo.flywithus.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCardDetailsDto {

    private String cardNumber ;
    private String name;
    private String address;
    private String city;
    private Integer cvv;
    private LocalDate expirationDate;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDetailsDto that = (CreditCardDetailsDto) o;
        return Objects.equals(getCardNumber(), that.getCardNumber()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getCvv(), that.getCvv()) &&
                Objects.equals(getExpirationDate(), that.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getName(), getAddress(), getCity(), getCvv(), getExpirationDate());
    }
}
