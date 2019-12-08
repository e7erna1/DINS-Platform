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
    * ```bash 
      mvn clean
      ```
    * ```bash 
      mvn install
      ```
* Run
  * Enter the following commands in the command line (Linux):
      * ```bash 
        cd target/
        ```
      * ```bash 
        java -jar DINS-Platform-0.0.1-SNAPSHOT.jar
        ```
  * Enter the following commands in the command line (Windows):
      * ```bash 
        cd .\target\
        ```
      * ```bash 
        java -jar .\DINS-Platform-0.0.1-SNAPSHOT.jar
        ```
To close, use the keyboard shortcut Ctrl + C
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
POST request </br>
http://localhost:8080/person </br>
Request body sample:</br>
``
{
  "name": "Name"
}
``

**Edit user** </br>
PUT request </br>
http://localhost:8080/person/{personId} </br>
Request body sample: </br>
In this case personId is 1 </br>
``
{
   "id": 1,
   "name": "New name"
 }
``

**Delete user** </br>
DELETE request </br>
http://localhost:8080/person/{personId} </br>

**Get all records** </br>
GET request </br>
http://localhost:8080/person/{personId}/phoneBook </br>

**Get all records by part of the phone number** </br>
GET request </br>
http://localhost:8080/person/{personId}/phoneBook/number?num= {number} </br>

**Get record by id** </br>
GET request </br>
http://localhost:8080/person/{personId}/phoneBook/{recordId} </br>

**Add record** </br>
POST request </br>
http://localhost:8080/person/{personId}/phoneBook </br>
Request body sample:</br>
In this case personId is 1 </br>
``
{
	"name": "Oleg",
	"phoneNumber": "88005553535"
}
``

**Edit record** </br>
PUT request </br>
http://localhost:8080/person/{personId}/phoneBook/{recordId} </br>
Request body sample:</br>
In this case personId is 1 and recordId is 1</br>
``
{
 "id": 1,
	"name": "New value",
	"phoneNumber": "88005553636"
}
``

**Delete record** </br>
DELETE request </br>
http://localhost:8080/person/{personId}/phoneBook/{recordId} </br>
