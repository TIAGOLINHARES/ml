
import Tech.Rpa.Controller.MercadoLivreController;


public class Main {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		
		System.out.println("Inicio da Automa��o");
		MercadoLivreController mercadoLivreController = new MercadoLivreController();
		mercadoLivreController.startFlow();
		
		System.out.println("Fim da Automa��o");
		
	}


	

	}


