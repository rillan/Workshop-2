package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Database.DB;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import workshop.Boat;
import workshop.Member;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	@FXML
	private AnchorPane pane;
	@FXML
	Parent root;
	@FXML
	private static Session session;
	@FXML
	private static SessionFactory factory; 
	@FXML
	    private Button verbose;
	 @FXML
	    private Button updateButton;
	 @FXML
	    private Button registerBoat;
	    @FXML
	    private Button compose;
	    @FXML
	    private ListView composeList;
	    @FXML
	    private ListView verboseList;
	    @FXML
	    private Button deleteBoat;
	    @FXML
	    private GridPane grid;
	    @FXML
	    private Button singleMemberButton;
	    @FXML
	    private Button create;
	    @FXML
	     MemberController memberControll = new MemberController();
	    
	    
	    @FXML
	    private Button delete;
	    public static void main(String[] args) {
			 try{
				 List<Member> allMembers = new ArrayList();
				 allMembers.addAll(DB.members().findAll());
				 List<Boat> allBoats = new ArrayList();
				 allBoats.addAll(DB.boats().findAll());
				
		         factory = new Configuration().configure().buildSessionFactory();
		      }catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
			
			
			launch(args);
		}
	    @FXML
	     private void createMember(ActionEvent event) throws IOException{
	    	memberControll.showCompose();
	    	memberControll.showVerbose();
	    	 Parent root = null;
	    	 if(event.getSource()== create){
	    	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddMember.fxml"));
	    	 
	    	  root = (Parent) loader.load();
	     }
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setTitle("Add a member");
	    	 stage.setScene(scene);
	    	 stage.show();
	     }
	    
	    
	    @FXML
	     private void deleteMember(ActionEvent event) throws IOException{
	    	
	    	 Parent root = null;
	    	 if(event.getSource()== delete){
	    	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("Delete.fxml"));
	    	 
	    	  root = (Parent) loader.load();
	     }
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setScene(scene);
	    	 stage.setTitle("Delete a member");
	    	 stage.show();
	     }
	    @FXML
	    private void cancel(ActionEvent event)throws IOException{
	    	Parent root = null;
	    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("Start.fxml"));
	    	root = (Parent) loader.load();
	     
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setTitle("Workshop 2");
	    	 stage.setScene(scene);
	    	 stage.show();
	    	 
	    }
	    
	    
	    @FXML
	     private void updateMember(ActionEvent event) throws IOException{
	    	
	    	 Parent root = null;
	    	 if(event.getSource()== updateButton){
	    	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("UpdateMember.fxml"));
	    	 
	    	  root = (Parent) loader.load();
	     }
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setScene(scene);
	    	 stage.setTitle("Update a member");
	    	 stage.show();
	     }  
	    
	    // Boat methods
	    
	    @FXML
	     private void registerBoat(ActionEvent event) throws IOException{
	    	
	    	 Parent root = null;
	    	 if(event.getSource()== registerBoat){
	    	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegisterBoat.fxml"));
	    	 
	    	  root = (Parent) loader.load();
	     }
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setScene(scene);
	    	 stage.setTitle("Register Boat");
	    	 stage.show();
	     }  
	       @FXML
	     private void deleteBoatMethod(ActionEvent event) throws IOException{
	    	
	    	 Parent root = null;
	    	 if(event.getSource()== deleteBoat){
	    	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("DeleteBoat.fxml"));
	    	 
	    	  root = (Parent) loader.load();
	     }
	    	 Scene scene = new Scene(root);
	    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	 stage.setScene(scene);
	    	 stage.setTitle("Delete a boat");
	    	 stage.show();
	     }  
	    
	
	@Override
	public void start(Stage primaryStage)throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Start.fxml"));
		
		root = (Parent) loader.load();
		 Scene scene = new Scene(root);
    		primaryStage.setTitle("Workshop 2");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}

	@FXML
	public void showCompose(ActionEvent event) throws IOException{
		
		Parent root = null;
   	 if(event.getSource()== compose){
   	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("composeView.fxml"));
   	 
   	  root = (Parent) loader.load();
    }
   	 Scene scene = new Scene(root);
   	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   	 stage.setScene(scene);
   	 stage.setTitle("Compose view");
   	 stage.show();
        
        }
		  
		  
		 
	 @FXML
		public void showVerbose(ActionEvent event) throws IOException{
			
		 Parent root = null;
	   	 if(event.getSource()== verbose){
	   	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("verboseView.fxml"));
	   	 
	   	  root = (Parent) loader.load();
	    }
	   	 Scene scene = new Scene(root);
	   	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	 stage.setScene(scene);
	   	 stage.setTitle("Verbose view");
	   	 stage.show();
	        
	        }
			  
	 @FXML
		public void showOneMember(ActionEvent event) throws IOException{
			
		 Parent root = null;
	   	 if(event.getSource()== singleMemberButton){
	   	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("singleMemberView.fxml"));
	   	 
	   	  root = (Parent) loader.load();
	    }
	   	 Scene scene = new Scene(root);
	   	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	 stage.setScene(scene);
	   	 stage.setTitle("Single member view");
	   	 stage.show();
	        
	        }
	
	
	public long getId() { return id; }
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
