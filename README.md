# Weather Application

## Description
The application is designed to utilize the OpenWeatherAPI's API, allowing users to access Weather Forecasts (including current weather) based on either **city name** or **latitude-longitude** coordinates. 

The application provides users with the ability to register and log in. Upon logging in, users can retrieve weather information by entering either the **city name** or **latitude-longitude** details. 

Additionally, users can save cities as favorites and conveniently access them at a later time.

Application is built on Spring Boot and React. 
Spring Boot project is located in the **weather** folder and the React project is located in the **weather-client** folder

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

![image](https://github.com/emrekayacik/weather/assets/73127270/a09188f7-f1f5-4311-b98b-61f4b29106f7)

The server is up on the 8080 port

![image](https://github.com/emrekayacik/weather/assets/73127270/37612931-f78a-4864-8e73-d24adb864f17)

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


![image](https://github.com/emrekayacik/weather/assets/73127270/ae15566d-7204-4b59-bd23-b8f8e725c30d)


#### Run the command to start the React Application

```
npm start
```

![image](https://github.com/emrekayacik/weather/assets/73127270/023dbf2d-0049-421b-a231-c8a48a564331)

**Congratulations, your application is up on the port 3000, enjoy the project**

<hr><hr>

## Swagger UI endpoints

![image](https://github.com/emrekayacik/weather/assets/73127270/82408a96-0753-4d69-9b79-0a23a9e069a1)

<hr>

## Screen Images from client application

#### Sign In

![image](https://github.com/emrekayacik/weather/assets/73127270/24dcad9d-d798-4556-9283-461a3d5dda04)

#### Register

![image](https://github.com/emrekayacik/weather/assets/73127270/72d30964-f978-4ce5-81c5-6e33ffdf5b42)


#### Home page, After logging in or creating an account

![image](https://github.com/emrekayacik/weather/assets/73127270/52426f01-7bf2-4079-af5d-9fa3c9df30f3)


### Choices to fetch weather data

![image](https://github.com/emrekayacik/weather/assets/73127270/288e3596-0ba4-4658-bd2b-74a454227d3b)

### By city name

![image](https://github.com/emrekayacik/weather/assets/73127270/2511b388-de19-42bc-90a3-f58a305ac8f4)

![image](https://github.com/emrekayacik/weather/assets/73127270/bb4c02e9-d09e-42bc-b0e6-9dac12b63805)

### Add to favorite

![image](https://github.com/emrekayacik/weather/assets/73127270/83ceb1f6-4087-404a-af86-d82464008f3f)


### By latitude and longtitude

![image](https://github.com/emrekayacik/weather/assets/73127270/b3a1e8f7-3955-4ee9-9ebe-d4d1a56ac1ef)


### By user's favorite

![image](https://github.com/emrekayacik/weather/assets/73127270/4b955712-8501-4e16-b55b-a08e9ab94b6b)

![image](https://github.com/emrekayacik/weather/assets/73127270/71e5e8fa-dc6d-46c6-a87f-f3f7a9a7574b)


### Remove from favorites

![image](https://github.com/emrekayacik/weather/assets/73127270/7ff6ba72-0da7-45c7-a672-9435b7c37875)

### Favorite List is updated

![image](https://github.com/emrekayacik/weather/assets/73127270/305e38c7-1c4c-4aeb-a667-e08e0673b6dd)

### Logout(right-top corner of the application)

![image](https://github.com/emrekayacik/weather/assets/73127270/04b19f4c-810f-469e-93ba-11cfe9ca0054)

