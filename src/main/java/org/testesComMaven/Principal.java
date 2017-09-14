package org.testesComMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class Principal {

	public static void main(String[] args) {
		//System.setProperty("webdriver.gecko.driver", "C:\\\\\\\\DevDrivers\\\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\\\\\\\DevDrivers\\\\chromedriver.exe");
		System.out.println("Entrou no main!");

		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();	
		
		/*
		driver.get("http://www.google.com.br/");		
		WebElement campoTexto = driver.findElement(By.name("q"));
		campoTexto.sendKeys("Administração pública para municipios");		
		campoTexto.submit();*/
		
		driver.get("http://localhost:8080/usuarios/new");
		WebElement nome  = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("Ronaldo Luiz de Albuquerque");
		email.sendKeys("ronaldo2009@terra.com.br");
		
		nome.submit();
		
		boolean achouNome  = driver.getPageSource().contains("Ronaldo Luiz de Albuquerque");
		boolean achouEmail = driver.getPageSource().contains("ronaldo2009@terra.com.br");
		
		assertTrue(achouNome);
		assertTrue(achouEmail);
		
		driver.close();	
		
	}
	
}
