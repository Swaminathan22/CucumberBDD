# CucumberBDD
Automated Tests Setup and Execution Guide Prerequisites

Java Development Kit (JDK): Ensure JDK 8 or higher is installed. Download JDK- https://www.oracle.com/java/technologies/javase-downloads.html

Maven: Ensure Apache Maven is installed. Download Maven- https://maven.apache.org/download.cgi

Chrome WebDriver: Download the Chrome WebDriver compatible with your Chrome browser version. Since we are using Selenium 4 dependency , we need explicitly specify chrome driver or download it , selenium manager will take care of the same.

Git: Ensure Git is installed. Download Git- https://git-scm.com/downloads

Setup Instructions

Clone the Repository: git clone cd

Install Dependencies mvn clean install

Directory Structure src/main/java: Contains the application code. src/test/java: Contains the test code. src/test/resources: Contains the feature files for Cucumber

Common Commands Run All Tests: mvn test Run a Specific Test Class: mvn -Dtest= test Run Cucumber Tests with Tags: mvn test -Dcucumber.options="--tags @Smoke"

For newer version we can use --- mvn test -D cucumber.filter.tags="@Smoke or @Regression"
