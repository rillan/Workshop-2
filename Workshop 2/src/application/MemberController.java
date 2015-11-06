package application;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import Database.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import workshop.Boat;
import workshop.Member;

public class MemberController implements Initializable {
		
	public Main main ;
	@FXML
    private TextField deleteTextField;

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


    @FXML
    private void updateMember(ActionEvent event)throws IOException{
    	if(ID.getText().matches(numbers) && updateName.getText().matches(alphabet) && personNumber.getText().matches(numbers) && boatInfo.getText().matches(boat)   ){
    		error.setVisible(false);
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
    	member.registerBoat(boatname, length);
    	Boat boatToAdd = new Boat(boatname,length);
    	DB.boats().save(boatToAdd);
    	member.updateMember(name,newPersonNumber);
    	}
    	
 	
    	}
    	else
    		error.setVisible(true);
    		
    	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
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


