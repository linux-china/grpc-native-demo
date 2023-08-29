# maven java build
build:
  mvn -DskipTests clean package

# grpc call
grpc-call:
  grpcurl -plaintext -d '{}' localhost:50052 demo.Greeter/Hello

# native build
native-build:
   mvn -Pnative -DskipTests clean package native:compile

# run native image agent to generate related configuration files
native-assist:
  mvn -DskipTests clean package spring-boot:repackage
  mkdir -p target/native-image
  java -agentlib:native-image-agent=config-output-dir=./target/native-image/ -jar target/grpc-native-demo-1.0.0-SNAPSHOT.jar

# docker image build with Buildpacks
image-build:
   mvn -Pnative -DskipTests spring-boot:build-image
