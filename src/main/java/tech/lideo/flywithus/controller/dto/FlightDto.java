package tech.lideo.flywithus.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class FlightDto {

    private Long id;
    private String departureCity;
    private LocalDate departureDate;
    private String arrivalCity;
    private LocalDate arrivalDate;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto flightDto = (FlightDto) o;
        return Objects.equals(getId(), flightDto.getId()) &&
                Objects.equals(getDepartureCity(), flightDto.getDepartureCity()) &&
                Objects.equals(getDepartureDate(), flightDto.getDepartureDate()) &&
                Objects.equals(getArrivalCity(), flightDto.getArrivalCity()) &&
                Objects.equals(getArrivalDate(), flightDto.getArrivalDate()) &&
                Objects.equals(getPrice(), flightDto.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartureCity(), getDepartureDate(), getArrivalCity(), getArrivalDate(), getPrice());
    }
}
