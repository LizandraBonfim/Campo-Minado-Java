package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoRealEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultadoEsquerda = campo.adicionarVizinho(vizinho);

		assertTrue(resultadoEsquerda);
	}

	@Test
	void testeVizinhoRealDireita() {

		Campo vizinho = new Campo(3, 4);
		boolean resultadoDireita = campo.adicionarVizinho(vizinho);

		assertTrue(resultadoDireita);
	}

	@Test

	void testeVizinhoRealEmCima() {

		Campo vizinho = new Campo(2, 3);
		boolean resultadoCima = campo.adicionarVizinho(vizinho);

		assertTrue(resultadoCima);
	}

	@Test

	void testeVizinhoRealEmBaixo() {

		Campo vizinho = new Campo(4, 3);
		boolean resultadoBaixo = campo.adicionarVizinho(vizinho);

		assertTrue(resultadoBaixo);
	}

	@Test

	void testeVizinhoRealDiagonal() {

		Campo vizinho = new Campo(2, 2);
		boolean resultadoBaixo = campo.adicionarVizinho(vizinho);

		assertTrue(resultadoBaixo);
	}

	@Test

	void testeNaoVizinho() {

		Campo vizinho = new Campo(1, 1);
		boolean resultadoBaixo = campo.adicionarVizinho(vizinho);

		assertFalse(resultadoBaixo);
	}

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {

		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoENaoMarcado() {
		campo.minar();

		assertThrows(ExplosaoException.class, () -> {

			campo.abrir();
		});
	}

	@Test
	void testeAbrirComVizinhos1() {

		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);

		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);

		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	void testeAbrirComVizinhos2() {

		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);

		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		campo.adicionarVizinho(campo22);

		campo.abrir();

//		assertTrue(campo22.isAberto() && !campo11.isAberto());
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}

}
