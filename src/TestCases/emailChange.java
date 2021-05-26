package TestCases;

import Utilities.base;
import Utilities.commonOps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class emailChange extends base {

    @Test
    public void test01_FailEmailUpdates() throws IOException, SAXException, ParserConfigurationException  {

        commonOps.verifyClick(loginP.SignIn);
        loginP.LoginActions(getData("MyUserName"), getData("MyPassword"));
        updatePP.enterEditAccount();
        updatePP.changeEmail(getData("BadEmailAdress"));
        commonOps.verifyValueExist(updatePP.invalidSave, "Please enter the valid email");

    }

    @Test
    public void test02_SuccessEmailUpdate() throws IOException, SAXException, ParserConfigurationException  {

        updatePP.emailAdress.clear();
        updatePP.changeEmail(getData("EmailAdress"));

    }


}
