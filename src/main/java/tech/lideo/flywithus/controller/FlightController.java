package tech.lideo.flywithus.controller;

import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.FlightDto;

import java.util.List;


@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @RequestMapping( method = RequestMethod.GET)
    public List<FlightDto> get(){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public FlightDto create(@RequestBody FlightDto flightDto) {
        return null;
    }
}
