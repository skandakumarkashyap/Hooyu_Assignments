package uiRunner;

import org.junit.runner.RunWith;

import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/userInterfaceTests",glue = {"uiStepDefinitions"},
        monochrome = true,
        strict = true,
        stepNotifications = true, 
        plugin = { "pretty" }
)
public class UITestRunner {


}
