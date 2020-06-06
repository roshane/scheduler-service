FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
USER root:root
COPY BOOT-INF/lib /app/lib
COPY META-INF /app/META-INF
COPY BOOT-INF/classes /app
RUN mkdir ./logs
ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 8080/tcp
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-cp","app:app/lib/*","com.aeon.scheduler.service.SchedulerServiceBoot"]