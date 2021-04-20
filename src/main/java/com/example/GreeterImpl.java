package com.example;

import demo.CustomerProtos;
import demo.GreeterGrpc;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void hello(CustomerProtos.CustomerRequest req, StreamObserver<CustomerProtos.Customer> responseObserver) {
        CustomerProtos.Customer reply = CustomerProtos.Customer.newBuilder().setId(1).setFirstName("Josh").setLastName("Long")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
