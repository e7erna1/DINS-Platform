# DINS-Platform
## Description
This is simple application represents server side for working with users and their phone books.</br>
Application provide following functions:
## User
* Creation of the user.
* Getting of single uesr.
* Getting all users.
* Getting user by his name (part of name).
* Eddition of the user.
* Deletion of the user.
## Phone book
* Creation new phonebook entries.
* Getting all phonebook entries for any user.
* Getting phonebook entries for any user by it's id.
* Getting phonebook entries for any user by part (or full) number.
* Eddition of phonebook entries.
* Deletion of phonebook entries.
## Download, build and run application:
* Download
  * Create a folder on your computer.
  * In this folder, open command line and run the command:
    ```bash 
    git clone https://github.com/iCode002/DINS-Platform.git
    ```
* Build
  * Enter the following commands in the command line:
    * ``bash 
      mvn clean
      ``
    * ``bash 
      mvn install
      ``
* Run
  * Enter the following commands in the command line (Linux):
      * ``bash 
        cd target/
        ``
      * ``bash 
        java -jar DINS-Platform-0.0.1-SNAPSHOT.jar
        ``
# Working with application
I recommend using the utility [Postman](https://www.getpostman.com/). </br>
All work with the application occurs at the following url: http://localhost:8080 </br>

**Get all users** </br>
GET request </br>
http://localhost:8080/person </br>

**Find a user by name or part of a name** </br>
GET request </br>
http://localhost:8080/person/name?name= {Name}</br>

**Find a user by id** </br>
GET request </br>
http://localhost:8080/person/{personId} </br>

**Add new user** </br>
CREATE request </br>
http://localhost:8080/person </br>
