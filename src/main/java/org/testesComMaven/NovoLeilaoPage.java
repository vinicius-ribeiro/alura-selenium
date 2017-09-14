package org.testesComMaven;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoLeilaoPage {

	private WebDriver driver;

	public NovoLeilaoPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void preenche (String nome, double valor, String usuario, boolean usado) {
		
		WebElement campoNome  = driver.findElement(By.name("leilao.nome"));
		WebElement campoValor = driver.findElement(By.name("leilao.valorInicial"));
		
		campoNome.sendKeys(nome);
		campoValor.sendKeys(String.valueOf(valor));
		
		WebElement combo = driver.findElement(By.name("leilao.usuario.id"));
		org.openqa.selenium.support.ui.Select comboUsuario = new org.openqa.selenium.support.ui.Select(combo);
		comboUsuario.selectByVisibleText(usuario);
		
		if (usado) {
			WebElement ckUsado = driver.findElement(By.name("leilao.usado"));
			ckUsado.click();
		}		
		campoNome.submit();		
	}
	
	public boolean cadastrouSemNome () {
		return driver.getPageSource().contains("Nome obrigatorio!");
	}
	
	public boolean cadastrouSemValor () {
		return driver.getPageSource().contains("Valor inicial deve ser maior que zero!");
	}
	
}
