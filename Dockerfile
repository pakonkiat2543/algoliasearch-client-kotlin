FROM openjdk:17.0.2

ENV GRADLE_OPTS="-Dkotlin.compiler.execution.strategy=in-process -Dkotlin.incremental=false"
WORKDIR /app
COPY . /app/
RUN ./gradlew dependencies
