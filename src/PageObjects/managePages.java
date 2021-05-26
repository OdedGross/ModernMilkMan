package PageObjects;

import Utilities.base;
import org.openqa.selenium.support.PageFactory;

public class managePages extends base {

    //this is where i do all the pages initialization to make Sanity and testCases more  clean
    public static void init()
    {

        loginP = PageFactory.initElements(driver, loginPage.class);
        updatePP = PageFactory.initElements(driver, updateProfilePage.class);
    }
}
