package com.fever.demo.domain.port;

import com.fever.demo.domain.model.Events;

import java.util.Optional;

public interface EventsProviderRepository {
    Optional<Events> lastEventsList();
}
