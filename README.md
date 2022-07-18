# E2E Automation

Sample tests for https://www.saucedemo.com/ website.


## Prerequisites

* Java JDK >= 9
* Maven
* Chrome browser


## Running

`mvn test allure:report`

Test report will be created in `target/site/allure-report/index.html` However, due to security restrictions browsers 
don't allow AJAX requests on the local filesystem and hence the report won't be correctly displayed unless it's loaded 
from an HTTP server. You can use `mvn test allure:serve` instead.

## Multi-browser testing

By default, tests are executed with Chrome. It is possible to specify different browser by passing `-Dsel.jup.default.browser=BROWSER_NAME` to the above command.
It was tested with Firefox

## Tech Stack
* [Maven](https://maven.apache.org/)
* [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
* [JUnit 5](https://junit.org/junit5/)
* [Hamcrest](http://hamcrest.org/) 
* [Selenium](https://www.selenium.dev/)
* [Selenium-Jupiter](https://bonigarcia.github.io/selenium-jupiter/)
* [Allure Test Report](http://allure.qatools.ru/)
