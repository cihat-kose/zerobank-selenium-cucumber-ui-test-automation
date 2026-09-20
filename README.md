# ZeroBank Selenium Cucumber UI Test Automation

[![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)](https://cucumber.io/)
[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-FF7300?style=for-the-badge&logo=testng&logoColor=white)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![Apache POI](https://img.shields.io/badge/Apache%20POI-231F20?style=for-the-badge&logo=apache&logoColor=white)](https://poi.apache.org/)

Java 21 UI tests for the [ZeroBank demo](http://zero.webappsecurity.com/login.html), using Selenium,
Cucumber, TestNG, Page Objects, and Apache POI. Smoke tests check the main user journeys; regression
includes those journeys, additional data sets, Excel login, and invalid credentials.

## Quick start

1. Install **JDK 21** and **Microsoft Edge**. Set `JAVA_HOME` to the JDK directory, not its `bin` folder.
2. Clone this repository, or download and extract its ZIP. Open a terminal in the directory containing `pom.xml`.
3. Run the smoke suite:

**Windows PowerShell**

```powershell
java -version
.\mvnw.cmd clean test
```

**macOS / Linux**

```sh
java -version
sh mvnw clean test
```

The Maven Wrapper downloads **Maven 3.9.11** automatically. A separate Maven installation or IDE is not required.
The first run downloads dependencies and a matching browser driver, so it takes longer.
[Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/) manages driver downloads and caching.

A successful smoke run reports **3 tests, 0 failures**. Open `target/reports/smoke.html` for the HTML report.

See the committed [sample Smoke PDF report](docs/sample-report/SmokeTestReport.pdf) for an example of the generated
test evidence. It is a static demonstration artifact; current run outputs remain under `target/` or `testReports/`.

## Prerequisites

- JDK 21; newer JDKs have not been verified for this project.
- Edge for the default suite. The parallel suite also requires Firefox. Chrome can be selected separately.
- Internet access to Maven Central, browser driver downloads, and the ZeroBank demo.
- Write access to Maven/Selenium caches and the project's output folders.
- A desktop session for visible browsers, or `-Dheadless=true` for Edge, Chrome, and Firefox.
- Safari requires macOS and WebDriver support enabled; headless Safari is unsupported.

Check the JVM used by Maven with `.\mvnw.cmd -version` on Windows or `sh mvnw -version` on macOS/Linux.
The wrapper fixes the Maven version; dependency versions are defined in `pom.xml`.

## Test suites

| Suite | Selection | Scenarios | Purpose |
| --- | --- | ---: | --- |
| Smoke (default) | `@Smoke` | 3 | Valid login, one payee creation, one currency purchase |
| Regression | `@Regression` | 10 | All smoke coverage, two more payees, two more currencies, Excel login, two invalid login cases |
| Parallel | Smoke on Edge and Firefox | 6 | The same three smoke journeys on two concurrent browsers |
| Reports | Smoke with Extent adapter | 3 | Spark HTML and PDF output |

Smoke is a subset of regression. `@Smoke` on an Examples block selects only its representative row;
other data rows remain regression-only. `@Authentication`, `@Payee`, `@Currency`, `@Excel`, and `@Negative`
provide additional filtering. Each scenario gets a fresh browser session.

The suites verify authenticated home-page access and username, rejection of invalid credentials, the
created payee's name in the confirmation, a numeric currency conversion result, and purchase confirmation.
This is coverage of the implemented demo flows, not exhaustive banking-system or financial-calculation validation.

## Commands

Run these from the repository root. Use one suite profile per invocation.
On macOS/Linux, replace `.\mvnw.cmd` with `sh mvnw`.

```powershell
# Fast smoke check (also the default without -Psmoke)
.\mvnw.cmd clean test -Psmoke

# Full implemented regression coverage
.\mvnw.cmd clean test -Pregression

# Smoke in parallel on Edge and Firefox
.\mvnw.cmd clean test -Pparallel

# Smoke with Spark HTML and PDF reports
.\mvnw.cmd clean test -Preports

# Headless regression, suitable for a configured CI agent
.\mvnw.cmd clean test -Pregression "-Dheadless=true"

# Run the default smoke suite on Chrome
.\mvnw.cmd test "-Dbrowser=chrome"

# Select a subset of regression
.\mvnw.cmd test -Pregression "-Dcucumber.filter.tags=@Negative"

# Validate Gherkin/step matching without browser execution
.\mvnw.cmd test -Pregression "-Dcucumber.execution.dry-run=true"

# Verify concurrent Excel logging without a browser
.\mvnw.cmd test "-Dtest=ExcelUtilityTest"

# Compile, run smoke tests, and package
.\mvnw.cmd clean package
```

A dry run validates step matching only; it does not prove application behavior.
The main JAR may be empty because this repository contains test sources only. Use Maven/TestNG to execute the tests.

Existing runner classes can still be selected with `-Dtest=TestRunnerAll`, `-Dtest=TestRunnerRegression`,
`-Dtest=TestRunnerSmokeWithHtmlAndJson`, or `-Dtest=TestRunnerExtentReport`.
Do not combine `-Dtest` with the parallel profile, because explicit test selection overrides suite XML selection.

## Configuration

The complete `src/test/resources/cucumber.properties` is:

```properties
cucumber.glue=stepDefinitions
cucumber.publish.quiet=true
```

`glue` limits discovery to the project's step definitions and hooks, avoiding scans of unrelated dependency classes.
`publish.quiet` suppresses Cucumber's publishing banner; it does not enable report upload.
Tags and report plugins are selected by the runners rather than globally, so direct feature runs remain independent.

| Option | Default | Behavior |
| --- | --- | --- |
| `-Dbrowser=edge` | `edge` | `edge`, `chrome`, `firefox`, or `safari` |
| `-Dheadless=true` | `false` | Run without a browser window (except Safari) |
| `-Dresults.path=target/results/ScenarioResults.xlsx` | Shown path | Excel result destination |

`browserType` in a TestNG XML test takes precedence over `-Dbrowser`. The parallel suite uses Edge and Firefox.
Drivers and browser selection are isolated per thread; driver sessions are closed even when result logging fails.
Excel read/append/write operations are serialized within one JVM. Separate Maven processes must use different
`results.path` values. Keep output workbooks closed in Excel during a run.

## Reports and test data

| Run/output | Location |
| --- | --- |
| Every Maven test run | `target/surefire-reports/` |
| Default smoke HTML / JSON | `target/reports/smoke.html`, `target/reports/smoke.json` |
| Regression HTML / JSON | `target/cucumber-report-regression.html`, `target/cucumber-report-regression.json` |
| Legacy smoke HTML/JSON runner | `target/reports/smoke-html-report.html`, `target/reports/smoke-json-report.json` |
| Legacy HTML runner | `target/site/cucumber-pretty.html` |
| Scenario status and browser | `target/results/ScenarioResults.xlsx` |
| Extent Spark | `testReports/testReport <timestamp>/test-output/SparkReport/Spark.html` |
| Extent PDF | `testReports/testReport <timestamp>/PdfReport.pdf` |

The parallel runner uses TestNG/Surefire reports and Excel output so browser instances do not overwrite a shared
Cucumber HTML/JSON report. Failed scenarios attach screenshots when a browser session is available; Cucumber/Extent
report runners can display these attachments.

`src/test/resources/extent.properties` enables both Spark and PDF reporters:

```properties
extent.reporter.spark.start=true
extent.reporter.spark.out=test-output/SparkReport/Spark.html
basefolder.name=testReports/testReport
basefolder.datetimepattern=d-MMM-YY HH.mm.ss
screenshot.dir=test-output/screenshots/
screenshot.rel.path=../screenshots/
extent.reporter.pdf.start=true
extent.reporter.pdf.out=PdfReport.pdf
```

Excel login reads `src/test/java/apachePOI/ZeroBankData.xlsx`, sheet `Sheet1`, cells **F5/G5**.
The demo credentials are `username` / `password`. Payee/currency input comes from Gherkin Examples tables.
The old `src/test/java/apachePOI/ScenarioResults.xlsx` is historical output; new runs write under `target/`.
`clean` clears `target/`; timestamped Extent reports under `testReports/` are retained.

## IntelliJ IDEA

Open `pom.xml` as a Maven project, select JDK 21 for the project SDK and Maven runner, and reload Maven.
Use the Maven Wrapper as Maven home. Cucumber for Java and Gherkin plugins are needed for direct `.feature` runs.

Run a class under `src/test/java/runners/`, a TestNG XML suite, or a feature file. Set the working directory to the
repository root. Resources must be on the test classpath; build the project after changing `.properties` files.
If an existing Cucumber run configuration explicitly sets Glue, use `stepDefinitions`.
Direct feature runs execute that file and do not inherit a runner's tags or report plugins.

## CI and demo-site behavior

A Jenkins or other CI agent can run the headless commands above after JDK 21 and the selected browsers are installed.
No Jenkins pipeline or hosted CI configuration is bundled. Archive `target/surefire-reports/`, `target/reports/`,
the regression report files, and `testReports/` as appropriate to the selected suite.

The public demo redirects successful login to a problematic HTTPS endpoint. The login step returns to the HTTP
home page while retaining the session, then verifies the authenticated username. Firefox's specific
`nssFailure2` error on the demo's `auth/accept-certs.html` redirect is handled; unrelated WebDriver failures propagate.
This workaround does not validate the site's HTTPS redirect or account-summary page.
Availability and changes to the external demo can affect test results. Tests use demo data only.

## Project structure

```text
.mvn/wrapper/                 Maven distribution configuration
mvnw, mvnw.cmd                Maven launchers
pom.xml                      Dependencies and suite profiles
src/test/java/
  features/                  Gherkin scenarios and data sets
  pages/                     Page Objects and explicit waits
  stepDefinitions/           Scenario steps and lifecycle hooks
  runners/                   Cucumber/TestNG suite selection
  utilities/                 Browser and Excel utilities, Excel concurrency test
  xml/                       Smoke, regression, parallel TestNG suites
  apachePOI/                 Excel login input and historical results
src/test/resources/          Cucumber and Extent configuration
target/                      Build output, standard reports, Excel results
testReports/                 Timestamped Extent reports
```

## Dependencies

| Component | Version |
| --- | --- |
| Java target | 21 |
| Maven Wrapper distribution | 3.9.11 |
| Selenium Java | 4.49.0 |
| Cucumber Java / TestNG integration | 7.19.0 |
| TestNG | 7.10.2 |
| Apache POI / POI OOXML | 5.4.0 |
| ExtentReports Cucumber 7 adapter | 1.14.0 |
| Maven Surefire | 3.5.1 |
| Maven Compiler | 3.13.0 |

## Verification

Local validation on 20 September 2026 used Windows 11, JDK 21, Edge 153, and Firefox 155:

- Clean default build: 3/3 smoke scenarios passed with visible Edge.
- Headless Edge regression: 10/10 scenarios passed.
- Parallel headless Edge/Firefox: 6/6 scenarios passed, with both browser names recorded in Excel.
- Extent runner: 3/3 smoke scenarios passed; Spark HTML and PDF files were generated.
- Excel concurrency test: an existing row plus 20 concurrent appends were preserved with the correct browser values.
- Direct Cucumber dry run: all 90 steps matched without classpath scanning warnings.

Chrome, Safari, macOS, and Linux were not exercised in this validation.

## Contributing and license

Create a branch, keep smoke scenarios representative and fast, tag all regression coverage with `@Regression`,
and run the relevant suites before opening a pull request. Do not commit generated reports or real credentials.
Report issues through this repository's issue tracker. Licensed under the [MIT License](LICENSE).
