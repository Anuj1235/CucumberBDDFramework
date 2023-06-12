package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"D:\\Intellji_Idea_Selenium_Project_Files\\CucumberNewTestNgFramewok\\src\\test\\java\\Features\\Login.feature","D:\\Intellji_Idea_Selenium_Project_Files\\CucumberNewTestNgFramewok\\src\\test\\java\\Features\\Customers.feature"},
        // features = {"D://Intellji_Idea_Selenium_Project_Files//CucumberNewTestNgFramewok//src//test//java//Features"},

        // features = {"D://Intellji_Idea_Selenium_Project_Files//CucumberNewTestNgFramewok//src//test//java//Features"},



        glue="StepDefinition",
        dryRun = false,    //  dryRun = true ---> check the implementation method in stepdefination file with feature file steps -->   e.g. Given User Launch Chrome browser           # StepDefinition.LoginStepDefinition.user_launch_chrome_browser()
        monochrome = true,  // if the         monochrome = false ---> O/p it will not readable format
     tags = "@Sanity",//scenarios under @sanity tag will be executed
       //tags="@Regression",
       //tags="@Sanity or @Regression",
        //tags ="@Sanity and @Regression",
         //tags ="@Sanity and not @Regression",
        //plugin = {"pretty"}
       // plugin = {"pretty","html:target/cucumber-reports/reports_html.html","junit:target/cucumber-reports/reports1.xml","junit:target/cucumber-reports/reports2.xml"}

      //  plugin = {"pretty","junit:target/cucumber-reports/reports1.xml"}
	//plugin = {"pretty","json:target/cucumber-reports/report_json.json"}

        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)

//plugin = {"pretty","html:target/cucumber-reports/reports1.html"}
//		plugin = {"pretty","json:target/cucumber-reports/report_json.json"}

public class TestRunner {

    /*This class will be empty*/
}
