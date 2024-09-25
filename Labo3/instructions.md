[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/k_pAQCTO)
# Laboratoire 3: UML 

Durée du laboratoire: 4 périodes. A rendre le vendredi 13 octobre 2023 à 10h15.

# Fédération Internationale de Gymnastique

Définir le diagramme de classes UML permettant de représenter le fonctionnement des événements sportifs gérés par la Fédération Internationale de Gymnastique.

- Une fédération nationale est caractérisée par son nom, ses gymnastes et ses juges.
- Un gymnaste est caractérisé par son nom, son prénom, sa date de naissance, son genre, son numéro de téléphone, son poids et sa taille.
- Un juge est caractérisé par son nom, son prénom, sa date de naissance et son numéro de téléphone.
- Une discipline (p.ex. “Gymnastique artistique hommes”, “Trampoline femmes”...) est caractérisée par son nom, le genre des gymnastes qui peuvent y participer et ses catégories.
- Une catégorie (p.ex. “Poutre”, “Barres asymétriques”...) est caractérisée par son nom. Pour toutes les caté- gories un nombre maximum de gymnastes pouvant être inscrits est défini (16).
- Un événement sportif est caractérisé par son nom, sa date et les disciplines qui y sont présentées. Si une discipline est présente à un événement, toutes ses catégories le seront également.
- Une fédération nationale peut s’inscrire à un événement. Elle doit alors indiquer quels juges elle envoie sur l’événement et inscrire ses gymnastes. La dernière date de modification de cette inscription est conservée.
- Un gymnaste peut concourir dans différentes catégories. Il ne participe pas forcément à toutes les catégo- ries d’une discipline donnée.
- Pour chaque catégorie, la note (un réel) de chacun des gymnastes ayant participé est conservée.
- Le système doit permettre d’effectuer les opérations suivantes (parmi d’autres):
  - Afficher la liste des événements où a participé une fédération donnée.
  - Le podium (les 3 meilleurs gymnastes) d’une catégorie pour un événement donné.
  - Le nombre de victoires d’un gymnaste dans une catégorie donnée.

**Remarques**
- Documenter vos choix et hypothèses de travail.
- Attention à bien factoriser les informations redondantes.
- Indiquer le type des attributs, le type de retour des méthodes ainsi que les types de leurs paramètres.
- Ne pas indiquer les constructeurs ni les accesseurs triviaux.
- Ici, seules les méthodes directement invoquables, répondant aux opérations demandées doivent être représentées. Il serait par exemple inutile de représenter un accesseur `double getScore()` utilisé pour la détermination d’un podium
- Indiquer toutes les cardinalités des associations ainsi que leur nom (ou leurs rôles).
- Indiquer les signatures des méthodes nécessaires pour répondre aux traitements de la donnée.
- Spécifier en français les contraintes d’intégrité qui ne peuvent pas être exprimées sur le diagramme.

**Rendu**
Le diagramme de classes ainsi que les contraintes d’intégrité éventuelles sont à rendre sous format PDF sur Github (branch main), bien présentés. N'oubliez pas de faire un `git push` avant la date de rendu.
