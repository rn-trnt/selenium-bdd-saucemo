package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions", "hook"},
    plugin = {"pretty", "html:target/site/cucumber-pretty.html", "json:target/cucumber.json"},
    tags = "@Checkout"
)

public class TestRunnerCheckout extends AbstractTestNGCucumberTests {
}
