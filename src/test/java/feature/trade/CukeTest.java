package feature.trade;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "json:target/cucumber/wikipedia.json",
                "html:target/cucumber/wikipedia.html",
                "pretty"
        },
        tags = {"~@ignored"}
)
public class CukeTest {
}
