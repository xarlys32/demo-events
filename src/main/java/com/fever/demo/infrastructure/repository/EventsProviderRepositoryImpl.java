package com.fever.demo.infrastructure.repository;

import com.fever.demo.config.Constants;
import com.fever.demo.domain.model.Events;
import com.fever.demo.domain.port.EventsProviderRepository;
import com.fever.demo.infrastructure.entity.XMLEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
public class EventsProviderRepositoryImpl implements EventsProviderRepository {

    private final WebClient webClient = WebClient.builder().baseUrl(Constants.PROVIDER_BASE_URL).build();

    @Scheduled(fixedRate = 100000)
    public Optional<Events> lastEventsList() {
        webClient.get()
                .uri(Constants.PROVIDER_EVENT_ENDPOINT)
                .retrieve()
                .bodyToFlux(XMLEvent.class)
                .subscribe(this::mapToEvents);
        return Optional.of(new Events());
    }

    private Events mapToEvents(XMLEvent xmlEvent) {
        return new Events();
    }
}
