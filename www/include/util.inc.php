<?php
//------------------------------------------------------------------------------------------------
    
function compteur(){
    $file = fopen('include/hit_counter.txt', 'r');
    //ouverture du fichier en lecture
    $count = fread($file, filesize('include/hit_counter.txt'));
    //lecture du fichier
    $file = fopen('include/hit_counter.txt', 'w');
    //ouverture du fichier en ecriture
    $count+=1;
    //ajout d'une visite
    fwrite($file, $count);
    fclose($file);
    return $count;
}

 //------------------------------------------------------------------------------------------------

 define("language", 'french');
	function datetoday($lang="fr") {
		
		$Jours=array(1=>'Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi','Dimanche');
		$Mois=array(1=>'Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre');
		$Days=array(1=>'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday');
		$Mounths=array(1=>'January','February','March','April','May','June','July','August','September','October','November','December');
		
		$date = "\t\t<p class='positionAuteurDate'>";	
		
			if($lang=="fr") {	
				$date .= $Jours[date("N")]." ".date("d")." ".$Mois[date("n")]." ".date("Y");
			}
			else {
				$date .= $Days[date("N")].",".date("d").",".$Mounths[date("n")].",".date("Y");
			}
		$date .= "</p>\n";
		
		return $date;		
	}
 //------------------------------------------------------------------------------------------------

 