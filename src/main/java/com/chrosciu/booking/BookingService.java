package com.chrosciu.booking;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@RequiredArgsConstructor
@JBossLog
public class BookingService {
    private final FlightService flightService;
    private final HotelService hotelService;

    public Uni<String> book(String destination) {
        return Uni.join()
            .all(
                flightService.bookFlight(destination),
                hotelService.bookHotel(destination)
            )
            .andFailFast()
            .onItem().transform(
                bookings -> String.join("\n", bookings)
            )
            .onSubscription().invoke(() -> log.infof("[%s] Start booking...", destination))
            .onItem().invoke(() -> log.infof("[%s] Booking finished!", destination));
    }
}