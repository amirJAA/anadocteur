<?php
declare(strict_types=1);
 echo "<!DOCTYPE html>\n";
 echo "<html lang=\"fr\">\n";
 echo "<head>\n";
 echo "    <link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />\n";
 echo "    <meta charset=\"utf-8\" />\n";
 echo "    <meta name=\"language\" content=\"FR\" />\n";
 echo "    <meta name=\"author\" content=\"JAAFAR Amir, BOURAS Nadia, GHEZIL Achref\" />\n";
 echo "    <meta name=\"keywords\" content=\"ANADOCTEUR, CABINET, MEDECIN, CONSULTATION, SANTE, MALADE\" />\n";
 echo "    <meta name=\"date\" content=\"20/10/2021\" />\n";
 echo "    <title>$titre</title>\n";
 echo "<style>\n
 table, th, td {\n
   border: 1px solid black;\n
 }\n
 table {\n
   width: 100%;\n
 }\n
 </style>\n";
 echo "</head>\n";
 echo "<body style=\"font-family: 'Times New Roman', Times, serif\">\n";
 echo "\t<section id=\"start\">\n";
 echo "\t\t<img src=\"doc/logo_anadocteur.png\" alt=\"logo_anadocteur\" width=\"250\"/>\n";
 echo "\t</section>\n"; 
 echo "\t<header>";
echo'   
        <nav class="menu" >
            <ul>
               <li><a href="index.php">ANADOCTEUR</a></li>
               <li><a href="esp.php">ESPACE PERSONNEL</a></li>
               <li><a href="rdv.php">PRENDRE RDV</a></li>
               <li><a href="logout.php">SE DECONNECTER</a></li>
               <li><a href="info.php">A PROPOS DE NOUS</a></li>
            </ul>
        </nav>
    </header>';

 ?>