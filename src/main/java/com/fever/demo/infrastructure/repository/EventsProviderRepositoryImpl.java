package com.fever.demo.infrastructure.repository;

import com.fever.demo.config.Constants;
import com.fever.demo.domain.port.EventsProviderRepository;
import com.fever.demo.domain.usecases.EventUsesCases;
import com.fever.demo.infrastructure.adapter.EventXmlToDomAdapter;
import com.fever.demo.infrastructure.entity.Xml.PlanList;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class EventsProviderRepositoryImpl implements EventsProviderRepository {

    @Autowired
    private EventUsesCases eventUsesCases;

    @Autowired
    private EventXmlToDomAdapter eventXmlToDomAdapter;

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
    public void lastEventsList() {
        webClient.get()
                .uri(Constants.PROVIDER_EVENT_ENDPOINT)
                .accept(MediaType.valueOf(MediaType.TEXT_XML_VALUE))
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // Log or handle the error response body here
                                    return Mono.error(new RuntimeException("Error response: " + errorBody));
                                })
                )
                .bodyToFlux(PlanList.class)
                .onErrorMap((throwable) -> {
                    if (throwable instanceof RuntimeException) {
                        return new Exception("API call failed", throwable);
                    } else {
                        return throwable;
                    }
                })
                .subscribe(this::processEvent,
                        error -> {
                            System.err.println("Final error: " + error.getMessage());
                        });
    }

    private void processEvent(PlanList xmlEvent) {
        eventUsesCases.getProcessedEvent(eventXmlToDomAdapter.toDom(xmlEvent));
    }
}
