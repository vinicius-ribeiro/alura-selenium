package org.testesComMaven;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {
	
	private WebDriver driver;

	public UsuariosPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void visita (String link) {
		driver.get(link);
	}
	
	public NovoUsuarioPage novo () {
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}	
	
	public boolean existeNaListagem (String nome, String email) {
		return driver.getPageSource().contains(nome) && driver.getPageSource().contains(email);
	}
	
	public boolean cadastrouSemNome (String mensagem) {
		return driver.getPageSource().contains(mensagem);
	}
	
	public boolean cadastrouEmBranco (String erroNome, String erroEmail) {
		return driver.getPageSource().contains(erroNome) && driver.getPageSource().contains(erroEmail);
	}
	
	public void deletaUsuarioNaPosicao (int posicao) {
		driver.findElements(By.tagName("button")).get(posicao-1).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public AlteraUsuarioPage altera (int posicao) {
		driver.findElements(By.linkText("editar")).get(posicao-1).click();
		return new AlteraUsuarioPage(driver);
	}
	
}
