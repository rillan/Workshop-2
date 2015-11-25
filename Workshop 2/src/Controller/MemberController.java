package Controller;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import Database.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Boat;
import model.Member;
import view.Main;

public class MemberController implements Initializable {
	private static DB db;
	public Main main ;
	@FXML
    private TextField deleteTextField;
	 @FXML
	    private ListView composeList;
	 
	 @FXML
	    private ListView verboseList ;
	 
	 @FXML
	    private ListView oneMember ;
	 @FXML
	    private TextField memberID;
	 @FXML
	 ObservableList<String> singleInformation =FXCollections.observableArrayList()  ;
	 
	 @FXML
	 ObservableList<String> composeInformation =FXCollections.observableArrayList()  ;
	
	 @FXML
	 ObservableList<String> verboseInformation =FXCollections.observableArrayList()  ;
	 @FXML
	    private Button oneMemberButton;
	 
    @FXML
    private Button deleteCancel;

    @FXML
    private Button deleteOk;

	
	Pattern patternA = Pattern.compile("[a-zA-Z]+");
	Pattern patternB = Pattern.compile("[A-Za-z0-9,]+");
	Pattern patternD = Pattern.compile("[0-9]+");
	@FXML
	private String alphabet = patternA.toString() ;
	@FXML
	private String numbers = patternD.toString();
	
	@FXML
	private String boat = patternB.toString();
	
	
	   @FXML
	    private Button cancel;

	    @FXML
	    private TextField personnumber;

	    @FXML
	    private TextField name;

	    @FXML
	    private Button create;

	    @FXML
	    private AnchorPane pane;
	    
	    @FXML
	    private TextField errrortext;
	    
	    @FXML
	    private TextField updateName;

	    @FXML
	    private TextField ID;
	    
	    @FXML
	    private TextField boatInfo;
	    @FXML
	    private TextField personNumber;
	    @FXML
	    private Button addBoat;

	   
	   

	    @FXML
	    private Button UpdateMember;
	    
	    @FXML
	    private TextField error;
	    
	    public static MemberController memberSelectController;
	    
	    public Stage addEventStage;
	    
	    public Member member;
	    
	   
	    @FXML
	    public void addMember(ActionEvent event)throws IOException{
	    	
	    	if(name.getText().matches(alphabet)&& personnumber.getText().matches(numbers)){
	    		
	    		Member newMember = new Member(name.getText(),personnumber.getText());
	    		System.out.println(newMember.name + newMember.personNumber);
	    		DB.members().save(newMember);
	    		
	    		Parent root = null;
	    		
	        	FXMLLoader loader = new FXMLLoader(Main.class.getResource("Start.fxml"));
	        	root = (Parent) loader.load();
	       	 Scene scene = new Scene(root);
	        	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        	 stage.setScene(scene);
	        	 stage.show();
	        	
	    	    	}
	    	
	    	else{
	    		errrortext.setVisible(true);
	    	}
	    }	

	    
	    // Method for updating a member

    @FXML
    private void updateMember(ActionEvent event)throws IOException{
   	if(ID.getText().matches(numbers) && updateName.getText().matches(alphabet) && personNumber.getText().matches(numbers) && boatInfo.getText().matches(boat)   ){
    		
    	String memberID = ID.getText();
    	Long id = Long.parseLong(memberID);
    	member = (Member) DB.members().findById(id);
    	String name =updateName.getText();
    	String newPersonNumber = personNumber.getText();
    	String test1 = (boatInfo.getText());
    	String test2 = test1.replaceAll("[^a-zA-Z0-9]", "");
    	System.out.println(test2);
    	String[]boat = test2.split("(?<=\\D)(?=\\d)");
    	String boatname = boat[0];
    	
    	int length = Integer.parseInt(boat[1]);
    	String kayak = "Kayak";
    	String motorboat ="Motorboat";
    	String sailboat = "Sailboat";
    	String other = "Other";
    	if(boatname.matches(kayak)|| boatname.matches(motorboat) || boatname.matches(sailboat) || boatname.matches(other)  ){
    	
    	Boat boatToAdd = new Boat(boatname,length,member.id);
    	member.registerBoat(boatToAdd);
   	DB.boats().save(boatToAdd);
    	member.updateMember(name,newPersonNumber);
    	}
    	
 	
    	}
    	else
    		error.setVisible(true);
   		
    // Method for showing a single members information
    }
   @FXML
   public void showMember(ActionEvent event)throws IOException{
   	String theID = memberID.getText();
	Long id = Long.parseLong(theID);
	member = (Member) DB.members().findById(id);
   	singleInformation.add(member.getCompose(member));
   	oneMember.setItems(singleInformation);
   	oneMember.setVisible(true);
   }
   
   // Method to return to main stage
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
	    
	    // Method to show the compose list
	    
	    @FXML
		public void showCompose(){
			
	        List<Member> list = new ArrayList<>();
	        list.addAll(DB.members().findAll());
	        for (Member event : list) { //for better performance
				composeInformation.add(event.getCompose(event));
	        	composeList.setItems(composeInformation);
				composeList.setVisible(true);
	        }
	    }
	    
	    // Method to show the verboselist
	    
	    @FXML
		public void showVerbose(){
			
	        List<Member> list = new ArrayList<>();
	        list.addAll(DB.members().findAll());
	        for (Member event : list) { //for better performance
	        	verboseInformation.add(event.getVerbose());
	        	verboseList.setItems(verboseInformation);
	        	verboseList.setVisible(true);
	        	
	        }
			  }
		
	    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	// Method to delete a member
	 @FXML
     private void deleteMember(ActionEvent event) throws IOException{
		 if(deleteTextField.getText().matches(numbers)){
    	String test = deleteTextField.getText();
    	Long id = Long.parseLong(test);
    	member = (Member) DB.members().findById(id);
        DB.members().delete(member);
        id =null;
        }
		 else{
			 System.out.println("Enter a number");
	 }
     }
	
}


