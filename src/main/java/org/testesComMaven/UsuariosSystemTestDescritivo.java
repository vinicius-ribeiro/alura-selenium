package org.testesComMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuariosSystemTestDescritivo {
	
	private ChromeDriver driver;
	private String link = "http://localhost:8080/usuarios";
	
	@Before
	public void inicializa () {
		driver = new ChromeDriver();
	}
	
	@After
	public void finaliza () {
		driver.close();
	}
	
	@Test
	public void deveAdicionarUmUsuario() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\\\\\DevDrivers\\\\chromedriver.exe");
		
		//WebDriver driver = new ChromeDriver();
		
		driver.get(link);
		driver.findElement(By.linkText("Novo Usuário")).click();
		
		WebElement nome  = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		
		nome.sendKeys("Ronaldo Luiz de Albuquerque");
		email.sendKeys("ronaldo2009@terra.com.br");
		
		nome.submit();
		
		boolean achouNome  = driver.getPageSource().contains("Ronaldo Luiz de Albuquerque");
		boolean achouEmail = driver.getPageSource().contains("ronaldo2009@terra.com.br");
		
		assertTrue(achouNome);
		assertTrue(achouEmail);
	}
	
	@Test
	public void naoDeveAdicionarUmUsuarioSemNome() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\\\\\DevDrivers\\\\chromedriver.exe");
		
		//WebDriver driver = new ChromeDriver();		
		driver.get(link);
		driver.findElement(By.linkText("Novo Usuário")).click();
		
		WebElement email = driver.findElement(By.name("usuario.email"));		
		
		email.sendKeys("ronaldo2009@terra.com.br");		
		email.submit();
		
		assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
	}
	
	@Test
	public void naoDeveAdicionarSemNomeSemEmail () {
		System.setProperty("webdriver.chrome.driver", "C:\\\\\\\\DevDrivers\\\\chromedriver.exe");
		
		//WebDriver driver = new ChromeDriver();		
		driver.get(link);
		driver.findElement(By.linkText("Novo Usuário")).click();	
		WebElement email = driver.findElement(By.name("usuario.email"));		
						
		email.submit();
		
		assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
		assertTrue(driver.getPageSource().contains("E-mail obrigatorio!"));
	}
		
}
