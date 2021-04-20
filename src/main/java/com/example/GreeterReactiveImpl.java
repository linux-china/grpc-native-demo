package com.example;

import demo.CustomerProtos;
import demo.ReactorGreeterGrpc;
import reactor.core.publisher.Mono;

public class GreeterReactiveImpl extends ReactorGreeterGrpc.GreeterImplBase {
    @Override
    public Mono<CustomerProtos.Customer> hello(Mono<CustomerProtos.CustomerRequest> request) {
        CustomerProtos.Customer reply = CustomerProtos.Customer.newBuilder()
                .setId(1).setFirstName("Josh").setLastName("Long")
                .build();
        return Mono.just(reply);
    }
}
