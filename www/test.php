<?php
// Connexion, sélection de la base de données
$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
    or die('Connexion impossible : ' . pg_last_error());


// Exécution de la requête SQL
$query = "SELECT id_patient FROM patient WHERE email = 'najat.jaafar@outlook.fr'";
$result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());



// Affichage des résultats en HTML
?>
<table>
<?php
while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
    echo "\t<tr>\n";
    foreach ($line as $col_value) {
        echo "\t\t<td>$col_value</td>\n";
    }
    echo "\t</tr>\n";
}
echo "</table>\n";

// Libère le résultat
pg_free_result($result);

// Ferme la connexion
pg_close($dbconn);
?>