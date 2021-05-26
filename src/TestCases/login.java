package TestCases;

import PageObjects.loginPage;
import Utilities.base;
import Utilities.commonOps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class login extends base {

    @Test
    public void test01_FailLoginPageTexts() throws IOException, SAXException, ParserConfigurationException
    {

        commonOps.verifyClick(loginP.SignIn);
        loginP.failLoginErrorTexts("333", "password");

        // test that i cant type letters
        loginP.userPhoneNo.clear();
        loginP.userPass.clear();
        loginP.failLoginErrorTexts("11numbers11", "password");

    }

    @Test
    public void test02_FailLoginPageError() throws IOException, SAXException, ParserConfigurationException
    {

        loginP.userPhoneNo.clear();
        loginP.userPass.clear();
        loginP.LoginActions("333333333333", "password");
        commonOps.verifyClick(loginP.afterLoginError);


    }

    @Test
    public void test03_SuccessLogin() throws IOException, SAXException, ParserConfigurationException, InterruptedException
    {

        loginP.userPhoneNo.clear();
        loginP.userPass.clear();
        loginP.LoginActions(getData("MyUserName"), getData("MyPassword"));

    }
}
