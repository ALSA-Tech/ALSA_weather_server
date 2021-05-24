# ASLA Weather Cloud Project

#### Technology Stack| `Languagues and Tools`
><img alt="Spring" src="https://img.shields.io/badge/spring%20Boot%20-%236db33f.svg?&style=for-the-badge&logo=spring&logoColor=white"/> <img alt="Java" src="https://img.shields.io/badge/JAVA%20-%23E34F26.svg?&style=for-the-badge&logo=java&logoColor=white"/> <img alt="JavaFX" src="https://img.shields.io/badge/JAVA%20FX%20-%23f29400.svg?&style=for-the-badge&logo=java&logoColor=white"/><img alt="CSS3" src="https://img.shields.io/badge/css3%20-%231572B6.svg?&style=for-the-badge&logo=css3&logoColor=white"/> <img alt="Restful" src="https://img.shields.io/badge/RESTful API%20-%23404d59.svg?&style=for-the-badge"/> <img alt="AzureSQL" src="https://img.shields.io/badge/Azure%20SQL%20-%230089d6.svg?&style=for-the-badge&logo=Microsoft-Azure&logoColor=white"/> <img alt="GitHub" src="https://img.shields.io/badge/github%20-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/> <img alt="Git" src="https://img.shields.io/badge/git%20-%23F05033.svg?&style=for-the-badge&logo=git&logoColor=white"/> 

---
<br>
<br>

# Table of contents <!-- omit in toc -->

- **[About DevUp](#About-ASLA-Tech Weather API)**
- **[Architectural Approach](#Architectural-Approach)**
    - **Single page (SPA)**
    - **Restful API**
    - **Model View Controller (MVC)**
- **[Security](#Security)**
    - **Session**
    - **Hosting**
    - **Data storage**
- **[Backend](#Backend)**
    - **Server:**
    - **Routes:**
    - **MongoDB:**
   - **Middleware:**   
- **[Frontend](#Frontend)**
- **[File Structure](#File-Structure)**
---
<br>


# 1. About ALSA-Weather API
                                                  
ALSA Weather API is a software suit for subscribing and searching for top temperatures at geographical locations. The API is open for searches on locations, but requires an account and authentication for subscribing to locations. For each location, the ALSA API returns the top temperature for each day over the coming 10 days.

<img src="/alsa.PNG" alt="Ai target AI" width="800"/>     
# 2. Architectural Approach

The software suit includes a public RESTful server, and a desktop GUI application for graphical displays of API data.
Mainly, the cloud service receives a search for a location as a String, derives its longitude and latitude coordinates, makes a request to SMHI API, and then derives and returns top temperatures for the coming 10 days from that data. The client application displays the data in graph format. On top of that, accounts can be registered for subscribing to multiple locations, for which login is prompted.

# 3. Security

## 3.1 Data transfer

## 3.2 Data storage

## 3.3 Client authentication
Before the client accesses web resources, they have to provide a username and password via HTTP authentication,
comparing provided client password with hashed value in the cloud database. Upon a successful login a session attribute
will be created and associated with the client. Since HTTP is stateless (no session information is retained by the receiver), there is no way to associate a request 
to any other request and thus we need a way to identify the current user accessing our system. 
This will help us protect routes by keeping track of user privileges and access to resources.

# 4. Server

## 4.1 Architecture
The server application (backend) is developed using Spring Boot. It therefore centres around the Spring container, which is responsible for instantiating controller and service classes (as *beans*) which can be injected (*autowired*) using dependency injection. This is convenient as it reduces code and object management. From a programmer perspective, it also provides clarity as the class annotations used clearly specifies the purpose of a class (controller/service/repository/model etc.).

The server application provides a RESTful API as endpoint for client interactions. The API exposes the supported server features, and uses HTTP Session objects for gatekeeping, so that certain features may only be served to logged in clients. Other than that, the REST controller quickly delegates job to a Service instance. The service instance handles the business logic of processing and/or routing requests correctly inside the server application. The service instance is also the one that makes data access requests to the database manager instance, which in Spring JPA terms is known as a repository. Creating clear separations of concerns is good both for security and for delegations of tasks among team members, as it creates isolated and specific instances. 

So, the server application has an HTTP endpoint dealing with requests and responses in JSON format, as well as a backend database communication interface. However, because of the use of Jackson and ORM, manual object mappings in JSON or SQL queries is rarely needed. This reduces repetitive code, and creates a much slimmer and clearer code base. 

Figure 1 below illustrates an abstraction of the dataflow when serving a client request.

## 4.2 Client endpoint
The client endpoins uses the RESTful API architectural style, where HTTP requests
are used to access resources provided by the web server. In the web server, endpoints are setup
in the same way where routes specify what type of CRUD method its expecting on a predefined
URL. REST technology uses less bandwidth, making it suitable in comparison to other techniques and
serves the client application efficiently.

## 4.3 SMHI API

## 4.4 Database

## 4.5 Deploy

## 4.6 Automation: CI/CD

## 4.7 File structure

# 5. Client

## 5.1 File structure
<pre>
📦ASLA_weather_client
 ┣ 📂Controller
 ┃ ┣ 📜LoginController.java
 ┃ ┣ 📜OfflineController.java
 ┃ ┣ 📜PrimaryController.java
 ┃ ┣ 📜RegistrationController.java
 ┃ ┣ 📜UserController.java
 ┣ 📂models
 ┃ ┣ 📜Client.java
 ┃ ┣ 📜Location.java
 ┃ ┣ 📜LocationDataXY.java
 ┣ 📂utils
 ┃ ┣ 📜HTTPController.java
 ┃ ┣ 📜InputController.java
 ┃ ┣ 📜StringResource.java
 ┃ ┣ 📜WriteReadFiles.java
 ┣ 📜App.java
 ┣ 📂resources
 ┃ ┣ 📂fxml
 ┃   ┣ 📂fxml
 ┃     ┗ 📜login_pane.fxml
 ┃     ┗ 📜offline_user.fxml
 ┃     ┗ 📜registrations_pane.fxml
 ┃     ┗ 📜user_pane.fxml
 ┃     ┗ 📜primary.fxml
 </pre>
