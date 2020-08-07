package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		
		try {
			boolean continuar = true;
			
			while(continuar) {
				
				cicloDoJogo();
				
				System.out.println("Outra partida? s ou n ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}
				else {
					tabuleiro.reiniciar();
				}
			}
			
		} catch (SairException e) {
			// TODO: handle exception
			System.out.println("Tchau!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {

		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite x e y: " );
				
			 Iterator<Integer> xy =	Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
			 
//			 System.out.println(xy.next());
//			 System.out.println(xy.next());
			 
			 
			 digitado = capturarValorDigitado("1-Abrir e 2-(Des)Marcar: ");
			 
			 if("1".equals(digitado)){
				 tabuleiro.abrir(xy.next(), xy.next());
				 
			 } else if("2".equals(digitado)) {
				 tabuleiro.alternarMarcacao(xy.next(), xy.next());
			 }
			 
			 
			}
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!! ");
			
		} catch (ExplosaoException e) {
			// TODO: handle exception
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu. " );
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}

}
