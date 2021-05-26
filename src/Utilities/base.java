package Utilities;

import PageObjects.loginPage;
import PageObjects.updateProfilePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.sikuli.script.Screen;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class base {

    public static WebDriver driver;
    public static Screen screen;
    public static ExtentReports  extent;
    public static ExtentTest test;
    public static String timeStamp = new SimpleDateFormat("yyyy-mm-dd_HH-mm-ss").format(Calendar.getInstance().getTime());


    public static loginPage loginP;
    public static updateProfilePage updatePP;

    //---------------open Params from external file---------------------
    public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
    {
        File fXmlFile = new File("C://Seleniumstuff/example.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    //---------------------------Browser Selection, open Browser--------------------------
    public static void initBrowser(String browserType) throws ParserConfigurationException, SAXException, IOException {
        switch (browserType.toLowerCase())
        {
            case "firefox":
                driver = initFFDriver();
                break;
            case "ie":
                driver = initIEDriver();
                break;
            case "chrome":
                driver = initChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.navigate().to(getData("URL"));  //go to the URL you want
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        screen = new Screen();

    }



    public static WebDriver initFFDriver() throws IOException, SAXException, ParserConfigurationException {
        System.setProperty("webdriver.gecko.driver", getData("FFDriverPath"));
        WebDriver driverff = new FirefoxDriver();
        return driverff;
    }

    public static WebDriver initIEDriver() throws IOException, SAXException, ParserConfigurationException {
        System.setProperty("webdriver.ie.driver", getData("IEDriverPath"));
        WebDriver driverie = new InternetExplorerDriver();
        return driverie;
    }

    public static WebDriver initChromeDriver() throws IOException, SAXException, ParserConfigurationException {
        System.setProperty("webdriver.chrome.driver", getData("ChromeDriverPath"));
        WebDriver driver = new ChromeDriver();
        return driver;
    }


    //----------------------------------------- Reports -------------------------------------------------
    public static void  InstanceReport() throws IOException, SAXException, ParserConfigurationException
    {
        extent = new ExtentReports(getData("ReportFilePath") + getData("ReportFileName") + timeStamp + ".html", true);
    }

    public static void initReportTest(String testName, String testDescription)
    {
        test = extent.startTest(testName, testDescription);
    }

    public static void finalizeReportTest()
    {
        extent.endTest(test);
    }

    public static void FinalizeExtentTest()
    {
        extent.flush();
        extent.close();
    }

    //Screenshot
    public static String takeSS() throws IOException, ParserConfigurationException, SAXException
    {
        String SSpath = getData("SSPath") + "screenshot_" + getRandomNumber() + ".png";
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(SSpath));
        return SSpath;
    }

    //random number generator for File names
    public static int getRandomNumber()
    {
        Random rand = new Random();
        int n = rand.nextInt(99999) + 1000;
        return n;
    }

    //-------------------------------- all the annotations that are not @Test -----------------------------

    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void openBrowser() throws IOException, SAXException, ParserConfigurationException
    {
        initBrowser(getData("BrowserType"));
        InstanceReport();
        PageObjects.managePages.init();
    }

    @After
    public void doAfterTest()
    {
        finalizeReportTest();
    }

    @Before
    public void doBeforeTest()
    {
        initReportTest(name.getMethodName().split("_")[0], name.getMethodName().split("_")[1]);
    }

    @AfterClass
    public static void BrowserClose() throws InterruptedException
    {
        FinalizeExtentTest();
        Thread.sleep(5000);
        driver.quit();
    }
}
