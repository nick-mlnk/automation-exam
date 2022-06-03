### Prerequisites

1. Java (1.8) can be installed from [sdkman](https://sdkman.io) (flexible to switch Java versions)
2. [Maven](https://maven.apache.org)

### How to run the tests from command line

1. navigate to automation-repo from CLI `cd /automation-exam`
2. run tests `mvn clean test -Dsuite=cartTestSuite.xml`
3. generate report `mvn allure:serve`
4. open report with a default browser `cd target/surefire-reports && open index.html`