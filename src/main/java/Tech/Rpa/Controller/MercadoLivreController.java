package Tech.Rpa.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Tech.Rpa.model.Produto;

public class MercadoLivreController {

	public void startFlow() {
		List<String> products = new ArrayList<>();
		products.add("Celular");
		// products.add("Notebook");
		// products.add("Tv");

		List<Produto> productsOutput = buscarProdutos(products);

		ExcelController excelController = new ExcelController();
		excelController.writeOuteputFile(productsOutput);

	}

	private List<Produto> buscarProdutos(List<String> products) {

		WebDriver driver = new ChromeDriver(getChromeOptions());
		List<Produto> productsOutput = new ArrayList<>();

		for (String produtoNome : products) {
			driver.get("https://lista.mercadolivre.com.br/" + produtoNome);

			List<WebElement> linksProdutos = driver.findElements(By.xpath("//div[@class='ui-search-result__image']/a"));
			List<String> urlProdutos = new ArrayList<>();

			for (WebElement webElement : linksProdutos) {
				urlProdutos.add(webElement.getAttribute("href"));
			}

			for (String currentUrl : urlProdutos) {
				driver.get(currentUrl);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				//js.executeAsyncScript("alert('olá mundo')");

				String nomeProduto = (String) js.executeScript("return document.querySelector('h1[class=ui-pdp-title]').textContent ");
				String precoProduto = (String) js.executeScript("return document.querySelector('div[class=ui-pdp-price__second-line] span span span[class=price-tag-fraction]').firstChild.textContent");
				
				//String nomeProduto = driver.findElement(By.xpath("//h1[@class='ui-pdp-title']")).getText();
				//String precoProduto = driver.findElement(By.xpath(
					//	"//div[@class='ui-pdp-price__second-line']/span/span/span[@class='price-tag-fraction'][1]"))
						//.getText();

				Produto produto = new Produto();

				produto.setNome(nomeProduto);
				produto.setPreco(precoProduto);
				produto.setUrl(currentUrl);
				productsOutput.add(produto);

			}

		}

		driver.close();
		driver.quit();

		return productsOutput;

	}

	private ChromeOptions getChromeOptions() {

		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePreferences = new HashMap<String, Object>();

		// 0 = default 1 = Allow 2 = Block
		// precisa adicionar um option para funcionar

		chromePreferences.put("profile.managed_default_content_settings.images", 2);

		options.addArguments("--start-maximized");
		// options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePreferences);

		return options;

	}

}
