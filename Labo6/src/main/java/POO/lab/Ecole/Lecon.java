package POO.lab.Ecole;

import java.util.Arrays;

public class Lecon {
    private final static int LARGEUR_CHAMP = 13;
    private final static String SEPARATEUR_VERTICAL = "|";
    private final static String SEPARATEUR_HORIZONTAL = "-".repeat(LARGEUR_CHAMP);
    private final static String SEPARATEUR_LIGNE = String.format("%s%s", SEPARATEUR_VERTICAL, SEPARATEUR_HORIZONTAL, SEPARATEUR_VERTICAL);
    private final static String[] JOURS = {"Lun", "Mar", "Mer", "Jeu", "Ven"};
    private final static String[] PERIODES = {"8:30", "9:15", "10:25", "11:15", "12:00", "13:15", "14:00", "14:55", "15:45", "16:35", "17:20"};
    private final int jourSemaine;
    private final int periodeDebut;
    private final int duree;
    private final String salle;
    private final String matiere;
    private Profeseur professeur;

    /**
     * Constructeur pour la classe lecon.
     *
     * @param matiere      La matière d'une lecon.
     * @param jourSemaine  Le jour de la semaine d'une lecon.
     * @param periodeDebut La période de début d'une lecon.
     * @param duree        La durée en période d'une lecon.
     * @param salle        La salle d'une lecon.
     */
    public Lecon(String matiere, int jourSemaine, int periodeDebut, int duree, String salle) {
        this.matiere = matiere;
        this.jourSemaine = jourSemaine;
        this.periodeDebut = periodeDebut;
        this.duree = duree;
        this.salle = salle;
    }


    public Lecon(String matiere, int jourSemaine, int periodeDebut, int duree, String salle, Profeseur professeur) {
        this(matiere, jourSemaine, periodeDebut, duree, salle);
        if (professeur != null) {
            this.professeur = professeur;
            Lecon[] lecons = professeur.obenirLecons();
            Lecon[] nlleLecons = Arrays.copyOf(lecons, lecons.length + 1);
            nlleLecons[lecons.length] = this;
            professeur.definirLecons(nlleLecons);
        }
    }

    /**
     * Permet d'afficher un horaire pour des lecons.
     *
     * @param lecons Les lecons à afficher.
     * @return L'horaire sous forme de chaîne de caractères.
     */
    public static String horaire(Lecon... lecons) {
        final Lecon[][] horaire = matriceLecons(lecons);

        // Creation de la représentation textuelle de l'horaire.
        StringBuilder sb = new StringBuilder();
        for (Lecon[] jour : horaire) {
            for (Lecon lecon : jour) {
                if (lecon != null) {
                    final String leconString = String.format("%-6s%3s %3s",
                            lecon.matiere,
                            lecon.salle,
                            lecon.professeur != null ? lecon.professeur.abreviation() : ""
                    );
                    sb.append(SEPARATEUR_VERTICAL).append(leconString);
                } else {
                    sb.append(SEPARATEUR_VERTICAL).append(" ".repeat(LARGEUR_CHAMP));
                }
            }
            sb.append(SEPARATEUR_VERTICAL).append("\n");
        }

//        StringBuilder sb = new StringBuilder("ENTETE ICI");
//        sb.append(SEPARATEUR_HORIZONTAL.repeat(JOURS.length)).append(SEPARATEUR_VERTICAL).append("\n");
//        return sb.toString();
        return sb.toString();
    }

    private static Lecon[][] matriceLecons(Lecon... lecons) {
        Lecon[][] matrice = new Lecon[PERIODES.length][JOURS.length];
        for (Lecon lecon : lecons) {
            for (int i = 0; i < lecon.duree; i++) {
                matrice[lecon.periodeDebut + i][lecon.jourSemaine] = lecon;
            }
        }
        return matrice;
    }
}
