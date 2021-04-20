package com.example;

import demo.CustomerProtos;
import demo.ReactorGreeterGrpc;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreeterReactiveImpl extends ReactorGreeterGrpc.GreeterImplBase {
    @Override
    public Mono<CustomerProtos.Customer> hello(Mono<CustomerProtos.CustomerRequest> request) {
        CustomerProtos.Customer reply = CustomerProtos.Customer.newBuilder()
                .setId(1).setFirstName("Josh").setLastName("Long")
                .build();
        return Mono.just(reply);
    }
}
