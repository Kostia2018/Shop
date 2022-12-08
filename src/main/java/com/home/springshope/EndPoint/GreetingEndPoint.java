package com.home.springshope.EndPoint;


import com.home.springshope.Service.GreetingService;
import com.home.springshope.ws.greeting.GetGreetingRequest;
import com.home.springshope.ws.greeting.GetGreetingResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GreetingEndPoint {

    private GreetingService greetingService;


    public GreetingEndPoint(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @PayloadRoot(namespace = "http://home.com/springshope/ws/greeting", localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest greetingRequest) throws DatatypeConfigurationException {

        GetGreetingResponse getGreetingResponse = new GetGreetingResponse();

        getGreetingResponse.setGreeting(greetingService.generateGreeting(greetingRequest.getName()));


        return getGreetingResponse;

    }


}
