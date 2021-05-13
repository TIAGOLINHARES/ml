
import Tech.Rpa.Controller.MercadoLivreController;


public class Main {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		
		System.out.println("Inicio da Automação");
		MercadoLivreController mercadoLivreController = new MercadoLivreController();
		mercadoLivreController.startFlow();
		
		System.out.println("Fim da Automação");
		
	}


	

	}


