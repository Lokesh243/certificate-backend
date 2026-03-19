# -------- Build Stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# copy pom first (for caching)
COPY pom.xml .

# download dependencies
RUN mvn dependency:go-offline

# copy source code
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# -------- Run Stage --------
FROM eclipse-temurin:21-jdk

WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# expose port (Spring Boot default)
EXPOSE 8080

# run application
ENTRYPOINT ["java","-jar","app.jar"]