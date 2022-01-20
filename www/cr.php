<?php
$titre='ANADOCTEUR | CREATION DE COMPTE ';
$titre2=' A PROPOS DE NOUS '; 
include_once 'include/header.inc.php';
?>

<?php
// Connexion, sélection de la base de données
$dbconn = pg_connect("host=postgresql-anadocteur.alwaysdata.net dbname=anadocteur_db user=anadocteur password=projetL3")
    or die('Connexion impossible : ' . pg_last_error());



if(isset($_POST['submit'])&&!empty($_POST['submit'])){
  if (!empty($_POST['nom'])&&!empty($_POST['prenom'])&&!empty($_POST['email'])&&!empty($_POST['mot_de_passe'])&&!empty($_POST['date_naissance'])&&!empty($_POST['n_tel'])) {
  // Exécution de la requête SQL
    $passw= password_hash($_POST['mot_de_passe'],PASSWORD_DEFAULT);
    $result = pg_insert($dbconn, 'patient', array('nom' =>$_POST['nom'],'prenom'=>$_POST['prenom'],'email'=>$_POST['email'],'mot_de_passe'=>$passw,'date_naissance'=>$_POST['date_naissance'],'n_tel'=>$_POST['n_tel']));
    if($result){
      header ('location: crx.html');
    }else{

      echo "<p>Une ou plusieurs erreur(s) sont présente(s) dans le formulaire. </p>";
    }
  }else {
    echo "<p style='color:#FF0000';>Veuillez remplir tous les champs du formulaire</p>";
  }

}
?>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="container">
  <h2>CRÉATION DE VOTRE ESPACE PERSONNEL :</h2>
  <form method="post">
  
    <div class="form-group">
      <label for="nom">Nom :</label>
      <input type="text" class="form-control" id="nom" placeholder="Entrer votre nom" name="nom" requuired>
    </div>

    <div class="form-group">
      <label for="Prenom">Prénom :</label>
      <input type="text" class="form-control" id="prenom" placeholder="Entrer votre Prenom" name="prenom" requuired>
    </div>
    
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Entrer votre email" name="email">
    </div>
    
    <div class="form-group">
      <label for="n_tel">Numéro de téléphone :</label>
      <input type="number" class="form-control" maxlength="10" id="n_tel" placeholder="Entrer votre Numéro de téléphone" name="n_tel">
    </div>
    
    <div class="form-group">
      <label for="date_naissance">Date de naissance :</label>
      <input type="date" class="form-control"  id="date_naissance" placeholder="Entrer votre Numéro de téléphone" name="date_naissance">
    </div>
    
    <div class="form-group">
      <label for="mot_de_passe">Mot de passe :</label>
      <input type="password" class="form-control" id="mot_de_passe" placeholder="Entrer votre mot de passe" name="mot_de_passe">
    </div>
     
    <input type="submit" name="submit" class="btn btn-primary" value="Inscription">
  </form>
</div>

<?php
include_once 'include/footer.inc.php';
?>
