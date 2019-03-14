package tech.lideo.flywithus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.lideo.flywithus.service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ReservationService reservationService;

    //every hour we check for expired reservations

    //@Fixme add integration test
    @Scheduled(cron = "${flywithus.autoCancel.cron}")
    public void autoCancelationExpiredReservations() {
        log.info("Auto cancellation expired reservations job started at", dateFormat.format(new Date()));
        reservationService.autoCancelExpiredReservations();
    }
}
