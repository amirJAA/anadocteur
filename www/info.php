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

<h2 style="text-align:center">L'équipe de réalisation</h2>
<div class="row">
    <div class="column">
        <div class="card">
        <div class="container">
            <h2>JAAFAR Amir</h2>
            <p>Étudiant en L3 Informatique au département des sciences et techniques de la faculté de Cergy-Pontoise.</p>
            <p>jaafar.amir@outlook.fr</p>
        
        </div>
        </div>
    </div>

    <div class="column">
        <div class="card">
        <div class="container">
            <h2>GHEZIL Achref</h2>
            <p>Étudiant en L3 Informatique au département des sciences et techniques de la faculté de Cergy-Pontoise.</p>
            <p>achref.ghezil@yahoo.com</p>
            
        </div>
        </div>
    </div>

    <div class="column">
        <div class="card">
        <div class="container">
            <h2>BOURAS Nadia</h2>
            <p>Étudiante en L3 Informatique au département des sciences et techniques de la faculté de Cergy-Pontoise.</p>
            <p>bourasnadia@outlook.com</p>
            
        </div>
        </div>
    </div>
</div>
<div class="about-section">
    <p>Nous avons créé ce site dans le cadre du projet de Réseaux & DataBase au département des sciences et techniques de la faculté de Cergy-Pontoise.</p>
</div>
</section>


<?php
include_once 'include/footer.inc.php';
?>