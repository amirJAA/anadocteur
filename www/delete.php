<?php
session_start ();

$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
		or die('Connexion impossible : ' . pg_last_error());

$query = "DELETE FROM patient WHERE id_patient =".$_SESSION['id_patient'];
$res = pg_query($dbconn,$query);


session_unset ();

// On détruit notre session
session_destroy ();

// On redirige le visiteur vers la page d'accueil
header ('location: index.html');

?>