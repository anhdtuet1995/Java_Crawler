/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrolldownup;

/**
 *
 * @author Anh
 */
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

public class HandleLogFile {
    public WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anh\\Desktop\\chromedriver_win32\\chromedriver.exe");        
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
    }
    
    public void setForDriver(String web){
        driver.get(web);
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public String analyzeLog() {
        String a = "";
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            a = entry.getMessage();
        }
        return a;
    }
    
    public void infiniteScrollDown(int quantityScroll) throws InterruptedException{
        String a = "";
        for(int i=1; i< quantityScroll; i++){
            ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            System.out.println(i);
            //a = a + analyzeLog();
           
            Thread.sleep(1000);   
        }
        
        //return a;
    }
}
