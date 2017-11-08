package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class One extends Panel implements ActionListener {
	private Button lb1, lb2;
	private BorderLayout blay;
	private CardLayoutUse cl;
	private Image img;
	private String pic = "C:\\Users\\jack\\Desktop\\test.png";
	private Panel p;

	public One(CardLayoutUse cl) {
		this.cl = cl;

		blay = new BorderLayout();
		lb1 = new Button("다음");
		lb2 = new Button("이전");
		p = new Panel();

		p.add(lb1);
		p.add(lb2);
		this.setLayout(blay);
		this.add(p, "North");

		lb1.addActionListener(this);
		lb2.addActionListener(this);
		img = Toolkit.getDefaultToolkit().getImage(pic);
	}

	public void paint(Graphics g){
		g.drawImage(img, 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(lb1)){
			cl.nextPanel();
		} else if(e.getSource().equals(lb2)){
			cl.prevPanel();
		}
	}
}
