# ASLA Weather Cloud Project

#### Technology Stack| `Languagues and Tools`
><img alt="Spring" src="https://img.shields.io/badge/spring%20Boot%20-%236db33f.svg?&style=for-the-badge&logo=spring&logoColor=white"/> <img alt="Java" src="https://img.shields.io/badge/JAVA%20-%23E34F26.svg?&style=for-the-badge&logo=java&logoColor=white"/> <img alt="JavaFX" src="https://img.shields.io/badge/JAVA%20FX%20-%23f29400.svg?&style=for-the-badge&logo=java&logoColor=white"/><img alt="CSS3" src="https://img.shields.io/badge/css3%20-%231572B6.svg?&style=for-the-badge&logo=css3&logoColor=white"/> <img alt="Restful" src="https://img.shields.io/badge/RESTful API%20-%23404d59.svg?&style=for-the-badge"/> <img alt="AzureSQL" src="https://img.shields.io/badge/Azure%20SQL%20-%230089d6.svg?&style=for-the-badge&logo=Microsoft-Azure&logoColor=white"/> <img alt="GitHub" src="https://img.shields.io/badge/github%20-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white"/> <img alt="Git" src="https://img.shields.io/badge/git%20-%23F05033.svg?&style=for-the-badge&logo=git&logoColor=white"/> 

---
<br>
<br>

## Table of contents <!-- omit in toc -->

- **[About DevUp](#About-DevUp)**
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


## About DevUp
- DevUps allows software developers  to easily market themselves for job or gig opportunities.

- No account or login is required for browsing among developers.

- If you are looking for a developer for employment or gig purposes, use the filter function to specialize your search.

- If you are a developer, create an account to start edit your profile in simple steps.

- A DevUp admin is privileged to ban, delete and add developers

- Error management using appropriate HTTP status codes, served by backend and handled by frontend.


## Architectural Approach
 - **#Single page (SPA):**
 - **Restful API:**
 - **Model View Controller (MVC):**
 - 
## Security
 - **Session:**
 - **Hosting:**
 - **Data storage:**

## Backend
   - **Server:**
      - **Enviromental Variables**
   - **Routes:** 
   ```javascript
        var express = require('express');
        var router = express.Router();

        router.post('/',function(req,res){
            //logic
        });
        module.exports = router;
   ```
   - **MongoDB:**
        -    MongoDB is intigrated as the backend database. The database is used as it gives a good structure of JSON objects. As the data communication in devUp is designed to work with JSON data, makes it a perfect fit without need to take into consideradion of relationship. As all data is connected to every specific developer. This also reduce the risk of SQL injection.
        -   Backend implement the `Moongose framework` for a faster and easier implementation of the MongoDB. To create specific schema and model templates for adding Developers and creating accounts. The two Model classes are `user.js` and `utils.js`.
            -   `user.js`:  this class implementing a template for when users/devlopers are added and retrived from the MongoDB. Making sure every user follow the same structure. This class also make sure that passwords get hashed and salted with bcrypt and validate so data is in correct format.

                ```javascript
                const mongoose = require('mongoose');
                const bcrypt = require('bcrypt');
                const saltRounds = 10;

                var validateEmail = function(email) {
                    let re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    return re.test(email)
                };
                // Schema
                const userSchema = new mongoose.Schema({
                    _id: {
                        type: mongoose.Types.ObjectId,
                        default: mongoose.Types.ObjectId(),
                        auto: true
                    },
                    email: {
                        type: String,
                        trim: true,
                        lowercase: true,
                        unique: true,
                        required: 'Email address is required',
                        validate: [validateEmail, 'Not a valid emails'],
                        match: [/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/, 'Not a a valid email'],
                    },
                    password: String,
                    isAdmin: {
                        type: Boolean,
                        default: false
                    },
                    // Code extended
                 ```
            -   `utils.js`:  This as a helper class for helping the frontend display and implement Skills the devlopers can pick and assign to themself. The skills are later sent to the frontend and helps them to render this data when page is loaded.

   - **MiddleWare and Controllers:**
        -    In the backend multiple classes are implemented for handling speciffic function in the backend system and act as helpers. This make the code more readable and seperate majority of logic out of important classes.
                -   `middleware.js` act to setup mockdata, creating important data that is needed both for the frontend and backend.
                -   `memberController.js` controlls and handle the data for the `member.js` routes class that manipulate data from the database to respond the necassary information to the frontend. Function exampeel is check if a user isBanned, creatinging specific clientObjects so they can be displayed correctly.
                -   `developerController.js` helps the `developer.js` routes class. By implementing function for filtingering information from the database to respond the right objects depending on a front end filter search.

                ```javascript
                function searchFilter (admin,skills,name_start,price_max){
                    let adminFilter;
                    if(!admin){
                        var isBanned = {isBanned: false};
                    }
                    if(skills.length > 0){
                        //var skillFilter = {'skillset': {$elemMatch: {'skillName': skills}}};
                        var skillFilter = { 'skillset.skillName':  {$all: skills}};
                    }
                    adminFilter ={...isBanned,...skillFilter};
                    console.log(adminFilter);
                    return adminFilter;
                }
                 module.exports = {
                searchFilter: searchFilter,
                }
                ```

## Frontend
 - **Injectable components:**
 - **DOM manipulation:**
 - **Ajax:**

```html

<p>show html preview here</p>

```
---
<br>

## File Structure Server


## File Structure Client
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
