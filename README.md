# xe-challenge
## Author: Filippos Addimando
### REST API made for the XE.gr challenge

### What you will need to run the API

- Eclipse IDE
- Apache Tomcat 8.5 (embedded inside Eclipse IDE) 
- Postman

### Steps to run

1. Open project in Eclipse (Download / clone then Import from directory inside IDE)
2. Run "mvn install", or right-click onto the project, then Run As -> Maven Install
3. You should now be able to see the generated target folder with the classes.
4. Right click on the Project -> Run As -> Run on Server. If Tomcat is properly 
installed, you should be able to test the API using Postman. Please note that, when
using Postman, you will need to send the requests using "raw" as body format.

### Running the Unit tests

In Eclipse, right click on the Project, select "Coverage As" and run as JUnit test.

