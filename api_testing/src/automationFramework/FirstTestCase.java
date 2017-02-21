package automationFramework;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class FirstTestCase {
	
	private static final String srcApiFile = "C:\\BENCH\\AutomationAPI\\src_api_list.txt";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		

		
		Calendar tDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(tDate.getTime()) ;
		String file_name = "C:/BENCH/AutomationAPI/"+sDate+"_test_api.txt";
	
		WebDriver driver;
	    System.setProperty("webdriver.gecko.driver", "C:\\BENCH\\geckodriver.exe");
	    driver = new FirefoxDriver();
	     
			try (BufferedReader br = new BufferedReader(new FileReader(srcApiFile))) {

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
				     driver.get(sCurrentLine);
				     String out = driver.findElement(By.tagName("body")).getText();
				     Thread.sleep(2000);
				     WriteFile data = new WriteFile(file_name, true);
				     data.writeToFile(sCurrentLine + "\n" + "TEST RESULT: " + out + "\n");
				}        

		}
	     
		catch (IOException e) {
			e.printStackTrace();
		}
	    }   
	}

