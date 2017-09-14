package org.testesComMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlteraUsuarioPage {

	private WebDriver driver;

	public AlteraUsuarioPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public UsuariosPage para (String nome, String email) {
		WebElement campoNome  = driver.findElement(By.name("usuario.nome"));
		WebElement campoEmail = driver.findElement(By.name("usuario.email"));		
			campoNome.clear();
			campoEmail.clear();
			
			campoNome.sendKeys(nome);
			campoEmail.sendKeys(email);
			
			campoEmail.submit();
		return new UsuariosPage(driver);
	}
	
}
