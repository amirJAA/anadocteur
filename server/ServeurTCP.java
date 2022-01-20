package serverTCP;

import java.net.BindException;
import java.net.MalformedURLException;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;



import java.beans.Statement;
import java.io.* ;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

class Multi extends Thread{
	/*
	 * CLasse pour le multi theaing pour pouvoir ouvrir plusieur client en meme temp
	 */
	private Socket clientSocket=null;
	PrintWriter flux_sortie;
	BufferedReader flux_entree;
	String query="";
	String result="";
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy") ;
	String now= dateFormat.format(new Date());
	Multi() throws IOException{
	
	}
	
	Multi(Socket clientSocket) throws IOException{
	    this.clientSocket=clientSocket;
        flux_sortie = new PrintWriter (clientSocket.getOutputStream (), true) ;
        flux_entree = new BufferedReader (
                                new InputStreamReader (
                                clientSocket.getInputStream ())) ;
	}

	
	
public void run() {
        
		String jdbcURL ="jdbc:postgresql://postgresql-anadocteur.alwaysdata.net:5432/anadocteur_db";
		String username ="anadocteur";
		String password ="projetL3";
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		try {
			connection = DriverManager.getConnection(jdbcURL,username,password);
			System.out.println("Connection avec la base de donne Postgresql reussi");

		} catch (SQLException e) {
			System.err.println("Error in connecting to PSQL server");
			e.printStackTrace();
		}
		
		//"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
		//"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
		//"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
		//"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
		//"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
		
        String chaine_entree="", chaine_sortie ="";
        
        try {
        	while (chaine_entree!=null){
        		try {
        			chaine_entree = flux_entree.readLine();
        		}catch(Exception e) {
        			System.err.println ("Une déconnexion inattendu du client est survenue") ;
				     break;
        		}
				result="";
			     System.out.println ("J'ai recu " + chaine_entree) ;
			     if (chaine_entree.equals ("Au revoir !")) {
			    	flux_sortie.println("a plus") ;
			        break ;
			     }
			     else {
			    	 //le client renvoie une chaine de char qu'on découpe suivant un separateur pour traiter la demande 
			    	 String request[] = new String[10];
			    	 request = chaine_entree.split(";;"); // separateur ;;
			    	 int nb = request.length;
			    	 int i=0;
			    	 //
			    	 switch (request[0]) {
			    	 	// SELECT FROM ALLLL ################################################################################################
			    	 	case "select"://cas ou le client renvoi une requete select
			    	 		if(nb>=3) {
				    	 		switch(request[2]) {
				    	 			// SELECT FROM PATIENTS ################################################################################################
				    	 			case "patient":
				                	 	if(nb>=4) {
				                	 		query="select "+request[1]+" from "+request[2]+" WHERE "+request[3]+"='"+request[4]+"'";
				               	 		}else {
				               	 			 query="select "+request[1]+" from "+request[2];
				               	 		}
				            	 		try (java.sql.Statement stmt = connection.createStatement()) {
					           		        ResultSet rs = stmt.executeQuery(query);
				
					           		        int j=0;
				        	 				if(request[1].equals("*")) {
				    	           		        while (rs.next()) {
				    	             		      int id_patient = rs.getInt("id_patient");
				    	           		          String prenom = rs.getString("prenom");
				    	           		          String nom = rs.getString("nom");
				    	           		          String date_naissance = rs.getString("date_naissance");
				    	           		          String n_tel = rs.getString("n_tel");
				    	           		          String email= rs.getString("email");
				    	           		          String mot_de_passe = rs.getString("mot_de_passe");
				    	           		          result+=id_patient + "#" + prenom + "#" + nom +
				    	   		                             "#" + date_naissance + "#" + n_tel+"#"+email + "#" + mot_de_passe +";;";
				    	           		          j++;
				    	           		        }
				        	 				}else {
				    	           		        while (rs.next()) {
				    	             		      String s = rs.getString(request[1]);
				    	           		          result+=s+";;";
				    	           		        }
				        	 				}
				        	 				
						           		    } catch (SQLException e) {
						           		    	e.printStackTrace();
					  	           		    }
				            	 			break;
				    	 				
				    	 				
				    	 				
				    	 				
				    	 				
				    	 			//SELECT FROM COMPTE RENDU###################################################################
				    	 			case "compte_rendu":
				    	 				
				    	 				try (java.sql.Statement stmt = connection.createStatement()) {
	
					           		        if(request[1].equals("*")) {
				        	 					query="select * from compte_rendu where id_patient='"+request[4]+"'";
				    	           		        ResultSet rs = stmt.executeQuery(query);
					    	           		     while (rs.next()) {
					    	           		          String mime_type = rs.getString("mime_type");
					    	           		          String file_name = rs.getString("file_name");
					    	           		          
					    	           		          int id_cr =rs.getInt("id_cr");
					    	           		          
					    	           		          byte[] filebytes = rs.getBytes("file_data");
					    	           		          Path path = Paths.get(request[5]+file_name);
					    	           		          Files.write(path, filebytes);
					    	           		          //System.out.println(Arrays.toString(filebytes));
					    	           		          result ="compte rendu enregistré dans vos Documents";
					    	           		     }
					           		        }else {
				        	 					query="select id_patient from compte_rendu";
				    	           		        ResultSet rs = stmt.executeQuery(query);
					    	           		     while (rs.next()) {		    	           		          
					    	           		          int id_patient =rs.getInt("id_patient");
				        	           		          result+=id_patient+";;";
					    	           		     }
					           		        }
					           		    } catch (SQLException e) {
					           		    	e.printStackTrace();
				  	           		    }
				    	 				break;
				    	 				
				    	 				
				    	 			case "rdv":
				    	 				if(request[1].equals("*")) {

				        	 				query="SELECT * FROM rdv WHERE libre=true AND date>='"+now+"' ORDER BY date";
				        	 				try (java.sql.Statement stmt = connection.createStatement()) {
					           		        ResultSet rs = stmt.executeQuery(query);
				    	           		     while (rs.next()) {
				    	           		          String date= rs.getString("date");
				    	           		          String heure= rs.getString("heure");
				    	           		          result+=date+"#"+heure+";;";
				    	           		     }
				    	           		     if(result.isEmpty()) {
				    	           		    	 result="aucun rdv disponible";
				    	           		     }else {
				    	           		    	 System.out.println(result);
				    	           		     }
				
						           		    } catch (SQLException e) {
						           		    	e.printStackTrace();
					  	           		    }
				    	 				}else if(request[1].equals("occuper")) {
				        	 				query=" SELECT prenom,nom,date,heure FROM rdv JOIN patient ON rdv.id_patient=patient.id_patient WHERE libre=false AND date>='"+now+"' ORDER BY date;";
				        	 				try (java.sql.Statement stmt = connection.createStatement()) {
					           		        ResultSet rs = stmt.executeQuery(query);
				    	           		     while (rs.next()) {
				    	           		          String date= rs.getString("date");
				    	           		          String heure= rs.getString("heure");
				    	           		          String prenom= rs.getString("prenom");
				    	           		          String nom= rs.getString("nom");
				    	           		          result+=prenom+"#"+nom+"#"+date+"#"+heure+";;";
				    	           		     }
				    	           		     if(result.isEmpty()) {
				    	           		    	 result="aucun rdv pris";
				    	           		     }
						           		    } catch (SQLException e) {
						           		    	e.printStackTrace();
					  	           		    }
				    	 				}
				    	 				break;
				    	 				
				    	 				
				    	 				
				    	 				
				    	 			case "consultation":
				    	 				query="SELECT id_consultation,nom,prenom,nom_personnel,prenom_personnel,nom_examen,date FROM consultation JOIN patient ON consultation.id_patient=patient.id_patient JOIN personnel ON consultation.id_personnel=personnel.id_personnel JOIN exam ON consultation.id_exam=exam.id_exam;";
				    	 				try (java.sql.Statement stmt = connection.createStatement()) {
				           		        ResultSet rs = stmt.executeQuery(query);
					           		     while (rs.next()) {
					           		          String date= rs.getString("date");
					           		          String id_consultation= rs.getString("id_consultation");
					           		          String prenom= rs.getString("prenom");
					           		          String nom= rs.getString("nom");
					           		          String nom_personnel= rs.getString("nom_personnel");
					           		          String prenom_personnel= rs.getString("prenom_personnel");
					           		          String nom_examen= rs.getString("nom_examen");
					           		          result+=id_consultation+"#"+nom+"#"+prenom+"#"+nom_personnel+"#"+prenom_personnel+"#"+nom_examen+"#"+date+";;";
					           		     }
					           		    } catch (SQLException e) {
					           		    	e.printStackTrace();
				  	           		    }
						       		    break;
						       		  default :
						       			  result=request[2]+": n'est pas une table accessible depuis le client car elle n'est pas présente sur le diagramme d'échange";
						    	
				    	 		}
			    	 		}
			       		    	
			             // ALL ISERRTTTTSSS ################################################################################################
			    	 	case "insert":
			    	 		switch (request[1]) {
			    	 			// INSERT INTO PATIENTS##################################################
			    	 			case "patient":
			              			String sql = "INSERT INTO patient (prenom,nom,date_naissance,n_tel,email,mot_de_passe)" 
			            					+"VALUES (?,?,?::date,?,?,?)";
			        				preparedStatement =connection.prepareStatement(sql);
			                    	 for (String a: request) {
			                    		if(i>1) {
				                            System.out.println(i-1+a);
				                			try {
				        	        			preparedStatement.setString(i-1,a);
				        	        			
				                			}catch (Exception e) {
				        						e.printStackTrace();
				        					}
			                			}
			                			i++;
			                    	 }
			                    	 
			             			int rows;
			        				try {
			        					System.out.println(preparedStatement);
			        					rows = preparedStatement.executeUpdate();
			        	     			if(rows>0) {
			        	     				result="A new patient has been inserted.";
			        	     			}
			        				} catch (SQLException e) {
			        					// TODO Auto-generated catch block
			        					e.printStackTrace();
			        				}
			        				i=0;
			    	 				break;
			    	 			
			    	 				
			    	 				
			    	 				//ISERT INTO COMPTRE RENDUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
			    	 			
			    	 			case "compte_rendu":
			    					
			    					PreparedStatement ps = connection.prepareStatement("INSERT INTO compte_rendu (mime_type,file_name,file_data,id_patient)VALUES (?,?,?,?)");
			    					
			    					File file = new File(request[3]);
			    				    byte[] arr = new byte[(int)file.length()];
			    				    
			    				    URLConnection conn = null;
			    					try {
			    						conn = file.toURL().openConnection();
			    					} catch (MalformedURLException e2) {
			    						// TODO Auto-generated catch block
			    						e2.printStackTrace();
			    					} catch (IOException e2) {
			    						// TODO Auto-generated catch block
			    						e2.printStackTrace();
			    					}
			    				    String mimeType = conn.getContentType();
			    					FileInputStream fis = null;
			    					try {
			    						fis = new FileInputStream(file); 
			    						fis.read(arr);
			    						System.out.println(Arrays.toString(arr));
			    					} catch (IOException e1) {
			    						// TODO Auto-generated catch block
			    						e1.printStackTrace();
			    					}
			    					try {
			    						fis.close();
			    					} catch (IOException e1) {
			    						// TODO Auto-generated catch block
			    						e1.printStackTrace();
			    					}
			      				    
			    					ps.setString(1, mimeType);
			    					ps.setString(2, file.getName());
			    				    //InputStream targetStream = new ByteArrayInputStream(request[4].getBytes());
			    					ps.setBytes(3, arr);
			    					int id = Integer.parseInt(request[2]);
			    					ps.setInt(4, id);
			    				
			    					//ps.setBinaryStream(3, fis, (int)file.length());
			    					rows = ps.executeUpdate();
			    	     			if(rows>0) {
			    	     				result="un nouveau compte rendu a était ajouter";
			    	     			}else{
			    	     				result="l'insertient du compte rendu na pas était faite";
			    	     			}
			    					ps.close();
			    					//System.out.println(request[5]);
			    	 				break;
			    	 		}

							break;
					
				 		case "ajouter":
				 			int quantite = 0;
				 			int value=0;
				 			try {
				 				value = Integer.parseInt(request[1]);
				 			}catch (Exception e) {
								result="la valeur a jouter n'est pas une valeur numérique";
								//e.printStackTrace();
								break;
				 			}
			 				try (java.sql.Statement stmt = connection.createStatement()) {
			 					query="select quantite from stocks where nom='"+request[2]+"'";
			       		        ResultSet rs = stmt.executeQuery(query);
			           		     while (rs.next()) {
			           		          quantite = rs.getInt("quantite");
			           		     }
			 				}catch(Exception e) {
			 					e.printStackTrace();
			 					result="Le server n'arrive pas de recuperer les information pour modifier la table stocks";
			 					break;
			 				}
				 			
				 			
			    			String sql = "UPDATE stocks SET quantite="+(quantite+value)+" WHERE nom='"+request[2]+"';";
							preparedStatement =connection.prepareStatement(sql);
			     			int rows;
							try {
								rows = preparedStatement.executeUpdate();
				     			if(rows>0) {
				     				result="la quantité de "+request[2]+" a passer de "+quantite+" a "+(quantite+value) ;
				     			}else {
				     				result="erreur l'ajout n'a pas etait fait correctement : la produit "+request[2]+" n'est pas present dans la table stocks";
				     			}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 			break;
				 		case "recette":
				 			Date dt = new Date();
				    		flux_sortie.println("vous voulez la recette aujourd'hui ? repondez par oui ou par une DATE") ;
							String rep = flux_entree.readLine();
							if (rep.equals("oui")) {
				    			String recette = "select prix from consultation JOIN exam on consultation.id_exam=exam.id_exam WHERE date='"+now+"'";
				    			int prix = 0;
		            	 		try (java.sql.Statement stmt = connection.createStatement()) {
			           		        ResultSet rs = stmt.executeQuery(recette);
	    	           		        while (rs.next()) {
	    	             		      prix += rs.getInt("prix");
		        	 				}
	    	           		        if(prix == 0) {
	    	           		        	result="votre recette aujourd'hui le: "+now+" est de zéro euro ";
	    	           		        }else {
	    	           		        	result="votre recette aujourd'hui le: "+now+" est de: "+prix+"€";
	    	           		        }
	    	           		    } catch (SQLException e) {
			           		    	e.printStackTrace();
		  	           		    }
							}else {
								try {
								    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(rep);
					    			String recette = "select prix from consultation JOIN exam on consultation.id_exam=exam.id_exam WHERE date='"+rep+"'";
					    			int prix = 0;
			            	 		try (java.sql.Statement stmt = connection.createStatement()) {
				           		        ResultSet rs = stmt.executeQuery(recette);
		    	           		        while (rs.next()) {
		    	             		      prix += rs.getInt("prix");
			        	 				}
		    	           		        if(prix == 0) {
		    	           		        	result="votre recette du: "+rep+" est de zéro euro ";
		    	           		        }else {
		    	           		        	result="votre recette du: "+rep+" est de: "+prix+"€";
		    	           		        }
		    	           		    } catch (SQLException e) {
				           		    	e.printStackTrace();
			  	           		    }
								} catch (Exception e) {
									result="Réponse non valide :(";
								}

							}

				 			break;
				 			
				 		default:
				 			result="requête non reconnue par le diagramme d'échange";
			    	 }
			    	 
			    	 // envoie un message au client
			    	 if(result.isEmpty()) {
				    	 flux_sortie.println("requête non valide") ;
			    	 }else {
			    		 flux_sortie.println(result) ;
			    	 }
			     }
			}
			System.err.print("fermeture de la connection");
	        flux_sortie.close () ;
	        flux_entree.close () ;
	        clientSocket.close () ;
       
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }catch(Exception e) {
			 	System.err.println("Une déconnexion inattendu du client est survenue");
	        	//e.printStackTrace();
		}
        try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



public class ServeurTCP {
    public static void main (String argv []) throws IOException, SQLException ,InterruptedException{
        while(true){
	        ServerSocket serverSocket = null ;
	        try {
	        	if(argv.length>0) {
	        		try {
	        		int port = Integer.parseInt(argv[0]);
		        	serverSocket = new ServerSocket (port) ;
	        		}catch(NumberFormatException e) {
	        			System.err.println("Le numéro de port doit étre une valeur numerique");
	        			System.exit(1);
	        		}
	        	}else {
		        	serverSocket = new ServerSocket (5000) ;//5000 port par défaut
	        	}

	        }catch(BindException e) {
	        	System.err.println("BIND : le server tente d'ouvrir une socket sur un port déja utilisé");
	            System.exit (1) ;
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	            System.exit (1) ;
	        }
	        Socket clientSocket = null ;
	        try {
	            clientSocket = serverSocket.accept () ;
	            Multi t=new Multi(clientSocket);
	            t.start();

	            Thread.sleep(2000);
	            serverSocket.close();
	        } 
	        catch (IOException e) {
	            System.err.println ("Accept echoue.") ;
	            System.exit (1) ;
	        }
	        
        }    
   }    
}

