package StepDefinition;

import BaseClass.Base;
import PageObjectClass.AddNewCustomerPage;
import PageObjectClass.LoginPage;
import PageObjectClass.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// child class of baseclass
public class StepDefinition extends Base {

     /*
  1.Note : @Before method will be executed increase (forward order from 0 to 1 ) order wise executed , First it will executed  @Before (order=0) method
  2.Note:   @Before ("@Sanity") @After("@Sanity") it is Conditional Hooks , it will before & after executed  @Sanity @ Regression mention tag Scenario
  3.Note : BeforestepmethodDemo() & AfterstepmethodDemo() it will executed before and after each steps scenario mention in feature file
  4.Note : Class name mention "StepDefinition" & same name mention in log4j.properties file --->  logger.file.name=StepDefinition
*/
   // @Before(order=1)
    //@Before ("@Sanity")

    @Before ("@Sanity")
    public  void SetUp1()  {

        readConfig = new ReadConfig();

        //initialise logger
        log = LogManager.getLogger("StepDefinition");

        System.out.println("Setup-Sanity method executed..");

        String browser = readConfig.getBrowser();

        //launch browser
        // used switch case statement
        switch(browser.toLowerCase())
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "msedge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                driver = null;
                break;

        }


        log.fatal("Setup1 executed...");

        driver.manage().window().maximize();
    }


