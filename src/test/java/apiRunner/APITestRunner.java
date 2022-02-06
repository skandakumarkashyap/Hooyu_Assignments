package apiRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/apiTests",glue = {"apiStepDefinitions"},
        monochrome = true,
        strict = true,
        stepNotifications = true, 
        plugin = { "pretty" }
)
public class APITestRunner {
}
