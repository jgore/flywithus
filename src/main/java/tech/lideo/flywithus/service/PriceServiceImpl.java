package tech.lideo.flywithus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Component
public class PriceServiceImpl implements PriceService {

    private String fastBriefPrice;

    private String discount;

    private Currency currency;

    public PriceServiceImpl(@Value("${flywithus.fastBrief.price}") String fastBriefPrice,
                            @Value("${flywithus.loggedUser.discount}") String discount,
                            @Value("${flywithus.currency}") String currency) {
        this.fastBriefPrice = fastBriefPrice;
        this.discount = discount;
        this.currency = Currency.getInstance(currency);
    }

    @Override
    public BigDecimal calculateReservation(UserDto userDto, FlightDto flightDto, ReservationDto reservationDto) {

        BigDecimal price = new BigDecimal(0);

        if (reservationDto.isFastBriefing()) {
            price = price.add(new BigDecimal(fastBriefPrice));
        }


        if (userDto != null) {
            BigDecimal finaldiscount = price.multiply(new BigDecimal(discount));
            price = price.subtract(finaldiscount);
        }

        price = price.multiply( BigDecimal.valueOf( reservationDto.getPassengersAmount()));


        return price.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }
}
