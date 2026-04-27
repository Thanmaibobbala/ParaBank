# ParaBank Test Automation Framework

A Selenium + TestNG test automation framework built in Java for the [ParaBank](https://parabank.parasoft.com/parabank/index.htm) demo banking application. It follows the **Page Object Model (POM)** design pattern and includes HTML reporting via ExtentReports and automatic screenshot capture on failures.

---

## Tech Stack

| Tool / Library        | Version   | Purpose                            |
|-----------------------|-----------|------------------------------------|
| Java                  | 17        | Language                           |
| Selenium WebDriver    | 4.21.0    | Browser automation                 |
| TestNG                | 7.10.2    | Test execution & lifecycle         |
| WebDriverManager      | 5.8.0     | Automatic ChromeDriver setup       |
| ExtentReports         | 5.1.1     | HTML test reporting                |
| Apache Commons IO     | 2.15.1    | Screenshot file handling           |
| Maven                 | —         | Build & dependency management      |

---

## Project Structure

```
parabank-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # WebDriverWait helpers
│   │   │   │   └── DriverFactory.java     # Browser init & teardown
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java         # Login / Logout actions
│   │   │   │   ├── RegisterPage.java      # New user registration
│   │   │   │   ├── AccountPage.java       # Account overview & details
│   │   │   │   ├── TransferPage.java      # Fund transfer
│   │   │   │   ├── TransactionPage.java   # Find transactions
│   │   │   │   └── ValidationPage.java    # Form validation checks
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java      # Reads config.properties
│   │   │       ├── ExtentManager.java     # Singleton ExtentReports
│   │   │       └── ScreenshotUtil.java    # On-failure screenshots
│   │   └── resources/
│   │       └── Config.Properties          # Browser, URL, timeout config
│   └── test/
│       └── java/
│           ├── listeners/
│           │   └── TestListener.java      # TestNG listener (pass/fail/screenshot)
│           └── tests/
│               ├── BaseTest.java          # @BeforeClass / @AfterClass setup
│               ├── AuthTest.java          # Registration & login tests
│               ├── AccountTest.java       # Account overview test
│               ├── TransferTest.java      # Fund transfer test
│               ├── TransactionTest.java   # Transaction search test
│               └── ValidationTest.java   # Empty-form validation tests
├── reports/
│   └── extent-report.html                 # Generated after test run
├── screenshots/                           # Captured on test failure
└── pom.xml
```

---

## Configuration

Edit `src/main/resources/Config.Properties` to change the target environment:

```properties
browser=chrome
baseUrl=https://parabank.parasoft.com/parabank/index.htm
timeout=10
```

| Property  | Description                          |
|-----------|--------------------------------------|
| `browser` | Browser to use (`chrome` supported)  |
| `baseUrl` | Application URL                      |
| `timeout` | Implicit wait in seconds             |

---

## Prerequisites

- **Java 17+** installed and `JAVA_HOME` set
- **Maven** installed
- **Google Chrome** installed (ChromeDriver is managed automatically by WebDriverManager)

---

## Running the Tests

### Run all tests

```bash
mvn test
```

### Run a specific test class

```bash
mvn test -Dtest=AuthTest
mvn test -Dtest=TransferTest
```

---

## Test Coverage

| Test Class         | Test(s)                                              | Description                                       |
|--------------------|------------------------------------------------------|---------------------------------------------------|
| `AuthTest`         | `registerUser`, `testLogin` (data-driven)            | New user registration + valid/invalid login       |
| `AccountTest`      | `testAccountOverview`                                | Verifies account table and account detail page    |
| `TransferTest`     | `testFundTransfer`                                   | Transfers $100 between accounts                   |
| `TransactionTest`  | `testTransactionSearchByAmount`                      | Searches transactions by amount ($100)            |
| `ValidationTest`   | `testRegisterValidation`, `testBillPayValidation`    | Empty form submission error messages              |

---

## Reporting

After each test run, an HTML report is generated at:

```
reports/extent-report.html
```

Open it in any browser to see a summary of passed/failed tests with embedded screenshots for failures.

---

## Screenshot on Failure

`TestListener` automatically captures a screenshot when any test fails and attaches it to the Extent report. Screenshots are saved to:

```
screenshots/<testName>_<timestamp>.png
```

The `TestListener` must be registered in your TestNG suite XML or via Maven Surefire configuration. Example `testng.xml`:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="ParaBank Suite">
    <listeners>
        <listener class-name="listeners.TestListener"/>
    </listeners>
    <test name="All Tests">
        <classes>
            <class name="tests.AuthTest"/>
            <class name="tests.AccountTest"/>
            <class name="tests.TransferTest"/>
            <class name="tests.TransactionTest"/>
            <class name="tests.ValidationTest"/>
        </classes>
    </test>
</suite>
```

Run with:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## Framework Design

- **Page Object Model (POM)** — each page of the application has a dedicated class with locators and actions, keeping tests clean and maintainable.
- **BasePage** — all page classes extend `BasePage`, which provides a shared `WebDriverWait` instance.
- **DriverFactory** — manages a single static `WebDriver` instance across the test session.
- **ConfigReader** — externalises environment config so tests don't hard-code URLs or credentials.
- **TestNG DataProvider** — `AuthTest.testLogin` is data-driven, running with both valid and invalid credentials from a single test method.

---

## Default Test Credentials

The demo site provides a built-in user for testing:

| Username | Password |
|----------|----------|
| `john`   | `demo`   |

Registration tests generate a unique username at runtime using `"user" + System.currentTimeMillis()`.
