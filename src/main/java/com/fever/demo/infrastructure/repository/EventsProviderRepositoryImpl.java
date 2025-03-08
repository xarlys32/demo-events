package com.fever.demo.infrastructure.repository;

import com.fever.demo.config.Constants;
import com.fever.demo.domain.model.Events;
import com.fever.demo.domain.port.EventsProviderRepository;
import com.fever.demo.infrastructure.entity.XMLEvent;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class EventsProviderRepositoryImpl implements EventsProviderRepository {

    private final HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
            .baseUrl(Constants.PROVIDER_BASE_URL).build();

    @Scheduled(fixedRate = 10000)
    public Optional<Events> lastEventsList() {
        webClient.get()
                .uri(Constants.PROVIDER_EVENT_ENDPOINT)
                .retrieve()
                .bodyToFlux()
                .subscribe(this::processEvent);
        return Optional.of(new Events());
    }

    private void processEvent(Object xmlEvent) {
        System.out.println("Event: "+xmlEvent);
    }
}
