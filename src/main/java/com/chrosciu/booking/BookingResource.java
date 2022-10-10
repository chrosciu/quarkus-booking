package com.chrosciu.booking;

import io.smallrye.mutiny.Uni;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestForm;

@Path("/booking")
@RequiredArgsConstructor
public class BookingResource {
    private final BookingService bookingService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> book(@RestForm String destination) {
        return bookingService.book(destination);
    }

}
