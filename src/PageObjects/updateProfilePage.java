package PageObjects;

import Utilities.commonOps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class updateProfilePage {

    public WebDriver driver;

    @FindBy(how = How.CSS, using = "li[class='account dropdown']" )
    public WebElement myprofile;

    @FindBy(how = How.CSS, using = "a[href='https://tst.themodernmilkman.co.uk/users/myAccount']")
    public  WebElement myaccount;

    @FindBy(how = How.CSS, using = "img[src='/img/images/Edit account.svg']")
    public  WebElement editAccount;

    @FindBy(how = How.ID, using = "email")
    public  WebElement emailAdress;

    @FindBy(how = How.CLASS_NAME, using = "update-bttn")
    public WebElement saveBttn;

    @FindBy(how = How.ID, using = "error-otp")
    public WebElement invalidSave;






    public void enterEditAccount() throws IOException, SAXException, ParserConfigurationException {
        commonOps.verifyClick(myprofile);
        commonOps.verifyClick(myaccount);
        commonOps.verifyClick(editAccount);
    }

    public void changeEmail(String eMail) throws IOException, SAXException, ParserConfigurationException {
        commonOps.verifyClick(emailAdress);
        emailAdress.sendKeys(eMail);
        commonOps.verifyClick(saveBttn);
    }


    public updateProfilePage(WebDriver driver)
    {
        this.driver = driver;
    }


}
