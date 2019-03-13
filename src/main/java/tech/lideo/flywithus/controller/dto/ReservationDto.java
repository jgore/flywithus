package tech.lideo.flywithus.controller.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ReservationDto {

    @NotNull
    private Long flightId;
    @NotNull
    private String userLogin;
    @NotNull
    private BigDecimal price;

    public ReservationDto() {
        status = ReservationStatus.CREATED;
    }

    private boolean fastBriefing;
    private ReservationStatus status;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

}
