package vue;

/**
 * Panneau principal du jeu
 *
 * contient:
 * - le panneau du donjon
 *
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel{

    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();

    // Montre les villes.
    private PanneauDonjon panDonjon;

    /**
     * Constructeur
     * @param taille La taille de l'ecran
     */
    public PanneauPrincipal() {

        // assigne la tâche
        setSize(tailleEcran);
        setPreferredSize(tailleEcran);

        // initialise les composantes
        initComposantes();
    }


    /*
     * Dimensionne et ajoute les differents panneaux e leur place.
     */
    private void initComposantes() {

        // définit le layout
        setLayout(new BorderLayout());

        // définit le panneau de donjon
        panDonjon = new PanneauDonjon(tailleEcran);
        add(panDonjon, BorderLayout.CENTER);

    }

}
