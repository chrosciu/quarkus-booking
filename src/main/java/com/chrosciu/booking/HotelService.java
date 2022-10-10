package com.chrosciu.booking;

import io.smallrye.mutiny.Uni;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class HotelService {
    public Uni<String> bookHotel(String destination) {
        return Uni.createFrom().item(() -> "Booked hotel in " + destination)
            .onItem().delayIt().by(Duration.ofSeconds(3))
            .onSubscription().invoke(() -> log.infof("[%s] Start booking...", destination))
            .onItem().invoke(() -> log.infof("[%s] Booking finished!", destination));
    }
}
