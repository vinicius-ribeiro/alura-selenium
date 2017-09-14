package org.testesComMaven;

import java.util.List;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalhesDoLeilaoPage {

	private WebDriver driver;

	public DetalhesDoLeilaoPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void lance (String usuario, double valor) {
		WebElement campoValor = driver.findElement(By.name("lance.valor"));
		WebElement combo      = driver.findElement(By.name("lance.usuario.id"));
		org.openqa.selenium.support.ui.Select comboUsuario   = new org.openqa.selenium.support.ui.Select(combo);
		
		comboUsuario.selectByVisibleText(usuario);
		campoValor.sendKeys(String.valueOf(valor));
		
		driver.findElement(By.id("btnDarLance")).click();
	}
	
	
	public boolean existeLance (String usuario, double valor) {
        Boolean temUsuario =
                new WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                    .textToBePresentInElement(By.id("lancesDados"), usuario));

        if(temUsuario) return driver.getPageSource().contains(String.valueOf(valor));
        return false;
    }
}
