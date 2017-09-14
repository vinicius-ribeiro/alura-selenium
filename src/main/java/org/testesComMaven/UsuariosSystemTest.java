package org.testesComMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuariosSystemTest {
	
	private WebDriver driver;
	private String link = new ConfiguracaoTeste().urlAplicacao() + "/usuarios";
	private UsuariosPage usuarios;
	private String nome  = "Vinicius Ribeiro";
	private String email = "vinicius.ribeiro@adpmnet.com.br";
	
	@Before
	public void inicializa () {		
		this.driver   = new ChromeDriver();
		driver.get(new ConfiguracaoTeste().urlAplicacao() + "/apenas-teste/limpa");
		this.usuarios = new UsuariosPage(driver);		
	}
	
	@After
	public void finaliza () {
		driver.close();
	}
	
	@Test
	public void deveAdicionarUmUsuario() {
		usuarios.visita(link);
		usuarios.novo().cadastra(nome, email);		
		assertTrue(usuarios.existeNaListagem(nome, email));		
	}
	
	@Test
	public void naoDeveAdicionarUmUsuarioSemNome () {
		usuarios.visita(link);
		usuarios.novo().cadastra("",email);
		assertTrue(usuarios.cadastrouSemNome("Nome obrigatorio!"));
	}
	
	@Test
	public void naoAdicionaUsuarioEmBranco () {
		usuarios.visita(link);
		usuarios.novo().cadastra("","");
		assertTrue(usuarios.cadastrouEmBranco("Nome obrigatorio!", "E-mail obrigatorio!"));
	}
	
	@Test
	public void deveExcluirUsuario () {
		usuarios.visita(link);
		usuarios.novo().cadastra(nome, email);		
		usuarios.deletaUsuarioNaPosicao(1);		
		assertFalse(usuarios.existeNaListagem(nome, email));
	}
	
	@Test
	public void deveAlterarUsuario () {
		usuarios.visita(link);
		usuarios.novo().cadastra(nome, email);
		usuarios.altera(1).para("Nome que foi alterado", "alterado@adpmnet.com.br");
		assertFalse(usuarios.existeNaListagem(nome, email));
		assertTrue(usuarios.existeNaListagem("Nome que foi alterado", "alterado@adpmnet.com.br"));
	}
		
}
