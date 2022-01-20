<?php
include_once 'include/header.inc.php';
$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
or die('Connexion impossible : ' . pg_last_error());
		 // Exécution de la requête SQL
		 $query="SELECT COUNT(*) AS nbr_doublon, date, heure FROM rdv GROUP BY date, heure HAVING   COUNT(*) > 1";


         echo "<h2>La requête suivante est à utiliser pour trouver les rdv dupliquées.</h2>";
		 $result = pg_query($dbconn,$query) or die('Échec de la requête : ' . pg_last_error());
 
 
		$dupliquer=0;
		 
		 // Affichage des résultats en HTML
			echo "<table>\n";	
			echo "<th>nombre de doublon</th>\n";
			echo "<th>date</th>\n";
			echo "<th>heure</th>\n";
			while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
				echo "\t<tr>\n";
				foreach ($line as $col_value) {
					echo "\t\t<td>$col_value</td>\n";
				}
				echo "\t</tr>\n";
				$dupliquer
                +=1;
			}
			echo "</table>\n";
		 
		 if ($dupliquer==0) {
			echo "<p  style='color:#FF0000';>aucun rdv dupliquées :(</p>";
		 }
         echo "<p  style='color:#FF0000';>nombre de rendez-vous dupliquées: ".$dupliquer."</p>";
include_once 'include/footer.inc.php';
?>