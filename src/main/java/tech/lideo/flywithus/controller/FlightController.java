package tech.lideo.flywithus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.service.FlightService;

import java.util.List;


@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightDto> getAll() {
        return flightService.getAll();
    }

}
