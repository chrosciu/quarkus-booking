package com.chrosciu.booking;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class AuditService {
    private List<String> auditEntries = new ArrayList<>();

    public Uni<Void> auditTravel(String destination) {
        return Uni.createFrom().voidItem()
            .onItem().invoke(() -> auditEntries.add(destination))
            .onItem().delayIt().by(Duration.ofSeconds(1));
    }

    public Multi<String> getAuditEntries() {
        return Multi.createFrom().items(() -> auditEntries.stream());
    }
}
