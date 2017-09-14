package org.testesComMaven;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

import org.junit.After;

public class LeiloesSystemTest {
	
	private WebDriver driver;
	private LeiloesPage leiloes;
	private ConfiguracaoTeste conf = new ConfiguracaoTeste();
	
	@Before
	public void inicializa () {		
		this.driver = new ChromeDriver();
		driver.get(conf.urlAplicacao() + "/apenas-teste/limpa");
		
		leiloes     = new LeiloesPage(driver);
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita(conf.urlAplicacao() + "/usuarios");
		usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
		usuarios.novo().cadastra("José Alberto", "jose@alberto.com");				
		leiloes = new LeiloesPage(driver);
		leiloes.visita();
		leiloes.novo().preenche("Geladeira", 500, "Paulo Henrique", false);
	}
	
	@After
	public void finaliza () {
		driver.close();
	}
	
	@Test
	public void deveCadastrarUmLeilao () {
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("Geladeira", 1299, "Paulo Henrique", true);
		assertTrue(leiloes.existe("Geladeira", 1299, "Paulo Henrique", true));
	}
	
	@Test
	public void naoDeveAdicionarLeilaoSemNome () {
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("", 1299, "Paulo Henrique", true);
		assertTrue(novoLeilaoPage.cadastrouSemNome());
	}
	
	@Test
	public void naoDeveAdicionarLeilaoSemValor () {
		leiloes.visita();
		NovoLeilaoPage novoLeilaoPage = leiloes.novo();
		novoLeilaoPage.preenche("Geladeira",0, "Paulo Henrique", true);
		assertTrue(novoLeilaoPage.cadastrouSemValor());
	}
	
	@Test
	public void deveFazerUmLance () {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		lances.lance("José Alberto", 150);
		assertTrue(lances.existeLance("José Alberto", 150));
	}
	
	
	
}
