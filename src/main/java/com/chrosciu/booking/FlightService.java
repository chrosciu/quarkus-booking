package com.chrosciu.booking;

import io.smallrye.mutiny.Uni;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class FlightService {
    public Uni<String> bookFlight(String destination) {
        return performBooking(destination)
            .onSubscription().invoke(() -> log.infof("[%s] Start booking...", destination))
            .onItem().invoke(() -> log.infof("[%s] Booking finished!", destination));
    }

    private Uni<String> performBooking(String destination) {
        if ("Moscow".equals(destination)) {
            return Uni.createFrom().failure(new IllegalStateException("Cannot book flight to Russia!"));
        } else {
            return Uni.createFrom().item(() -> "Booked flight to " + destination)
                .onItem().delayIt().by(Duration.ofSeconds(3));
        }
    }
}
