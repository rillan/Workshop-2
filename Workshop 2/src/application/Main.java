package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.aquafx_project.AquaFx;

import Database.BoatDAO;
import Database.DB;
import Database.MemberDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Boat;
import model.Member;
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
	MemberDAO membersData;
	@FXML
	BoatDAO boatData;
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
	    private Button changeBoat;
	    @FXML
	     MemberController memberControll = new MemberController();
	    
	    
	    @FXML
	    private Button delete;
	    public static void main(String[] args) {
			 
			
			
			launch(args);
		}
	    @FXML
	     private void createMember(ActionEvent event) throws IOException{
	    	
	    	
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
		 List<Member> list = new ArrayList<>();
	        list.addAll(DB.members().findAll());
	        System.out.println(list.size());
	        AquaFx.style();
	        
		for (Member event: list){
			System.out.println("members id is" +event.id);
			
	        System.out.println(list.size());
		}
		List<Boat> listBoat = new ArrayList<>();
		listBoat.addAll(DB.boats().findAll());
		for (Boat boat: listBoat){
			System.out.println("Boats id is" +boat.id);
		}
		root = loader.load();
		 Scene scene = new Scene(root);
    		primaryStage.setTitle("Workshop 2");
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}

	@FXML
	public void updateBoat(ActionEvent event) throws IOException{
		
		Parent root = null;
   	 if(event.getSource()== changeBoat){
   	 FXMLLoader loader = new FXMLLoader(Main.class.getResource("UpdateBoat.fxml"));
   	 
   	  root = (Parent) loader.load();
    }
   	 Scene scene = new Scene(root);
   	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   	 stage.setScene(scene);
   	 stage.setTitle("Update a Boat");
   	 stage.show();
        
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
	 public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List members = session.createQuery("FROM Member").list(); 
	        
	         
	         tx.commit();}
	      catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	 @Override
	    public void stop() {
	        DB.closeSessionFactory();
	    }
	public long getId() { return id; }
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
