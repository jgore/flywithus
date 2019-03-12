package tech.lideo.flywithus.controller;

import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.FlightDto;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FlightDto get(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public FlightDto create(@RequestBody FlightDto flightDto) {
        return null;
    }
}
