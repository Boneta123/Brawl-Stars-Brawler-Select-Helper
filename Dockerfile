#Run docker build -t brawl-app: (Version ex 1.0) to create new image with this file

# Build stage (AS build sets this stage name as build)
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy everything
COPY . .

# Build jar by compiling
RUN ./mvnw clean package

# Run stage (lightweight runtime)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy built jar from build stage (“Copy the file from the stage named build”)
COPY --from=build /app/target/*.jar app.jar

#App uses port 8080
EXPOSE 8080

# for this CMD also is an option but that does not always work, for spring
#ENTRYPOINT is better as it is fixed executable
ENTRYPOINT ["java", "-jar", "app.jar"]
