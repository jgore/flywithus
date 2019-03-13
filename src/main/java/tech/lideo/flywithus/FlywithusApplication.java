package tech.lideo.flywithus;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.repository.FlightRepository;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class FlywithusApplication implements CommandLineRunner {

    public static final Gson gson = new Gson();

    @Autowired
    private FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(FlywithusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for( int i=0;i<100;i++)
        {
            flightRepository.create( prepareFlight() );
        }
    }

    //@FIXME refactor - copy paste to some common class
    private FlightDto prepareFlight() {
        FlightDto flightDto = new FlightDto();
        flightDto.setArrivalCity("wroclaw");
        flightDto.setArrivalDate(new Date());
        flightDto.setDepartureCity("London");
        flightDto.setDepartureDate(new Date());

        flightDto.setPrice(new BigDecimal(15));
        flightDto.setPassengersAmount(2);

        return flightDto;
    }

}
