gRPC with Spring Native
=======================

# Features

* Spring Native
* Reactive gRPC support
* Docker images build with Buildpacks

# Attentions

* grpc-netty-shaded: not supported by Spring Native
* `option java_multiple_files = true`  preferred

# native-image args

* --gc=G1: linux only

# References

* Spring Native: https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/
* Reactive gRPC: https://github.com/salesforce/reactive-grpc
* gRPC-Java:  https://github.com/grpc/grpc-java
* gRPC Home: https://grpc.io/
* Maven plugin for GraalVM Native Image building: https://graalvm.github.io/native-build-tools/0.9.3/maven-plugin.html
