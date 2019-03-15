package tech.lideo.flywithus.controller.dto;

import java.util.Objects;

public class CreditCardDetailsDto {

    private String cardNumber ;
    private String name;
    private Integer cvv;
    private String expirationDate;

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

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDetailsDto that = (CreditCardDetailsDto) o;
        return Objects.equals(getCardNumber(), that.getCardNumber()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCvv(), that.getCvv()) &&
                Objects.equals(getExpirationDate(), that.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getName(), getCvv(), getExpirationDate());
    }
}