/*
    // @Before (order=0)

    //@Before("@Regression")
    public  void SetUp2(){


        System.out.println("steup2 method executed.....");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();



    }
*/



    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {

        loginPg = new LoginPage(driver);

        addNewCustomerPage = new AddNewCustomerPage(driver);  //allocated memory

          SerachCustpg = new SearchCustomerPage(driver);

        log.info("chrome browser launched=======");



    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {

        driver.get(url);

        log.info("url opened =========");

    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String emailadd, String password) {

        loginPg.enterEmail(emailadd);
        loginPg.enterPassword(password);

        log.info("email address and password entered ==========");

    }

    @When("Click on Login")
    public void click_on_login() {

        loginPg.clickOnLoginButton();

        log.info("Clicked on login button=========");

    }



    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedTitle) {

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle))
        {
            log.warn("Test passed: Login feature :Page title matched.====");

            Assert.assertTrue(true);//pass
        }
        else
        {
            Assert.assertTrue(false);//fail
           log.warn("Test Failed: Login feature- page title not matched.====");


        }

    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link () {

      loginPg.clickOnLogOutButton();

        log.info("user clicked on logout link.======");

    }




        @And("close browser")
        public void close_browser () {


        driver.close();
       // driver.quit();
            log.info("Browser closed====");

        }

        ////////////////////Add new Customers/////////////////////


    @Then("User can view Dashboad")
    public void user_can_view_dashboad() {

        String actualTitle = addNewCustomerPage.getPageTitle();

        String expectedTitle = "Dashboard / nopCommerce administration";

        if(actualTitle.equals(expectedTitle)){

            log.info("user can view dashboard test passed.====");

            Assert.assertTrue(true);
        }

        else {


            Assert.assertTrue(false);

            log.warn("user can view dashboard test failed.====");
        }

    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() {

        addNewCustomerPage.clickOnCustomersMenu();

        log.info("customer menu clicked===");
    }

    @And("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {


        addNewCustomerPage.clickOnCustomersMenuItem();

        Thread.sleep(2000);
        log.info("customer menu item clicked==");
    }

    @And("click on Add new button")
    public void click_on_add_new_button() {

        addNewCustomerPage.clickOnAddnew();
        log.info("clicked on add new button.");

    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {

        String actualTitle = addNewCustomerPage.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";

        if(actualTitle.equals(expectedTitle))
        {
           log.info("User can view Add new customer page- passed==");

            Assert.assertTrue(true);//pass
        }
        else
        {
            log.info("User can view Add new customer page- failed==");

            Assert.assertTrue(false);//fail
        }



    }


        @When("User enter customer info")
        public void user_enter_customer_info() throws InterruptedException {


        //addNewCustomerPage.enterEmail("Sameertest@gmail.com");
            addNewCustomerPage.enterEmail(generateEmailId()  + "@gmail.com");
        addNewCustomerPage.enterPassword("Test123");
        addNewCustomerPage.enterFirstName("Sameer");
        addNewCustomerPage.enterLastName("Deshmukh");
        addNewCustomerPage.enterGender("Male");
        addNewCustomerPage.enterDob("6/13/1995");
        addNewCustomerPage.enterCompanyName("SSDF");
        addNewCustomerPage.enterAdminContent("Admin content");
        addNewCustomerPage.enterManagerOfVendor("Vendor 196");



            log.info("customer information entered====");


        }

        @And("click on Save button")
        public void click_on_save_button() {

        addNewCustomerPage.clickOnSave();
            log.info("clicked on save button=====");

        }

        @Then("User can view confirmation message {string}")
        public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {

            String bodyTagText = driver.findElement(By.tagName("Body")).getText();
            if(bodyTagText.contains(exptectedConfirmationMsg))
            {
                Assert.assertTrue(true);//pass
                log.info("User can view confirmation message - passed===");

            }
            else
            {
               log.warn("User can view confirmation message - failed====");

                Assert.assertTrue(false);//fail

            }



        }


        ///////////////////////////// Search Customerpage//////////////////////////////////////

    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        SerachCustpg.enterEmailAdd("victoria_victoria@nopCommerce.com");
        log.info("Email address entered===");

    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {


        SerachCustpg.clickOnSearchButton();


        Thread.sleep(2000);

        log.info("Clicked on search button.====");
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {

        String expectedName = "Victoria Terces";


        if (SerachCustpg.searchCustomerByName(expectedName) == true) {
            Assert.assertTrue(true);
            log.info("User should found Email in the Search table - passed==");
        } else {

            log.info("User should found Email in the Search table - passed==");
            Assert.assertTrue(false);


        }

    }
    ////////////////////////search  Customer by Name ////////////////////////



    @When("Enter customer FirstName")
    public void enter_customer_first_name() {

        SerachCustpg.enterFirstName("Victoria");

    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {

        SerachCustpg.enterLastName("Terces");

    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {

            String expectedName = "Victoria Terces";


            if( SerachCustpg.searchCustomerByName(expectedName) ==true)
            {
                Assert.assertTrue(true);
            }
            else
                Assert.assertTrue(false);

        }


//@After (order=0)   //@After method will be executed decreasing (reverse from order wise 1 to 0 ) order wise executed , First it will executed  @After (order=1) method
//@After("@Sanity")  // Conditional Hooks

   // @After("@Sanity")
    public  void teardown1(Scenario sc){


        System.out.println("Tear Down method executed..");
        if(sc.isFailed()==true)
        {
            //Convert web driver object to TakeScreenshot

            String fileWithPath = "D:\\Intellji_Idea_Selenium_Project_Files\\CucumberNewTestNgFramewok\\Screenshot\\failedScreenshot.png";
            TakesScreenshot scrShot =((TakesScreenshot)driver);

            //Call getScreenshotAs method to create image file
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
            File DestFile=new File(fileWithPath);

            //Copy file at destination

            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }




        driver.quit();
    }




    @AfterStep
    public void addScreenshot(Scenario scenario){

        //validate if scenario has failed
        if(scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png",scenario.getName());
        }

    }








    /*
   // @After (order=1)


    @After("@Regression")
    public  void teardown2(){

        System.out.println("Tear down2 method executed........");

        driver.quit();
    }

    */
    //  Note : BeforestepmethodDemo() & AfterstepmethodDemo() it will executed before and after each steps scenario


    @BeforeStep
    public void BeforestepmethodDemo(){


        System.out.println("This is Before step-----");
    }



/*
    @AfterStep

    public void AfterstepmethodDemo(){


        System.out.println("This is After step-----");
    }

*/








    }








