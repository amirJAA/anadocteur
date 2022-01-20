<?php
session_start ();
$titre='ANADOCTEUR | ACCUEIL ';
$titre2=' A PROPOS DE NOUS '; 
if (isset($_SESSION['email']) && isset($_SESSION['pwd'])) {
    include_once 'include/headerConnecter.inc.php';
}else {
    include_once 'include/header.inc.php';
}

?>
<section>
    <h1>Bienvenue sur notre plateforme ANADOCTEUR.</h1>
    <p>Prenez rendez-vous auprès d'un de nos praticiens 7/7j, 24/24h.</p>
    <img src="doc/fond.jpg" alt="image d'accueil">
</section>

<?php
include_once 'include/footer.inc.php';
?>