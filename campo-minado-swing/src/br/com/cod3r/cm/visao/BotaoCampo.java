package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO = new Color(0, 100, 0);

	public BotaoCampo(Campo campo) {
		// TODO Auto-generated constructor stub
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));

		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		// TODO Auto-generated method stub
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;

		case MARCAR:
			aplicarEstiloMarcar();
			break;

		case EXPLODIR:
			aplicarEstiloExplodir();
			break;


		default:
			aplicarEstiloPadrao();
			break;
		}
		
		SwingUtilities.invokeLater(() ->
		{
			repaint();
			validate();
		});
	}

	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub
		
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");

	}

	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("*");
		

	}

	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub
		
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
	

	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMarcado()) {
			setBackground(BG_EXPLODIR);
			return;
		}

		setBackground(BG_PADRAO);

		switch (campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO);

			break;
		case 2:
			setForeground(Color.BLUE);
			break;

		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:

			setForeground(Color.PINK);
			break;
		}
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";	
		
		setText(valor);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		if (e.getButton() == 1) {
			System.out.println("Btn esquerdo");
			campo.abrir();
			
		} else {
			campo.alternarMarcacao();
		}
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
