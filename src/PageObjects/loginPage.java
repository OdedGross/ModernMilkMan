package PageObjects;

import Utilities.base;
import Utilities.commonOps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class loginPage extends base {

    public WebDriver driver;

   // commonOps comOps = new commonOps();

    @FindBy(how = How.CLASS_NAME, using = "btn-signin")
    public WebElement SignIn;

    @FindBy(how = How.ID, using = "phoneNo")
    public WebElement userPhoneNo;

    @FindBy(how = How.ID, using = "password")
    public WebElement userPass;

    @FindBy(how = How.ID, using = "btn_submit")
    public WebElement LoginButton;

    @FindBy(how = How.ID, using = "remember_me")
    public WebElement rememeberMe;

    // ----------------------------------- errors ----------------------------------
    @FindBy(how = How.ID, using = "error")
    public WebElement beforeLoginError;

    @FindBy(how = How.CLASS_NAME, using = "swal2-confirm")
    public WebElement afterLoginError;



    public loginPage(WebDriver driver)
    {
        this.driver = driver;
    }


    public void LoginActions(String phoneNum, String phonePass) throws IOException, SAXException, ParserConfigurationException
    {
        commonOps.verifyClick(userPhoneNo);
        userPhoneNo.sendKeys(phoneNum);
        commonOps.verifyClick(userPass);
        userPass.sendKeys(phonePass);
        commonOps.verifyClick(rememeberMe);
        commonOps.verifyClick(LoginButton);

    }

    public void failLoginErrorTexts(String phoneNum, String phonePass) throws IOException, SAXException, ParserConfigurationException
    {
        commonOps.verifyClick(userPhoneNo);
        userPhoneNo.sendKeys(phoneNum);
        commonOps.verifyClick(userPass);
        commonOps.verifyElementExist(beforeLoginError);
        commonOps.verifyValueExist(loginP.beforeLoginError, "Please enter a valid phone number" );
        commonOps.verifyClick(LoginButton);
        commonOps.verifyElementExist(beforeLoginError);
        commonOps.verifyValueExist(loginP.beforeLoginError, "Phone number should be of minimum 11 digits." );
    }



}
