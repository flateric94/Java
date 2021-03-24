import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author eric
 */

public class FenetreCycleCouleur extends JFrame{
    Color[] tabc = {Color.red, Color.green, Color.blue};
    int couleurCourante;
    JPanel pano;
    JButton changer;
    
    public FenetreCycleCouleur(){
        couleurCourante = 0;
        pano = new JPanel();
        pano.setPreferredSize(new Dimension(300,300));
        this.add(pano,"Center");
        

        JPanel pn = new JPanel();
        this.add(pn,"South");
        
        changer = new JButton("Changer");
        pn.add(changer);
    
        
        changer.addActionListener(
        
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pano.setBackground(tabc[couleurCourante]);
                couleurCourante ++;
                if (couleurCourante == 3){
                    couleurCourante = 0;
                }
            }                    
            
        }
        );
    
        this.pack();
    }
    
    public static void main(String[] args){
        FenetreCycleCouleur f = new FenetreCycleCouleur();
        f.setVisible(true);
    }
}