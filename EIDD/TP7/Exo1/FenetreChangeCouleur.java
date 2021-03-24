import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreChangeCouleur extends JFrame{
	JPanel pano;
	JButton br,bv,bj;
	
		
	public FenetreChangeCouleur(){
		pano=new JPanel();
		pano.setPreferredSize(new Dimension(300,300));
		this.add(pano,"Center");
		

		JPanel pn = new JPanel();
		this.add(pn,"North");

		// question 3)
		JPanel pn2 = new JPanel();
		this.add(pn2,"South");

		// br = new JButton("Rouge");  //énoncé
		// question 2)
		bj = new JButton("Jaune");
		// pn.add(br);   // énoncé
		// question 2)
		pn.add(bj);
		bv = new JButton("Vert");
		pn.add(bv);   //énoncé
		// question 3)
		pn2.add(bv);
		
		// br.addActionListener(    //énoncé
		bj.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// pano.setBackground(Color.red);    //énoncé
					// question 2)
					pano.setBackground(Color.yellow);

				}					
				
			}
		);
		
		bv.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pano.setBackground(Color.green);
				}					
				
			}
		);
		this.pack();
	}
	
	public static void main(String[] args){
			FenetreChangeCouleur f = new FenetreChangeCouleur();
			f.setVisible(true);
	}

}
