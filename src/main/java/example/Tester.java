package example;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.amazonaws.services.lambda.runtime.Context;

public class Tester{
		
	public JSONObject myHandler(JSONObject json, Context context) {
		// TODO Auto-generated method stub
		if(json.getJSONObject("intent").getString("displayName").equals("Bus Schedule")) {
			json.put("fulfillmentText", getBusInfo());
		}
		return json;
	}
	
	public String getBusInfo() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chang_andrew\\Downloads\\chromedriver_win32\\chromedriver.exe");
		  
		  WebDriver driver = new ChromeDriver();
		  
		  
		  driver.get("https://www.google.com/maps/place/San+Gabriel%2F25+1%2F2/@30.2905589,-97.7484636,18z/data=!4m7!3m6!1s0x8644b57bd2ed38bf:0xeaf387148e5a51ec!6m1!1v5!8m2!3d30.2900497!4d-97.7476928/");
		  
		  WebElement appcont = driver.findElement(By.id("app-container"));
		  WebElement contcont = appcont.findElement(By.id("content-container"));
		  WebElement pane = contcont.findElement(By.id("pane"));
		  WebElement widgetpane = pane.findElement(By.className("widget-pane"));
		  WebElement widgetpanecontent = widgetpane.findElement(By.className("widget-pane-content"));
		  WebElement wpch = widgetpanecontent.findElement(By.className("widget-pane-content-holder"));
		  WebElement listbox = wpch.findElement(By.className("section-listbox"));
		  WebElement selection = listbox.findElement(By.className("section-listbox"));
		  
		  List<WebElement> blocks = selection.findElements(By.className("section-line-local"));
		  String re = "";
		  for(WebElement x : blocks) {
			  WebElement box = x.findElement(By.className("section-common-line-headsign-time"));
			  WebElement time = box.findElement(By.className("section-common-line-time"));
			  re += time.getText()+", ";
		  }
		  
		  driver.close();
		  
		  return re;
	}
	

}
