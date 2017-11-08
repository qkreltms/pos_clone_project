package ui;

import java.awt.*;
import java.awt.event.*;

public class CardLayoutUse extends Frame{
	private CardLayout cards;
	private Panel panel;

	public CardLayoutUse() {    	
		cards = new CardLayout();
		panel = new Panel(cards);

		panel.add("First", new One(this));
		panel.add("Second", new Two(this));
		panel.add("Third", new Three(this));

		this.add(panel);
		this.setSize(500, 500);	
		this.addWindowListener(new WinExit());
	}

	public void nextPanel() {
		cards.next(this.panel); 
	}
	
	public void prevPanel() {
		cards.previous(this.panel); 
	}

	public static void main(String[] args) {
		CardLayoutUse cl= new CardLayoutUse();
		cl.setVisible(true);
	}
}


//������ x��ư ������ â�ݱ� Ȱ��ȭ
class WinExit extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}

