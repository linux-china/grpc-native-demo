package com.example;

import demo.Customer;
import demo.CustomerRequest;
import demo.GreeterGrpc;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void hello(CustomerRequest req, StreamObserver<Customer> responseObserver) {
        Customer reply = Customer.newBuilder().setId(1).setFirstName("Josh").setLastName("Long")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
