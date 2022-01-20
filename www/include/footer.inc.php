<?php
  echo "\t\t\t\n\n\n\n<footer>\n";
  include_once "include/util.inc.php"; 
?>
			<p> Auteurs : JAAFAR Amir, BOURAS Nadia, GHEZIL Achref</p>
			<p> Nombre de visites : <?php echo compteur() ?></p>
      <?php echo datetoday() ?>
      <p>Contact : anadocteur@outlook.com</p>
      <?php
          $url = "http://www.geoplugin.net/xml.gp?ip=".$_SERVER['REMOTE_ADDR'];
          $xml = simplexml_load_file($url);

          //api_key=nx7nsNiogbwYkn6dfAQZC5SnhrlFwb&

          echo "\t\t".'<p>'.$xml->geoplugin_city.', '.$xml->geoplugin_regionName.', '.$xml->geoplugin_region.', '.$xml->geoplugin_countryName.'</p>'."\n";
          echo "\t</footer>\n";
      ?>
  </body>
</html>