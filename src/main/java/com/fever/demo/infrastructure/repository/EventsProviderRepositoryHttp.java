package com.fever.demo.infrastructure.repository;

import com.fever.demo.infrastructure.entity.XMLEvent;
import com.fever.demo.infrastructure.entity.XMLPlanList;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url="", accept = "text/xml", contentType = "text/xml")
public interface EventsProviderRepositoryHttp {

    @GetExchange
    XMLPlanList getPlanList();
}
