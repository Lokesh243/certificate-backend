FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# fix permission issue
RUN chmod +x mvnw

# build jar
RUN ./mvnw clean package -DskipTests

# run app
CMD ["java", "-jar", "target/*.jar"]