package tech.lideo.flywithus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.service.ReservationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST)
    public ReservationDto create(@RequestBody ReservationDto reservationDto) {
        return reservationService.create(reservationDto);
    }

}
