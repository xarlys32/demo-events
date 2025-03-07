package com.fever.demo.infrastructure.repository;

import com.fever.demo.domain.model.Events;
import com.fever.demo.domain.port.EventsProviderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventsProviderRepositoryImpl implements EventsProviderRepository {

    @Scheduled(fixedRate = 1000)
    public Optional<Events> lastEventsList() {
        return Optional.empty();
    }
}
