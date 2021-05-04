package com.example;

import demo.Customer;
import demo.CustomerRequest;
import demo.ReactorGreeterGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@GRpcService
public class GreeterReactiveImpl extends ReactorGreeterGrpc.GreeterImplBase {
    @Override
    public Mono<Customer> hello(Mono<CustomerRequest> request) {
        Customer reply = Customer.newBuilder()
                .setId(1).setFirstName("Josh").setLastName("Long")
                .build();
        return Mono.just(reply);
    }
}
