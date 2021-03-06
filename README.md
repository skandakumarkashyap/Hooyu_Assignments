# Hooyu_Assignments
<h1>Assignments in UI and API automation</h1>
<h2> Rest assured + Cucumber + Junit + Maven project</h2>


<h4>Required software <h4>
  <p>Java JDK 8</p>
  <p> Maven configured in either Eclipse or IntelliJ</p>
  <p>Git should be installed and configured inorder to clone this project</p>
  
<h4> Libraries used in this project </h4>
  <p>Cucumber to generate BDD test cases (feature files) </p>
  <p> Webdrivermanager</p>
  <p>Selenium to perform web related actions</p>
  <p>RestAssured for testing REST API</p>  
  <p>junit to logically group the tests and execute it</p>
  
  
<h4> How to execute the test </h4>
  <p>1. Open the command prompt and navigate to the root directory of the project</p>
  <p>2. Issue mvn test command</p>
  <p>3. Both the API tests and User interface tests starts executing one after another</p>
  




<h5> Details about the project structure </h5>

This project contains automation scripts for testing both API and UI tasks provided.


Below are the folder structures followed and its details:

There are 2 runner files maintained, one for driving UI tests and another for driving API tests.

File location for API runner: src/test/java/apiRunner/APITestRunner.java
File location for UI tests runner : src/test/java/uiRunner/UITestRunner.java

Step definition files are located in below folder locations respectively

File location of API step definition:src/test/java/apiStepDefinitions/Steps.java
File location of UI step definition:src/test/java/uiStepDefinitions/Steps.java

Since this is Cucumber project, all the feature files for API and UI tests are under below location

API tests feature file location: src/test/resources/apiTests/PositiveTests.feature
                                 src/test/resources/apiTests/NegativeTests.feature

Each of them contains 5 scenarios each in the above feature files.

UI tests feature file location:  src/test/resources/userInterfaceTests/AddProductAndValidate.feature
                                 src/test/resources/userInterfaceTests/UserRegistrationNegativeTests.feature
                                 src/test/resources/userInterfaceTests/UserRegistration.feature
  
  
<h4> Future enhancements and improvements </h4>
  <p>1. Implementation of Driver related functions</p>
  <p>2. Extensive exception handling</p>
  <p>3. Implementing generic assertion methods</p>
  <p>4. Enhancing of steps definition for increased re-usability of the steps</p>

  
<h4> Configuration of CI/CD </h4>
 <p>1. Creating and configuring the jenkins/Azure agent by providing the link to source code in git</p>
  <p>2. As part of configuration adding mvn test command for the job created</p>
  <p>3. Setting necessary schedule as and when needed to trigger the execution </p>
  
