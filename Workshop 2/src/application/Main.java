package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import Database.DB;
import Database.MemberDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import workshop.Member;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	@FXML
	private AnchorPane pane;
	@FXML
	Parent root;
	private static SessionFactory factory; 
	@FXML
	    private Button compact;
	 @FXML
	    private Button updateButton;
	    @FXML
	    private Button compose;
	    @FXML
	    private Button deleteBoat;
	    @FXML
	    private GridPane grid;

	    @FXML
	    private Button create;

	    @FXML
	    private Button delete;
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
	public void showMemberinfo(){
		
        List<Member> list = new ArrayList<>();
        list.addAll(DB.members().findAll());
        for (Member event : list) { //for better performance
			
        System.out.println(event.getInfo());
        }
		  }
	
	@FXML
	public void showCompose(){
		
        List<Member> list = new ArrayList<>();
        list.addAll(DB.members().findAll());
        for (Member event : list) { //for better performance
			
        System.out.println(event.getVerbose(event));
        }
		  }
		  
		 
	   
	
	public static void main(String[] args) {
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		
		launch(args);
	}
	public long getId() { return id; }
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
