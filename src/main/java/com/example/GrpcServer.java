package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class GrpcServer {

    private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());
    private Server server;
    @Autowired
    private GreeterReactiveImpl greeterReactive;
    @Value("${grpc.port:50052}")
    private int grpcPort;

    @PostConstruct
    private void start() throws IOException {
        server = ServerBuilder.forPort(grpcPort)
                .addService(ProtoReflectionService.newInstance())
                //.addService(new GreeterImpl())
                .addService(greeterReactive)
                .build().start();
        logger.info("Server started, listening on " + grpcPort);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM
            // shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    @PreDestroy
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

}

