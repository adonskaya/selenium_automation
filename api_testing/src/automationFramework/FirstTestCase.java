package automationFramework;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTestCase {
	
	private static final String srcApiFile = "C:\\BENCH\\workspace\\selenium_automation\\api_testing\\src_api_list.txt";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
			
		Calendar tDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(tDate.getTime()) ;
		String file_success = "C:/BENCH/workspace/selenium_automation/api_testing/SUCCESS_RESULTS/"+sDate+"_api.txt";
		String file_failure = "C:/BENCH/workspace/selenium_automation/api_testing/FAILURE_RESULTS/"+sDate+"_api.txt";
		String file_other = "C:/BENCH/workspace/selenium_automation/api_testing/OTHER_RESULTS/"+sDate+"_api.txt";
	
		WebDriver driver;
	    System.setProperty("webdriver.gecko.driver", "C:\\BENCH\\geckodriver.exe");
	    driver = new FirefoxDriver();
	     
			try (BufferedReader br = new BufferedReader(new FileReader(srcApiFile))) {

				String sCurrentLine;
				BufferedReader new_input = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter system address: ");
				String sys_addr = new_input.readLine();
				System.out.print("Enter realm: ");
				String r = new_input.readLine();
				System.out.print("Enter password: ");
				String p = new_input.readLine();

				while ((sCurrentLine = br.readLine()) != null) {
					 String command = "http://"+sys_addr+"/api_web?r="+r+"&p="+p+"&"+sCurrentLine; 
				     driver.get(command);
				     String out = driver.findElement(By.tagName("body")).getText();
				     String success = "SUCCESS";
				     String failure = "FAILURE";
				     if (out.toUpperCase().contains(success.toUpperCase())) {
					     Thread.sleep(2000);
					     WriteFile success_results = new WriteFile(file_success, true);
					     success_results.writeToFile(command + "\n" + "TEST RESULT: " + out + "\n");
				     } else if (out.toUpperCase().contains(failure.toUpperCase())) {
				     Thread.sleep(2000);
				     WriteFile failure_results = new WriteFile(file_failure, true);
				     failure_results.writeToFile(command + "\n" + "TEST RESULT: " + out + "\n");
				     } else {
					     Thread.sleep(2000);
					     WriteFile other_results = new WriteFile(file_other, true);
					     other_results.writeToFile(command + "\n" + "TEST RESULT: " + out + "\n");
				     }
				}        
		}
	     
		catch (IOException e) {
			e.printStackTrace();
		}
	    }   
	}

