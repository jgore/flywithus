package tech.lideo.flywithus.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.lideo.flywithus.controller.dto.ReservationDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @RequestMapping(method = RequestMethod.POST)
    public ReservationDto create( @RequestBody ReservationDto reservationDto) {
       return null;
    }
}
