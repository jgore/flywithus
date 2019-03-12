package tech.lideo.flywithus.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FlightDto {

    private Long userId;
    private Date departure;
    private Date arrival;
    private int passengersAmount;
    private BigDecimal price;

    private boolean fastBriefing;
    private FlightStatus status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long FlightId) {
        this.userId = FlightId;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public boolean isFastBriefing() {
        return fastBriefing;
    }

    public void setFastBriefing(boolean fastBriefing) {
        this.fastBriefing = fastBriefing;
    }



}
