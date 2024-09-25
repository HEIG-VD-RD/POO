package POO.lab.Ecole;

/**
 * Classe représentant une personne.
 *
 * @author Rafaël Dousse
 * @author Aubry Mangold
 */
public class Personne {
    /**
     * Le nom d'une personne.
     */
    private final String nom;

    /**
     * Le prénom d'une personne.
     */
    private final String prenom;

    /**
     * Constructeur pour la classe personne.
     *
     * @param nom
     * @param prenom
     */
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }


    public String toString() {
        return String.format("%s %s", prenom, nom);
    }
}
