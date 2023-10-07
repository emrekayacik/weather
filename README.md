# Weather Application

## Description
The application is designed to utilize the OpenWeatherAPI's API, allowing users to access Weather Forecasts (including current weather) based on either **city name** or **latitude-longitude** coordinates. 

The application provides users with the ability to register and log in. Upon logging in, users can retrieve weather information by entering either the **city name** or **latitude-longitude** details. 

Additionally, users can save cities as favorites and conveniently access them at a later time.

Application is built on Spring Boot, Kafka and React. If your Kafka Broker is not running on your local machine or docker, you may encounter errors.
Please be sure your Kafka Broker is up and running and configured correctly. If you run app without Kafka, delete these files
"weather\src\main\java\com\emrekayacik\weather\kafka\*"
"weather\src\main\java\com\emrekayacik\weather\interceptors\KafkaLoggingInterceptor.java"
"weather\src\main\java\com\emrekayacik\weather\config\WebMvcConfig.java"
Spring Boot project is located in the **weather** folder, logging microservice with kafka is located in the **weather-log-service** folder and the React project is located in the **weather-client** folder

<hr> <hr>

## Links

**Swagger UI**

http://localhost:8080/api/v1/swagger-ui/index.html#/

<br>

**React Application**

http://localhost:3000/

<hr> <hr>

## Prerequisites

### Application properties

- Java version: 17
- Spring Boot version: 3.1.0
- Client Application: React
- Database: PostgreSQL



### Database

- Create PostgreSQL server and name it as **"weather"**, then Specify username and password.
- Create PostgreSQL database **"postgres"** under the server **"weather"** 
- Create PostgreSQL database **"postgres-test"** under the server **"weather"** 

<hr> <hr>


### Properties for Database Configuration in **application.properties** file
  
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
  
spring.datasource.username={username}
  
spring.datasource.password={password}
  
spring.datasource.driver-class-name=org.postgresql.Driver
```

  
### Properties for Database Configuration in **application.properties** file for test
  
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres-test
  
spring.datasource.username={username}
  
spring.datasource.password={password}
  
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Properties for Kafka in "weather-log-service"
  
```
server.port=8081

weather.kafka.logger.address=localhost:9092

weather.kafka.logger.topic=logger-topic

weather.kafka.logger.group-id=log-group
```
> You can find the original application.properties file in the project under **src/main/resources**


<hr><hr>
<br>

## How to run Server Application (Spring Boot)

#### Clone the repository

```
git clone https://github.com/emrekayacik/weather.git
```

#### Change directory to Spring Boot Application's folder

```
cd weather/weather
```

#### Run maven command to clean and install the Spring Boot project

```
mvn clean install
```

#### Run the jar

```
java -jar target/weather-0.0.1-SNAPSHOT.jar
```

![image](https://github.com/emrekayacik/weather/assets/73127270/2c1f378f-ed67-4982-806f-2ed3c00049fc)


The server is up on the 8080 port

![image](https://github.com/emrekayacik/weather/assets/73127270/0287e73d-0eef-4326-bdf3-df15f894b4f9)


Go to http://localhost:8080/api/v1/swagger-ui/index.html#/



<hr> <hr>
<br>

## How to run Client Application (React)

#### Clone the repository(do not clone the repository if you have already did)

```
git clone https://github.com/emrekayacik/weather.git
```

#### Change directory to React Application's folder

```
cd weather/weather-client
```

#### Run the command to install dependencies

```
npm install
```

> This process can take a while, maybe you can fill a cup of coffee :) Wait for the installation and do not proceed the next step until it's finished


![image](https://github.com/emrekayacik/weather/assets/73127270/da3ae65e-7570-4198-9837-79c4df046f27)


#### Run the command to start the React Application

```
npm start
```

![image](https://github.com/emrekayacik/weather/assets/73127270/5b029491-58d2-4f44-9c97-45f0dc7fbc0f)


**Congratulations, your application is up on the port 3000, enjoy the project**

<hr><hr>

## Swagger UI endpoints

![image](https://github.com/emrekayacik/weather/assets/73127270/4c448b1f-5a03-4b66-886f-a8e20c6939a8)

<hr>

## Screen Images from client application

#### Sign In

![image](https://github.com/emrekayacik/weather/assets/73127270/4d479f12-145d-4e26-8b18-f8860d1a894d)


#### Register

![image](https://github.com/emrekayacik/weather/assets/73127270/64337c0a-55cb-45d9-8764-a66b5f38aca7)


#### Home page, After logging in or creating an account

![image](https://github.com/emrekayacik/weather/assets/73127270/5e7371c2-528f-41e0-a186-3392da7e479a)


### Choices to fetch weather data

![image](https://github.com/emrekayacik/weather/assets/73127270/fef40a99-7648-4d5e-ad97-acb918939fe7)

### By city name

![image](https://github.com/emrekayacik/weather/assets/73127270/870f838d-9430-47a4-ba1a-158723cd3d30)

![image](https://github.com/emrekayacik/weather/assets/73127270/63db28c2-ab56-4b0a-ac7f-e8df07451470)

### Add to favorite

![image](https://github.com/emrekayacik/weather/assets/73127270/74841ec9-9acd-485c-b725-30dfae6fbf91)


### By latitude and longtitude

![image](https://github.com/emrekayacik/weather/assets/73127270/12132f83-89ab-4fad-a271-0da7cfd62106)


### By user's favorite

![image](https://github.com/emrekayacik/weather/assets/73127270/f454ac29-6607-47fd-bbf8-f52d2e60eacf)

![image](https://github.com/emrekayacik/weather/assets/73127270/eba0fbf0-fcfc-4749-9ba2-55b0a4d9c5b4)


### Remove from favorites

![image](https://github.com/emrekayacik/weather/assets/73127270/65af7997-d4c2-4c73-8ab4-46b96b5a08b9)

### Favorite List is updated

![image](https://github.com/emrekayacik/weather/assets/73127270/02afcbf8-2ccf-42a0-a34a-19bccfe321b0)

### Logout(right-top corner of the application)

![image](https://github.com/emrekayacik/weather/assets/73127270/49cbd4e3-0c4a-454d-9521-791a0b86f726)

### Request Logs from Kafka Listener

![image](https://github.com/emrekayacik/weather/assets/73127270/d5917e89-9e72-4dbf-938c-9cbaf044fc47)


