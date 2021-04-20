# maven java build
build:
  mvn -DskipTests clean package

# grpc call
grpc-call:
  grpcurl -plaintext -d '{}' localhost:50052 demo.Greeter/Hello

# native build
native-build:
   mvn -Pnative -DskipTests clean package

# run native image agent to generate related configuration files
native-assist: build
  mkdir -p target/native-image
  java -agentlib:native-image-agent=config-output-dir=./target/native-image/ -jar target/grpc-spring-native-demo-0.0.1-SNAPSHOT.jar

# docker image build with Buildpacks
image-build:
   mvn -Pnative -DskipTests spring-boot:build-image
