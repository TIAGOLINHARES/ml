import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","resources\\chromedriver.exe");;
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.mercadolivre.com.br/");
		driver.findElement(By.xpath("//div/form[@class='nav-search']/input")).sendKeys("Celular");
		driver.findElement(By.xpath("//div/form/button")).click();

		List<WebElement> linksProdutos = driver.findElements(By.xpath("//div[@class='ui-search-result__image']/a"));
		List<String> urlProdutos = new ArrayList<String>();
		
		
		for (WebElement webElement : linksProdutos) {
			urlProdutos.add(webElement.getAttribute("href"));
		}	
		
		for (String currentUrl : urlProdutos) {
			driver.get(currentUrl);
			
			String nomeProduto = driver.findElement(By.xpath("//h1[@class='ui-pdp-title']")).getText();
			String precoProduto = driver.findElement(By.xpath("//span[@class=\"price-tag ui-pdp-price__part\"]/span[@class=\"price-tag-fraction\"]")).getText();
			
			System.out.println(nomeProduto + "-" + precoProduto);
			
		}
			
		
		driver.close();
		driver.quit();
			
			
		
		}
		
		
		
	}


