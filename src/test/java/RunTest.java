import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = {"pretty","json:target/MyReports/report.json",
                "html:target/MyReports/HTMLReports"}, glue = {"stepDefinitions"}, tags ="@sanity",
                 features = {"src/test/java/features/Asana.feature"})
public class RunTest {}
