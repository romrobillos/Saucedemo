package testcase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static String ScreenshotFolder;
	public static Properties prop = new Properties();
	public static FileReader fr;
	protected long startTime;
	protected long endTime;
	protected double duration;
	protected String errorMessage, username, password;
	
	
	@BeforeMethod
	public void setUp() throws IOException {
		if (driver == null) {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);
		}
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(prop.getProperty("testurl"));
			driver.manage().window().maximize();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		System.out.println("Closed succesful");
	}

	@DataProvider(name = "allCredentials")
	public Object[][] loginData() {
		return new Object[][] { { "standard_user", "secret_sauce" }, { "locked_out_user", "secret_sauce" },
				{ "visual_user", "secret_sauce" }, { "problem_user", "secret_sauce" },
				{ "performance_glitch_user", "secret_sauce" }, { "error_user", "secret_sauce" },
				{ "invalid_user", "Invalid_pass" }, };
	}

	@DataProvider(name = "validCredentials")
	public Object[][] validData() {
		return new Object[][] { { "standard_user", "secret_sauce" }, { "visual_user", "secret_sauce" },
				{ "problem_user", "secret_sauce" }, { "performance_glitch_user", "secret_sauce" },
				{ "error_user", "secret_sauce" } };
	}

	@DataProvider(name = "ProblemAndErrorUsers")
	public Object[][] ProblemAndError() {
		return new Object[][] { { "problem_user", "secret_sauce" }, { "error_user", "secret_sauce" } };
	}

	// To screenshot using shutterbug
	
	public String getScreenshot(String fileName, String testName) {

		if (ScreenshotFolder == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			ScreenshotFolder = "Test_" + myDateObj.format(myFormatObj);
		}

		File folder = new File("./Screenshots/" + testName + "/" + ScreenshotFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String filePath = folder.getAbsolutePath() + "/" + fileName + ".png";

		Shutterbug.shootPage(driver, Capture.FULL, true).withName(fileName).save(folder.getAbsolutePath());

		System.out.println("Full page screenshot saved successfully: " + filePath);
		return filePath; 
	}

}
