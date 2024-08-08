FROM openjdk:21-jdk

ARG BATCH_MODULE_JAR=/batch/build/libs/*.jar
ARG EXTERNAL_MODULE_JAR=/external-api/build/libs/*.jar

COPY ${BATCH_MODULE_JAR} batch.jar
COPY ${EXTERNAL_MODULE_JAR} external-api.jar

EXPOSE 8081 8082

CMD java -Dspring.profiles.active=common -Dserver.port=8081 \
        -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
        -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
        -jar /batch.jar & \
    java -Dspring.profiles.active=common -Dserver.port=8082 \
        -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
        -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
        -jar /external-api.jar
