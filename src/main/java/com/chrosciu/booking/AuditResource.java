package com.chrosciu.booking;

import io.smallrye.mutiny.Uni;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.RequiredArgsConstructor;

@Path("/audit")
@RequiredArgsConstructor
public class AuditResource {
    private final AuditService auditService;

    @GET
    public Uni<List<String>> getAuditEntries() {
        return auditService.getAuditEntries().collect().asList();
    }


}
