# Setup Kafka local using Docker

### Step 1: Install Docker
Make sure you have Docker installed on your machine. You can download and install Docker from the official website: Docker.

### Step 2: Create a Docker Network
Create a Docker network to allow communication between Kafka and Zookeeper containers.

`docker network create kafka-net`

### Step 3: Run Zookeeper
Zookeeper is required for Kafka to function. Run a Zookeeper container:

`docker run -d --name zookeeper --network kafka-net -p 2181:2181 -e ALLOW_ANONYMOUS_LOGIN=yes wurstmeister/zookeeper`

### Step 4: Run Kafka
Run a Kafka container, linking it to the Zookeeper container:

```
docker run -d --name kafka --network kafka-net -p 9092:9092 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
    -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
    -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT \
    -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
    -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=false wurstmeister/kafka
```

This command sets up a Kafka container, and the KAFKA_ADVERTISED_LISTENERS and related environment variables are configured to expose Kafka on localhost:9092.

### Step 5: Create a Topic (Optional)
You can create a topic using the Kafka command-line tools. For example, to create a topic named "test" with one partition and one replication factor:
`docker exec -it kafka kafka-topics.sh --create --topic test --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092`

