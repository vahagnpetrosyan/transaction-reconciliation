FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ./target/${JAR_FILE} /apps/app.jar
COPY ./entrypoint.sh /apps/entrypoint.sh
RUN dos2unix /apps/entrypoint.sh
RUN chmod +x /apps/entrypoint.sh
ENTRYPOINT ["/apps/entrypoint.sh"]
