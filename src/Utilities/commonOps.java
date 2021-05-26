package Utilities;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class commonOps extends base {

    //need to return here to develop reporting.

    //function that verify element, need to get WebElement to see if it does exists or not
    public static void verifyElementExist(WebElement elem) throws ParserConfigurationException, SAXException, IOException {
        try {
            elem.isDisplayed();
            System.out.println("Element Exists");
            test.log(LogStatus.PASS, "Element Exists");
        } catch (Exception e) {
            System.out.println("Element does not Exists! ");
            test.log(LogStatus.FAIL, "Element Not Exists, see screenshot " + test.addScreenCapture(takeSS()));
            fail("Element does not Exists");
        }

    }

    //Function that verify if a value exsist
    public static void verifyValueExist(WebElement elem, String expectedValue) throws ParserConfigurationException, SAXException, IOException {
        try {
            String actual = elem.getText();
            assertEquals(expectedValue, actual);
            System.out.println("Value Exists");
            test.log(LogStatus.PASS, "Value Exists");
        } catch (Exception e) {
            System.out.println("Value does not Exists! ");
            test.log(LogStatus.FAIL, "Value Not Exists, see screenshot " + e.getMessage() + test.addScreenCapture(takeSS()));
            fail("Element does not Exists");

        } catch (AssertionError ea) {
            System.out.println("Assert Failed " + ea.getMessage());
            test.log(LogStatus.FAIL, "Assert Failed " + ea.getMessage() + test.addScreenCapture(takeSS()));
            fail("the value was different then the original");
        }

    }

    //function that select from a drop down.
    public static void selectDropDown(WebElement elem, String valueMenu) throws ParserConfigurationException, SAXException, IOException {
        try {
            elem.click();
            Select myValue = new Select(elem);
            myValue.selectByValue(valueMenu);
            System.out.println("Element Selected");
            test.log(LogStatus.PASS, "Element Selected");
        } catch (Exception e) {
            System.out.println("Element was not Selected! ");
            test.log(LogStatus.FAIL, "Element Not Selected, see screenshot " + e.getMessage() + test.addScreenCapture(takeSS()));
            fail("Element was not Selected");
        }

    }

    public static void verifyClick(WebElement elem) throws ParserConfigurationException, SAXException, IOException {
        try {
            elem.click();
            System.out.println("Element found and Clicked");
            test.log(LogStatus.PASS, "Element found and Clicked");
        } catch (Exception e) {
            System.out.println("Element is not Clickable! ");
            test.log(LogStatus.FAIL, "Element was not clickable, see screenshot " + test.addScreenCapture(takeSS()));
            fail("Element is not Clickable!");
        }
    }

}
