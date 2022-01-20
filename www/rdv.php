<?php
session_start ();
$titre='ANADOCTEUR | ESPACE PERSONNEL ';
if (isset($_SESSION['email']) && isset($_SESSION['pwd'])) {
    include_once 'include/headerConnecter.inc.php';
}else {
    include_once 'include/header.inc.php';
}
$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
or die('Connexion impossible : ' . pg_last_error());

if (isset($_GET['rdv'])) {
    $query="UPDATE rdv SET id_patient = ".$_SESSION['id_patient']." , libre=false WHERE id_rdv= ".$_GET['rdv']." and libre = true";
    

    if (!isset($_SESSION['rdv'])) {
        $res =  $result=pg_query($dbconn,$query);
        $_SESSION['rdv']=true;
        if ($res) {
            echo "<h2>Votre rendez-vous a été enregistré avec succès.</h2> \n<h3>Merci </h3> ";
        }else {
            echo "<h2>une erreur c'est glissée</h2> \n<h3>réessayez ulterieurement </h3> ";
        }

    } else {
        echo "<h2>Une erreur est surevenue dans la prise de rendez-vous. </h2> \n<h3>réessayez ulterieurement.</h3> ";
    }
}

 // Exécution de la requête SQL
 $query="SELECT * FROM rdv where libre=true;";
 $result = pg_query($dbconn,$query) or die('Échec de la requête : ' . pg_last_error());
 ?>

<img src="doc/rdv.png" alt="image_rdv">

    <form action="rdv.php">
        <label for="rdv">selectionnez un rendez-vous :</label>
        <select id="rdv" name="rdv">
            <?php
            while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
                var_dump($line);
                echo "<option value='".$line["id_rdv"]."'>Le: ".$line["date"]." à: ".$line["heure"]."</option> \n";
            }
            ?>
        </select>
        <input type="submit">
    </form>

    <p>Si vous rencontrez un problème lors de la prise de rendez-vous veuillez nous contactez par email à l'adresse : anadocteur@outlook.com </p>





<?php
pg_free_result($result);
include_once 'include/footer.inc.php';
?>
