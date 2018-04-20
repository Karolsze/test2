/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import javax.swing.*;
import modele.*;
import controleur.*;
import java.awt.*;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabyGraphique extends JFrame implements ActionListener
{
 private JPanel pan ; // panneau
 private JPanel pan2 ; // panneau
 private JButton boutons[][]; // matrice de boutons
 private JPanel container = new JPanel();
 private JButton bouton1 = new JButton("Avancer en profondeur");
 private JButton bouton2 = new JButton("Avancer aléatoirement");
 private JButton bouton3 = new JButton("Deplacement manuel");
 private JButton haut = new JButton("Avancer en HAUT");
 private JButton bas = new JButton("Avancer en BAS");
 private JButton droite = new JButton("Avancer en DROITE");
 private JButton gauche = new JButton("Avancer en GAUCHE");
 private JLabel label = new JLabel("Bienvenu dans le labyrinthe, choisissez la resolution");
 private Labyrinthe laby;
 private int reponse;
 
 public LabyGraphique (Labyrinthe labynew){ // constructeur
     
    laby=labynew;
    setTitle ("Mon labyrinthe");
    setSize (1080, 720);
    pan = new JPanel(); // instancier le panneau
    reponse=0;
    JPanel south = new JPanel();
    getContentPane().add(pan); // ajouter le panneau dans la fenêtre
 }
 
public int getreponse()
 {
     return reponse;
 }
 public void setreponse(int rep)
 {
    reponse=rep;
 }
 
 //methode qui affiche le menu
 public void menu(Labyrinthe laby,String nomlaby)
 {   
     
     //On met les boutons en bas
     JPanel south = new JPanel();
     south.add(bouton1,BorderLayout.SOUTH);
     south.add(bouton2,BorderLayout.SOUTH);
     south.add(bouton3,BorderLayout.SOUTH);
     pan.add(south, BorderLayout.SOUTH);
     
     //on active les boutons avec listener 
     bouton1.addActionListener(this);
     bouton2.addActionListener(this);
     bouton3.addActionListener(this);
     //on détails comment sera écrit la phrase
    /* Font police = new Font("Tahoma", Font.BOLD, 16);
     label.setFont(police);
     label.setForeground(Color.blue);
     label.setHorizontalAlignment(JLabel.CENTER);
     container.add(label, BorderLayout.CENTER);
     this.setContentPane(container);*/
     
     this.setVisible(true);
 }
 
 //Methode si on clique sur un bouton
 @Override
 public void actionPerformed(ActionEvent arg0) 
 {
  if(arg0.getSource() == bouton1)
  {

      TestLaby.deplacerDFS(Labyrinthe.getDepartY(), Labyrinthe.getDepartX());
  }
  if(arg0.getSource() == bouton2)
  {
      TestLaby.deplacerAuto();
  }
  if(arg0.getSource() == bouton3)
  {
      TestLaby.afficheManu();
  }
  if(arg0.getSource() == haut)
  {
      if (!Labyrinthe.getCase(laby.getCurrentPositionY()-1, laby.getCurrentPositionX()).getVisited()) {
            try {
                laby.move(laby.getCurrentPositionY()-1, laby.getCurrentPositionX());
            } catch (ImpossibleMoveException ex) {
                Logger.getLogger(LabyGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      TestLaby.afficheManu();
  }
  if(arg0.getSource() == bas)
  {
      // si la case pas visitée, s'y déplacer
      
        if (!Labyrinthe.getCase(laby.getCurrentPositionY()+1, laby.getCurrentPositionX()).getVisited()) {
            try {
                laby.move(laby.getCurrentPositionY()+1, laby.getCurrentPositionX());
            } catch (ImpossibleMoveException ex) {
                Logger.getLogger(LabyGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      TestLaby.afficheManu();
      
     //on augment d'un y
  }
  if(arg0.getSource() == droite)
  {
      //on augmente d'un x
      if (!Labyrinthe.getCase(laby.getCurrentPositionY(), laby.getCurrentPositionX()+1).getVisited()) {
            try {
                laby.move(laby.getCurrentPositionY(), laby.getCurrentPositionX()+1);
            } catch (ImpossibleMoveException ex) {
                Logger.getLogger(LabyGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      TestLaby.afficheManu();
  }
  if(arg0.getSource() == gauche)
  {
     //On diminue d'un x
      if (!Labyrinthe.getCase(laby.getCurrentPositionY(), laby.getCurrentPositionX()-1).getVisited()) {
            try {
                laby.move(laby.getCurrentPositionY(), laby.getCurrentPositionX()-1);
            } catch (ImpossibleMoveException ex) {
                Logger.getLogger(LabyGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      TestLaby.afficheManu();
  }
}

 public void afficheTout(Labyrinthe laby)
 {

     affiche(laby);
     haut.addActionListener(this);
     bas.addActionListener(this);
     droite.addActionListener(this);
     gauche.addActionListener(this);
     
     this.getContentPane().add(haut, BorderLayout.NORTH);
     this.getContentPane().add(bas, BorderLayout.SOUTH);
     this.getContentPane().add(droite, BorderLayout.EAST);
     this.getContentPane().add(gauche, BorderLayout.WEST);
     
     this.setVisible(true);
 }
// Méthode qui affiche la grille du labyrinthe
 public void affiche(Labyrinthe laby) 
 {
 
 pan.removeAll();
 pan.setLayout(new GridLayout(laby.getTailleY(), laby.getTailleX())); // mise en forme avec une grille
 boutons = new JButton[laby.getTailleY()][]; // instancier les lignes de la matrice de boutons
     
 ImageIcon icone = new ImageIcon("image/mur.jpg");//affichage de l'image
 ImageIcon icon = new ImageIcon("image/pacman.jpg");//affichage de l'image
 ImageIcon ico = new ImageIcon("image/herbe.jpg");//affichage de l'image
 
 JLabel image = new JLabel(icone);//instance de l'icone
 JLabel imag = new JLabel(icon);//instance de l'icone
 JLabel ima = new JLabel(ico);//instance de l'icone
     
 for (int i = 0; i < laby.getTailleY(); i++)
 boutons[i] = new JButton[laby.getTailleX()];// Pour chaque ligne de la matrice, instancier les boutons
 // Ajouter les boutons dans le panneau
 for (int i = 0; i < laby.getTailleY(); i++) {
 for (int j = 0; j < laby.getTailleX(); j++) {
 boutons[i][j] = new JButton(); // instancier chaque bouton
 //JLabel image = new JLabel( new ImageIcon( "mur.jpeg")); //instancie l'image
 pan.add(boutons[i][j]);

 Case c=laby.getCase(i,j);
 if (c instanceof CaseMur) {   
                        //pan.add(image);
                        //boutons[i][j].setText("M");
                        boutons[i][j].add(new JLabel(icone));

                } else {
                    if (c.getVisited()) {
                        //boutons[i][j].setText("v");
                         boutons[i][j].add(new JLabel(icon));
                    } else {
                        if(c instanceof CaseTrou)
                        {
                             boutons[i][j].add(new JLabel(ico));
                        }
                        //boutons[i][j].setText("_");
                        
                    }
                }
 }
 }
 //pan.addMouseListener((MouseListener) this);
 // rendre la fenetre visible
 this.setVisible(true);
 
} 
}