<?php
$host = "postgresql-anadocteur.alwaysdata.net";
$port = "5432";
$dbname = "anadocteur_db";
$user = "anadocteur";
$password = "projetL3"; 
$connection_string = "host={$host} port={$port} dbname={$dbname} user={$user} password={$password} ";
$dbconn = pg_connect($connection_string);
$res=0;

if (isset($_POST['email']) && isset($_POST['mot_de_passe'])) {
  $email = $_POST['email'];
  $pwd =$_POST['mot_de_passe'];
  
  //$passw= password_hash($pwd,PASSWORD_DEFAULT);  

  $result = pg_query($dbconn, "SELECT email, mot_de_passe,id_patient,prenom,nom,date_naissance,n_tel FROM patient WHERE email='".$email."'");
  if (!$result) {
    $res = 1;
    exit;
  }
  $row = pg_fetch_row($result);
  echo $row;
  if ($row[0]==$email && password_verify($pwd ,$row[1])) {
    session_start ();
		// on enregistre les paramètres de notre visiteur comme variables de session ($email et $pwd) (notez bien que l'on utilise pas le $ pour enregistrer ces variables)
		$_SESSION['email'] = $email;
		$_SESSION['pwd'] = $row[1];
    $_SESSION['id_patient'] =$row[2];
    $_SESSION['prenom'] =$row[3]; 
    $_SESSION['nom'] =$row[4]; 
    $_SESSION['date_naissance'] =$row[5]; 
    $_SESSION['n_tel'] =$row[6]; 
    header ('location: esp.php');
  }else {
    $res=2;
  }
  
}

$titre='ANADOCTEUR | SE CONNECTER ';
$titre2=' A PROPOS DE NOUS '; 
if (isset($_SESSION['email']) && isset($_SESSION['pwd'])) {
  include_once 'include/headerConnecter.inc.php';
}else {
  include_once 'include/header.inc.php';
}
?>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="container">
  <h2>CONNEXION A VOTRE ESPACE PERSONNEL </h2>
  <form method="post">
  
     
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Entrer votre email" name="email">
    </div>
    
     
    <div class="form-group">
      <label for="mot_de_passe">Mot de passe :</label>
      <input type="password" class="form-control" id="mot_de_passe" placeholder="Entrer votre mot de passe" name="mot_de_passe">
    </div>
     
    <input type="submit" name="submit" class="btn btn-primary" value="Connexion">
  </form>

</div>


<?php
if ($res==1) {
  echo "<h2>Une erreur s'est produite.</h2>\n";
}elseif($res==2){
  echo "<h2>Mot de passe ou email incorrect</h2>";
}
include_once 'include/footer.inc.php';
?>