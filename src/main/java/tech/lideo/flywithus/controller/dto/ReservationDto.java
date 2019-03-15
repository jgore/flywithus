package tech.lideo.flywithus.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class ReservationDto {

    @Null
    private Long id;
    @NotNull
    private Long flightId;
    @NotNull
    private String userEmail;
    @Null
    private BigDecimal price;
    @Min(1)
    private int passengersAmount;
    @Null
    private UUID reservationSecretCode;
    @Null
    private LocalDate created;

    public ReservationDto() {
    }


    private boolean fastBriefing;
    private ReservationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isFastBriefing() {
        return fastBriefing;
    }

    public void setFastBriefing(boolean fastBriefing) {
        this.fastBriefing = fastBriefing;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getReservationSecretCode() {
        return reservationSecretCode;
    }

    public void setReservationSecretCode(UUID reservationSecretCode) {
        this.reservationSecretCode = reservationSecretCode;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return getPassengersAmount() == that.getPassengersAmount() &&
                isFastBriefing() == that.isFastBriefing() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFlightId(), that.getFlightId()) &&
                Objects.equals(getUserEmail(), that.getUserEmail()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getReservationSecretCode(), that.getReservationSecretCode()) &&
                Objects.equals(getCreated(), that.getCreated()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFlightId(), getUserEmail(), getPrice(), getPassengersAmount(), getReservationSecretCode(), getCreated(), isFastBriefing(), getStatus());
    }
}
