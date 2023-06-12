package BaseClass;


import PageObjectClass.AddNewCustomerPage;
import PageObjectClass.LoginPage;
import PageObjectClass.SearchCustomerPage;
import Utilities.ReadConfig;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/*Parent Class*/
public class Base {

    public WebDriver driver;

    public LoginPage loginPg;

    public AddNewCustomerPage addNewCustomerPage;

    public SearchCustomerPage SerachCustpg;

    public static Logger log;

    public ReadConfig readConfig;



    //generate unique email id
    public String generateEmailId()
    {
        return(RandomStringUtils.randomAlphabetic(5));
    }













}
