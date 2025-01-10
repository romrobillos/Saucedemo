package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcase.BaseTest;

public class ExtentReportManager extends BaseTest implements ITestListener {

	private static ExtentSparkReporter sparkReporter;
	private static ExtentReports extent;
	private static ExtentTest test;
	private static String username;

	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");

		sparkReporter.config().setDocumentTitle("SauceDemo Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Tester Name", "Jon Romeo Robillos");
		extent.setSystemInfo("OS", "Windows11 Pro");
		extent.setSystemInfo("Browser name", "Chrome");
	}

	public void onTestStart(ITestResult result) {

		if (result.getName().contains("testInvalidLoginErrorMsg")) {
			username = "invalid_user";
		} else if (result.getName().contains("testLockedOutErrorMsg")) {
			username = "locked_out_user";
		} else if (result.getName().contains("testCheckoutBtnCoordinates") || result.getName().contains("testCartCoordinatesForVisualUserOnly")) {
			username = "visual_user";
		} else if (result.getParameters().length > 0 && result.getParameters()[0] instanceof String) {
			username = (String) result.getParameters()[0];
		} else {
			username = "";
		}
		result.setAttribute("username", username);
		System.out.println("Starting " + result.getName() + " for " + username);
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "_" + result.getMethod().getMethodName()).assignCategory(username);
		
		test.info(result.getMethod().getDescription() + " for " + username);
		test.log(Status.PASS, result.getName() + " PASSED!");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "_" + result.getMethod().getMethodName()).assignCategory(username);;
		test.info(result.getMethod().getDescription() + " for " + username);
		test.log(Status.FAIL, result.getName() + " Test case FAILED!");
		test.log(Status.FAIL, "Test Case FAILED due to : " + result.getThrowable());
		String testClassName = result.getTestClass().getName();
	 

		getScreenshot(username + "_" + result.getName(), testClassName);

		String screenshotPath = getScreenshot(username + "_" + result.getName(), testClassName);

		try {
			String styledText = "<b><span style='color:red;'>Screenshot on Failure</span></b>";
			test.addScreenCaptureFromPath(screenshotPath, styledText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "_" + result.getMethod().getMethodName()).assignCategory(username);;
		test.info(result.getMethod().getDescription() + " for " + username);
		test.log(Status.SKIP, result.getName() + " SKIPPED!");
		test.log(Status.SKIP, "Test Case SKIPPED due to : " + result.getThrowable());
		String testClassName = result.getTestClass().getName();
		getScreenshot(username + "_" + result.getName(), testClassName);

		String screenshotPath = getScreenshot(username + "_" + result.getName(), testClassName);

		try {
			String sreenshotText = "<b><span style='color:orange;'>Screenshot</span></b>";
			test.addScreenCaptureFromPath(screenshotPath, sreenshotText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
