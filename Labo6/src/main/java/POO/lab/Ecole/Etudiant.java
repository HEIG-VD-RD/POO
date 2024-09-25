package POO.lab.Ecole;

/**
 * Classe représentant un étudiant.
 *
 * @author Rafaël Dousse
 * @author Aubry Mangold
 */
public class Etudiant extends Personne {
    /**
     * Le matricule d'un étudiant.
     */
    private final int matricule;

    /**
     * Le groupe d'un étudiant.
     */
    private Groupe groupe;

    /**
     * Constructeur pour la classe étudiant
     * @param nom Le nom d'un étudiant.
     * @param prenom Le prénom d'un étudiant.
     * @param matricule Le matricule d'un étudiant.
     */
    public Etudiant(String nom, String prenom, int matricule) {
        super(nom, prenom);
        this.matricule = matricule;
    }

    /**
     * Afficher un étudiant.
     * @return
     */
    public String toString() {
        return String.format("Etud. %s (#%d) %s", super.toString(), this.matricule, groupe != null ? String.format("- %s", groupe.nom()) : "");
    }

    /**
     * Défini le groupe pour un étudiant.
     * @param groupe
     */
    void definirGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
