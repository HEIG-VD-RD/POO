import POO.lab.Ecole.*;

/**
 * Programme de test pour le laboratoire 6 "École".
 *
 * @author Rafaël Dousse
 * @author Aubry Mangold
 */
public class Ecole {
    /**
     * 1. Définir les professeurs *Daniel Rossier (DRE)* et *Pier Donini (PDO)*.
     * 2. Définir les trois leçons du cours de *POO (PDO)*, la leçon du cours *SYE (DRE)* ainsi qu’une leçon *TIC (projet non encadré)*.
     * 3. Définir les étudiants *John Lennon*, *Paul Mc Cartney*, *Ringo Starr*, *George Harisson*, *Roger Waters* et *David Gilmour*.
     * 4. Définir un groupe *IL6-1* contenant les quatre premiers étudiants et un groupe *SI6-1* contenant les deux derniers.
     * 5. Affecter au groupe *IL6-1* toutes les leçons existantes. Affecter au groupe *SI6-1* toutes les leçons de *POO*.
     * 6. Définir un tableau contenant toutes les personnes et en afficher les éléments en tirant parti du mécanisme de liaison dynamique.
     * 7. Afficher les informations relatives au groupe *IL6-1* (nom, nombre d’étudiants, horaire).
     * 8. Afficher l’horaire du professeur *PDO*.
     */
    public static void main(String[] args) {
        Profeseur p1 = new Profeseur("Rossier", "Daniel", "DRE");
        Profeseur p2 = new Profeseur("Donini", "Pier", "PDO");

        Lecon poo1 = new Lecon("POO", 2, 5, 2, "H02", p2);
        Lecon poo2 = new Lecon("POO", 3, 5, 2, "H02", p2);
        Lecon poo3 = new Lecon("POO", 3, 7, 2, "H02", p2);
        Lecon sye1 = new Lecon("SYE", 0, 0, 2, "G01", p1);
        Lecon sye2 = new Lecon("SYE", 3, 2, 2, "G01", p1);
        Lecon tic = new Lecon("TIC", 1, 9, 1, "F06", null);

        Etudiant e1 = new Etudiant("Lennon", "John", 1);
        Etudiant e2 = new Etudiant("Mc Cartney", "Paul", 2);
        Etudiant e3 = new Etudiant("Starr", "Ringo", 3);
        Etudiant e4 = new Etudiant("Harrison", "George", 4);
        Etudiant e5 = new Etudiant("Waters", "Roger", 5);
        Etudiant e6 = new Etudiant("Gilmour", "David", 6);

        Groupe g1 = new Groupe(1, "IL", 6, new Etudiant[]{e1, e2, e3, e4});
        Groupe g2 = new Groupe(1, "SI", 6, new Etudiant[]{e5, e6});

        g1.definirLecons(new Lecon[]{poo1, poo2, poo3, sye1, sye2, tic});
        g2.definirLecons(new Lecon[]{poo1, poo2, poo3});

        // Affichage des personnes
        Personne[] personnes = {p1, p2, e1, e2, e3, e4, e5, e6};
        System.out.println("-- Membres de l'ecole\n");
        for (var p : personnes)
            System.out.println(p);

        // Affichage des informations du groupe IL6-1
        System.out.printf("%n-- Informations du groupe %s (%s etudiants)%n", g1.nom(), g1.nombreEtudiants());
        System.out.println(g1.horaire());

        // Affichage de l'horaire du professeur PDO
        System.out.printf("%n-- Horaire du professeur %s%n", p2);
        System.out.println(p2.horaire());
    }
}
