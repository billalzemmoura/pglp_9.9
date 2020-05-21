# pglp_9.9
<h1>Dessin</h1>
<h2>pour créer un dessin :</h1>
    leNomDuDessin=dessin()
<h2> pour ajouter une formes ou dessin dans un dessin </h2>
     LeNomDessin.add(leNomDeFormeOuDessin)
<h1>Cercle</h1>
<h2>  pour créer un cercle :</h2>
    <p>un cercle prend en parametre un centre(x,y ==> des entier) et un rayon (r==>entier)</p>
    leNomDuCercle=cercle((4,5),45)


<h1>Triangle</h1>
<h2>  pour créer un triangle :</h2>
  <p> un triangle prend en parametre trois point (x,y==>des entier) </p>
  leNomDuTriangle=triangle((47,59),(42,53),(49,54))


<h1>Carre</h1>
<h2>  pour créer un carre :</h2>
<p>il prend en parametre un point (x,y==>des entier)  ce point est celui du bas a gauche d'un carréet une valleur de côtés ses deux dernier vont permmetre de calculer les coordonées des autres point :</p>
  leNomDucarre=carre((42,55),12)
<h1>Rectangle</h1>
<h2>  pour créer un rectangle :</h2>
<p>un rectangle prend en parametre 1 point (x,y==>des entier) ce point est celui du bas a gauche  d'un rectangle les autres seront calculer a base des coordonés de ce dernier et la largeur(entier 3eme parametre) et la longeur(entier 2eme parametre) inserer :</p>
    leNomDuRectangle=rectangle((42,55),88,66)

<h1>move</h1>
<p>  pour bouger une formes ou un dessin :(x,y)=(entier,entier) vecteur de déplacement<p>
move(nomFormesOuDessin,(x,y))

<h1>affichage</h1>
<p>  pour afficher une forme ou un dessin :</p>
afficher(nomFormesOuDessin)
<h1>supression</h1>
<p>  pour supprimer une forme ou un dessin :</p>
delete(nomFormesOuDessin)

<p> pour les commandes vous avez le choix soit vous  saisisez toutes les lettres en majuscules ou bien  toutes en  miniscule</p>
<p>"veuillez éviter de arréter le programme par force car cela peut détruire la base de données</p> 
<p>si  cela se produit il faut suprimer le dossier DATABASEDAOO pour que le programme  refonctionne </p>
