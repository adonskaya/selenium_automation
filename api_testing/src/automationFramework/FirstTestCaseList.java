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

public class FirstTestCaseList {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		
		Calendar tDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(tDate.getTime()) ;
		String file_name = "C:/BENCH/workspace/selenium_automation/api_testing/"+sDate+"_test_api.txt";
	
		WebDriver driver;
	    System.setProperty("webdriver.gecko.driver", "C:\\BENCH\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    
		BufferedReader scan_input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter system address: ");
		String cmd = scan_input.readLine();
		String srcApiFile = "C:\\BENCH\\workspace\\selenium_automation\\api_testing\\"+"src_"+cmd+"_list.txt";
	     
			try (BufferedReader br = new BufferedReader(new FileReader(srcApiFile))) {

				String sCurrentLine;
				BufferedReader new_input = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter system address: ");
				String sys_address = new_input.readLine();
				System.out.print("Enter realm: ");
				String r = new_input.readLine();
				System.out.print("Enter password: ");
				String p = new_input.readLine();
				System.out.print("Enter list_id: ");
				String list_id = new_input.readLine();
				System.out.print("Enter list_name: ");
				String list_name = new_input.readLine();

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

