package POO.lab.Ecole;

import java.util.Arrays;

public class Profeseur extends Personne {
    /**
     * Abreviation d'une personne
     */
    private final String abreviation;

    /**
     * Tableau de lecons d'un professeur
     */
    private Lecon[] lecons;

    /**
     * Constructeur pour la classe professeur.
     *
     * @param nom
     * @param prenom
     */
    public Profeseur(String nom, String prenom, String abreviation) {
        super(nom, prenom);
        this.abreviation = abreviation;
    }

    public Profeseur(String nom, String prenom, String abreviation, Lecon[] lecons) {
        this(nom, prenom, abreviation);
        this.lecons = Arrays.copyOf(lecons, lecons.length);
    }

    /**
     *
     * @return L'abreviation du professeur.
     */
    public String abreviation() {
        return abreviation;
    }

    /**
     *
     * @return L'horaire du professeur
     */
    public String horaire() {
        return Lecon.horaire(lecons);
    }

    public String toString() {
        return String.format("Prof. %s (%s)", super.toString(), this.abreviation);
    }

    Lecon[] obenirLecons() {
        if (lecons == null)
            return new Lecon[0];
        return Arrays.copyOf(lecons, lecons.length);
    }

    void definirLecons(Lecon[] lecons) {
        this.lecons = Arrays.copyOf(lecons, lecons.length);
    }
}
