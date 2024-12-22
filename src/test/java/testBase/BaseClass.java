package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.DrbgParameters.Capability;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseClass {


	
public WebDriver driver;
public Logger logger;	
public Properties p;
	
	@SuppressWarnings("deprecation")
	@Parameters({"browser","OS"})
	@BeforeClass(groups = { "sanity","Master"})
	public void setup(String br,String os) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		//execution_env
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			System.out.println("i'm in remote execution**********");
			
			DesiredCapabilities capablities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capablities.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac")) {
				capablities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("Linux")) {
				capablities.setPlatform(Platform.LINUX);//
			}
			else {
				System.out.println("Invalid Platform");
				return;
			}
			
			//browser
			
			
			switch (br.toLowerCase()) {
			case "chrome":
				capablities.setBrowserName("chrome");
				break;
			case "edge":
				capablities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				 capablities.setBrowserName("firefox");
				 break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capablities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			System.out.println("i'm in local execution**********");
			System.out.println("browser name:"+br);
			
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}	
		}
		
		
		driver.get(p.getProperty("appURL")); //Reading URL from Properties file
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	
	@AfterClass(groups = { "sanity","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshorts\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
