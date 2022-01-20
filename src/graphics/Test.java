package graphics;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Connection;

import javax.swing.JFrame;
import java.awt.FileDialog;

public class Test {
    /***
     * classe qui a servi a remplire la table des rendez vous 
     */
	static String jdbcURL ="jdbc:postgresql://postgresql-anadocteur.alwaysdata.net:5432/anadocteur_db";
	static String username ="anadocteur";
	static String password ="projetL3";

	public static void main(String[] args) {
		try {
			Connection connection = (Connection) DriverManager.getConnection(jdbcURL,username,password);
			System.out.println("Connection reussi");
			String sql = "INSERT INTO rdv (date,heure)"
					+"VALUES (?::date,?)";
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			int rows;
			
			for(int y=2021;y<=2021;y++) {
				for(int m=12;m<=12;m++) {
					for (int d=1;d<=12;d++) {
						for(int h=8;h<=17;h++) {
							for(int min=0;min<60;min=min+15) {
								Time time = new Time(h,min,0);
								preparedStatement.setString(1, m+"/"+d+"/"+y);
								preparedStatement.setTime(2, time);
								rows = preparedStatement.executeUpdate();
								if(rows>0) {
									System.out.println("A new rdv has been inserted. : "+m+"/"+d+"/"+y+"--"+h+":"+min);
								}else {
									
								}
							}
						}
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Error in connecting to PSQL server");
			e.printStackTrace();
		}

	}
	

}
