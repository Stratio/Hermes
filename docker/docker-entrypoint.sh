#!/bin/bash -xe

DEFAULT_PORT=2551
if [[ -z  ${PORT} ]]; then
  export PORT=${DEFAULT_PORT}
fi

PARAMS="-Dakka.remote.netty.tcp.port=${PORT}"

if [[ ! -z  ${SEED} ]]; then
  PARAMS="${PARAMS} -Dakka.cluster.seed-nodes.0=akka.tcp://hermes@${SEED}"
fi

if [[ ! -z ${HOSTNAME} ]]; then
  PARAMS="${PARAMS} -Dakka.remote.netty.tcp.hostname=${HOSTNAME}"
  sleep 5
  ping -c2 ${HOSTNAME} &> /dev/null
fi

if [[ ! -z  ${KAFKA_BOOTSTRAP_SERVERS} ]]; then
  PARAMS="${PARAMS} -DkafkaProducer.bootstrap.servers=${KAFKA_BOOTSTRAP_SERVERS}"
fi

PARAMS=PARAMS="${PARAMS} -DkafkaProducer.schema.registry.url=${SCHEMA_REGISTRY_URL}"
PARAMS=PARAMS="${PARAMS} -Dhermes.topic=${TOPIC}"

echo "Params: ${PARAMS}"
java -jar ${PARAMS} /hermes.jar

tail -F /var/log/sds/hermes/hermes.log
