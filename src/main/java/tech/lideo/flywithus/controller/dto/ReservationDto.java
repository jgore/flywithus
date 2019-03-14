package tech.lideo.flywithus.controller.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class ReservationDto {

    private Long id;
    @NotNull
    private Long flightId;
    @NotNull
    private String userEmail;
    @NotNull
    private BigDecimal price;

    private UUID reservationSecretCode;
    private LocalDate created;

    public ReservationDto() {
        status = ReservationStatus.CREATED;
        created = LocalDate.now();
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
}
