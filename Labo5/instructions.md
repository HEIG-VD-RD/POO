[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/gKwdxnEd)
# Matrix reloaded

_Durée du laboratoire: 4 périodes. A rendre le vendredi 03 novembre 2023, au début de la séance de laboratoire (10h15)._

_A rendre sur github, branche main._

Bonnes pratiques: https://heigvd-poo.github.io/Intro/
Intro à Maven: https://heigvd-poo.github.io/Intro/MAVEN_INTRO

#### Objectifs pédagogiques

* Concevoir des classes avec le principe d'encapsulation pour assurer l'intégrité des données et minimiser les dépendances
* Savoir lancer des exceptions en cas de paramètres non valides
* Modéliser des opérations arithmétiques avec une conception orientée-objet pour factoriser du code commun et pouvoir étendre les opérations facilement

## Description

Définir une classe permettant de représenter des matrices de taille
quelconque (_N_ × _M_) contenant des éléments entre 0 et _n_-1 pour un
entier _n_ (les entiers modulo _n_).

Il faut:

* être capable de créer une matrice soit en générant son contenu
  aléatoirement (une fois sa taille et son modulo connus), soit en
  passant ses valeurs en paramètre.

* être capable d'afficher le contenu de la matrice.

* être capable d'effectuer les opérations suivantes entre deux
  matrices: l'addition, la soustraction et le produit composante par
  composante. Les opérations doivent être effectuées modulo _n_.

* Le résultat _C_ d'une multiplication composante par composante entre
  une matrice _A_ et une matrice _B_ est défini par 
  _C<sub>i,j</sub>_ = _A<sub>i,j</sub>_ ⋅ _B<sub>i,j</sub>_ mod _n_.

* Dans le cas où l'on effectue une opération entre une matrice _A_ de
  taille _M<sub>1</sub>_ × _N<sub>1</sub>_ et une matrice _B_ de
  taille _M<sub>2</sub>_ × _N<sub>2</sub>_ et que les tailles ne
  correspondent pas, le résultat est une matrice de taille
  max(_M<sub>1</sub>_, _M<sub>2</sub>_) × max(_N<sub>1</sub>_, _N<sub>2</sub>_)
  où les _A<sub>i,j</sub>_ et _B<sub>i,j</sub>_ manquants ont été
  remplacés par des 0.

* Si les modules _n_ des deux matrices ne correspondent pas, lancez
  une `RuntimeException`.

* En cas de toute autre erreur, lancez une `RuntimeException`.

* Les méthodes `Math.floorMod()`, `Math.max()` et `Math.random()` vous
  seront sûrement utiles.

1. Implémenter cette classe.

2. Ecrire un programme de test prenant en argument les tailles
   _N<sub>1</sub>_, _M<sub>1</sub>_, _N<sub>2</sub>_, _M<sub>2</sub>_
   de deux matrices ainsi qu'un module _n_ et effectuant les
   opérations sur une matrice _N<sub>1</sub>_ × _M<sub>1</sub>_ et
   _N<sub>2</sub>_ × _M<sub>2</sub>_ de manière à produire un résultat
   semblable à

        The modulus is 5
        one:
        1 3 1 1 
        3 2 4 2 
        1 0 1 0 

        two:
        1 4 2 3 2 
        0 1 0 4 2 
        0 0 2 0 2 

        one + two:
        2 2 3 4 2 
        3 3 4 1 2 
        1 0 3 0 2 

        one - two:
        0 4 4 3 3 
        3 1 4 3 3 
        1 0 4 0 3 

        one x two:
        1 2 2 3 0 
        0 2 0 3 0 
        0 0 2 0 0 

3. Factoriser le code commun aux différentes opérations (addition, soustraction et multiplication) de manière à ce qu'il soit possible d'en ajouter de nouvelles ultérieurement. Définir des objets représentant l'opération à effectuer sur les __éléments__ des matrices opérandes et ceci sans utiliser de structures de contrôle (if, switch...).

4. N'hésitez pas à tester aussi les cas limites.
