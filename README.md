# cosmart_joybox

# Dependency
- Java Runtime Environment version 11+
- Apache Tomcat server
- Maven

# How to build and run the service
- Clone this repository to a Java IDE (Eclipse, Intellij Idea, etc).
- Run command `mvn clean install`. This will let Maven to download the necessary depedencies.
- Run command `mvn clean package`. This will create an executable Java Jar file in the project's local directory (inside the `/target` folder).
- Open terminal and change the current directory to the project's `target` folder i.e. (`~/cosmart_joybox/target`).
- Run command `java -jar cosmart_joybox-0.0.1-SNAPSHOT`. This will run the service. 

# Example Curl request to hit the APIs
- Get list of books by genre / subject
  ```
  curl --location --request GET 'localhost:8080/library/book?subject=love'
  ```
- Submit a book pick up schedule.
  ```
  curl --location --request POST 'localhost:8080/library/book/pickup' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "pickupSchedule": "2022-12-12 13:00",
    "book": {
        "title": "test",
        "edition": "lala",
        "authors": [
            {
                "key": "12345",
                "name": "kami"
            }
        ]
    }
  }'
  ```
- Get all book pick-up schedules 
  ```
  curl --location --request GET 'localhost:8080/library/book/pickup'
  ```
  
## It is important to note the for book pick-up schedule will not be persisted after the service has been stopped (as there is no database).
