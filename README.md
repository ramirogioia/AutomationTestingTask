# Automation Testing Task - End to end cases - QA Automation

---

 Some initial features

- Testing UI: End-to-end cases and element validation on the frontend for the following site:
  http://automationpractice.com/index.php

**Development**

Versión: 1.0


### Pre-Requirements📋
---

_Things necessary to run this project_

```
* Java 8 or higher
* Maven
* Drivers you want to run the script in (chromedriver could be a possibility)
```

## Getting Started 🚀
---

### Instalation / Set-up 🔧

_Clonning the project:_

    git clone https://bitbucket.org/CrDev/workstride-e2e/
    
_Go to the root of the previously downloaded project_

_DOWNLOAD & INSTALL all the necessary dependencies and libraries using Maven:_

    mvn package
    mvn build
    mvn clean test

    
### Running Test Suite 🔧




### Reporting 🔧

You do not have to run the application in a special way to generate the reports, they are generated automatically.
When you have run your tests, you will be able to see the Assure HTML report by executing the following command:

    allure serrve

This file will have all the results from the test suite ran and the root cause of failures.


## Built with 🛠️

---

_Tools used in the project_

- [Java 11](https://www.java.com/) - Java
- [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/) - Selenium WebDriver
- [Junit](https://junit.org/junit5/) - Junit
- [Allure Report](http://allure.qatools.ru/) - Allure Report

