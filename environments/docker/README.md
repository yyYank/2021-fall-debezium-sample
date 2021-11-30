
## dockerコンテナの起動

### networkつくる

```
docker network create 2021_fall_network_default
```

### kafkaにIPアドレスを教えるための環境変数設定

```
export DOCKER_HOST_IP=$(ipconfig getifaddr en0)
```

### 起動

```
docker-compose up -d
```

## kafkaのcliの使い方

### topic一覧

```
docker exec -it docker_kafka_1 /kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
```

### とあるtopicを見る

```
docker exec -it docker_kafka_1 /kafka/bin/kafka-console-consumer.sh --bootstrap-server "kafka:9092" --property print.key=true --property fetch.min.bytes=1 --topic "dbserver1.debezium-sample.albums" --from-beginning
```

## kafka connectの使い方

### connector登録

```shell
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '
{
        "name": "sample-music-connector",
        "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "tasks.max": "1",
        "database.hostname": "monolith-db",
        "database.port": "3306",
        "database.user": "debezium",
        "database.password": "dbz",
        "database.server.id": "223344",
        "database.server.name": "dbserver1",
        "database.include.list": "debezium-sample",
        "database.history.kafka.bootstrap.servers": "kafka:9092",
        "database.history.kafka.topic": "dbhistory.debezium-sample"
        }
}'
```

### connector一覧を表示する

```shell
curl -i -X GET -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors
```

### 登録したコネクターを見る

```shell
curl -i -X GET -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/sample-music-connector
```
