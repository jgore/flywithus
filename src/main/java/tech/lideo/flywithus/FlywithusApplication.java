package tech.lideo.flywithus;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlywithusApplication {

    public static final Gson gson = new Gson();

    public static void main(String[] args) {
        SpringApplication.run(FlywithusApplication.class, args);
    }

}
