# Kafka Payment Producer-Consumer

## Local Setup and Running

### Prerequisites
- **Java 17+** installed (`java -version`)
- **Maven** installed (`mvn -version`)
- **Docker** installed ([Download Docker](https://www.docker.com/get-started))

### Clone the Repository
```bash
git clone https://github.com/snkt-brao/kafka_payment_prod_consume.git  
cd kafka_payment_prod_consume  
```

### Build the Project
```bash
mvn clean install  
```

---  
## Kafka & Zookeeper Setup with Docker

### Start Kafka & Zookeeper
```bash
docker-compose up -d  
```

### Verify Kafka Installation
#### Check Running Containers:
```bash
docker ps  
```
You should see `kafka` and `zookeeper` containers running.

#### Create a Topic:
```bash
docker exec -it kafka kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1  
```

#### List Topics:
```bash
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092  
```

---  
## Run the Application
```bash
mvn spring-boot:run  
```

Now your Kafka-based payment processing app should be running locally! 🚀

---  
## Stop Kafka & Zookeeper (When done)
```bash
docker-compose down  
```

