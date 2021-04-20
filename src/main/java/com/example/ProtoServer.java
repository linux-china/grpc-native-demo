package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ProtoServer {

    private static final Logger logger = Logger.getLogger(ProtoServer.class.getName());

    private Server server;

    @PostConstruct
    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(ProtoReflectionService.newInstance())
                //.addService(new GreeterImpl())
                .addService(new GreeterReactiveImpl())
                .build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM
                // shutdown hook.
                System.err.println(
                        "*** shutting down gRPC server since JVM is shutting down");
                ProtoServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    @PreDestroy
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

/*
	public static void main(String[] args) throws Exception {
		final ProtoApplication server = new ProtoApplication();
		server.start();
		server.blockUntilShutdown();
	}*/
}

