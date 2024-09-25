package POO.lab.Ecole;

import java.util.Arrays;

public class Groupe {

    private final int numero;

    private final String orientation;

    private final int trimestre;

    private final Etudiant[] etudiants;

    private Lecon[] lecons;

    /**
     * Constructeur pour la classe groupe.
     * @param numero
     * @param orientation
     * @param trimestre
     * @param etudiants
     */
    public Groupe(int numero, String orientation, int trimestre, Etudiant[] etudiants) {
        this.numero = numero;
        this.orientation = orientation;
        this.trimestre = trimestre;

        this.etudiants = new Etudiant[etudiants.length];
        for (int i = 0; i < etudiants.length; i++) {
            etudiants[i].definirGroupe(this);
            this.etudiants[i] = etudiants[i];
        }
    }

    /**
     *
     * @return L'horaire d'un groupe.
     */
    public String horaire() {
        return Lecon.horaire(lecons);
    }

    /**
     *
     * @return Le nom du groupe.
     */
    public String nom() {
        return String.format("%s%d-%d", orientation, trimestre, numero);
    }

    /**
     *
     * @return Nombres d'étudiant d'un groupe.
     */
    public int nombreEtudiants() {
        return etudiants.length;
    }

    /**
     * Permet de définir les leçons pour un groupe de personnes
     * @param lecons Les leçons à définir.
     */
    public void definirLecons(Lecon[] lecons) {
        this.lecons = Arrays.copyOf(lecons, lecons.length);
    }
}
