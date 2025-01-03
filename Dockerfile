FROM artifactory.io.thehut.local:5000/adoptium-jdk/temurin17-alpine:17.0.2
WORKDIR /app
COPY target/popularity-service-0.1.0.jar popularity-service.jar
EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
#ENTRYPOINT ["sh", "-c", "java $JAVA_TOOL_OPTIONS -jar app.jar"]

ENTRYPOINT ["java", "--add-modules", "java.se", "--add-exports", "java.base/jdk.internal.ref=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED" ,"--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED", "--add-opens", "java.management/sun.management=ALL-UNNAMED", "--add-opens", "jdk.management/com.sun.management.internal=ALL-UNNAMED","-jar","popularity-service.jar"]