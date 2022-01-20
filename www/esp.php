<?php
session_start ();
$titre='ANADOCTEUR | ESPACE PERSONNEL ';
require_once "include/util.inc.php"; 
if (isset($_SESSION['email']) && isset($_SESSION['pwd'])) {
    include_once 'include/headerConnecter.inc.php';
}else {
    header ('location: index.php');
}
$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
or die('Connexion impossible : ' . pg_last_error());

?>

<section>
    <h1>
    <?php 
	/*
    echo "votre email : ".$_SESSION['email']." ||  mot de passe: ".$_SESSION['pwd']." || id:".$_SESSION['id_patient'];
    */
	?>
    </h1>
    <h1>Bienvenue sur votre espace personnel <?php echo $_SESSION['prenom']." ".$_SESSION['nom']; ?>!</h1>
    <h2>HISTORIQUE DES CONSULTATIONS</h2>
   
        <?php 
        
        //select("SELECT id_personnel,id_exam,date FROM consultation WHERE id_patient=3");




		 // Exécution de la requête SQL
		 $query="SELECT nom_personnel,nom_examen,date,prix FROM consultation INNER JOIN exam ON consultation.id_exam = exam.id_exam INNER JOIN personnel ON consultation.id_personnel = personnel.id_personnel WHERE id_patient=" .$_SESSION['id_patient'];
		 $result = pg_query($dbconn,$query) or die('Échec de la requête : ' . pg_last_error());
 
 
		$consultation=0;
		 
		 // Affichage des résultats en HTML
			echo "<table>\n";	
			echo "<th>nom du medecin</th>\n";
			echo "<th>examens effectué(s)</th>\n";
			echo "<th>date</th>\n";
			echo "<th>honoraires</th>\n";
			while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
				echo "\t<tr>\n";
				foreach ($line as $col_value) {
					echo "\t\t<td>$col_value</td>\n";
				}
				echo "\t</tr>\n";
				$consultation+=1;
			}
			echo "</table>\n";
		 
		 if ($consultation==0) {
			echo "<p  style='color:#FF0000';>Vous n'avez pas eu de consultation chez Anadocteur à ce jour.</p>";
		 }
 
		 // Libère le résultat
		 pg_free_result($result);
         
        ?>
    <h2>HISTORIQUE DES COMPTE_RENDUS</h2>
    <?php
    $query2="SELECT * FROM compte_rendu where id_patient =" .$_SESSION['id_patient'];
		 $result1 = pg_query($dbconn,$query2) or die('Échec de la requête : ' . pg_last_error());
 
		 $compte_rendu=0;
 
		 // Affichage des résultats en HTML
		 while ($line = pg_fetch_array($result1, null, PGSQL_ASSOC)) {
			$file_name = $line['file_name'];
			$mime_type = $line['mime_type'];
			$file_data= pg_unescape_bytea($line["file_data"]);
			$file = fopen($file_name, "w") or die("Unable to open file!");
			fwrite($file, $file_data);
			echo "<p><a href='$file_name' download>télécharger votre compte rendu ici </a></p>\n";
			$compte_rendu+=1;
		}
		 if ($compte_rendu==0) {
			echo "<p  style='color:#FF0000';>Vous n'avez pas de compte-rendu de consultation pour le moment.</p>";
		 }
 
		 // Libère le résultat
		 pg_free_result($result1);
    ?>
    <!-- <p>SELECT * FROM compte_rendu where id_patient =</p>id_patient = $_SESSION['id_patient'] -->
    <h2>MODIFIER LE MOT DE PASSE</h2>
	
	<form method="post">
		<div class="form-group">
			<label for="apwd">Ancien mot de passe:   </label>
			<input type="password" class="form-control" id="apwd" placeholder="Entrer votre ancien mot de passe" name="apwd">
		</div>
		<div class="form-group">
			<label for="npwd">Nouveau mot de passe:</label>
			<input type="password" class="form-control" id="npwf" placeholder="Entrer votre nouveau mot de passe" name="npwd">
		</div>
		<input type="submit" name="submit" class="btn btn-primary" value="modifier">
	</form>

	<?php
	if (isset($_POST['apwd']) && isset($_POST['npwd'])) {
		$apwd = $_POST['apwd'];
		$npwd =$_POST['npwd'];
		if (password_verify($apwd ,$_SESSION['pwd'])) {
			$passw= password_hash($npwd,PASSWORD_DEFAULT); 
			$query="UPDATE patient SET mot_de_passe = '".$passw."' WHERE id_patient = '".$_SESSION['id_patient']."'";
			$result = pg_query($dbconn,$query) or die('Échec de la requête : ' . pg_last_error());
			$_SESSION['pwd']=$passw;
			pg_free_result($result);
			echo "<p style='color:#FF0000';> Le mot de passe a été modifié avec succès.</p>";
			
		}else {
			echo "<p style='color:#FF0000';> L'ancien mot de passe saisi est incorrect. Veuillez recommencer. </p>";
		}
	}
	?>
    <h2>SUPPRIMER MON COMPTE</h2>
    <a href="delete.php"> Supprimer mon compte</a>
</section>

<?php

include_once 'include/footer.inc.php';
?>