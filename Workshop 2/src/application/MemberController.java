package application;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

import workshop.Member;

public class MemberController implements Initializable {
		
	public Main main ;
	@FXML
    private TextField deleteTextField;

    @FXML
    private Button deleteCancel;

    @FXML
    private Button deleteOk;

	
	
	
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
	    private TextField personNumber;

	    @FXML
	    private Button UpdateMember;
	    
	    
	    public static MemberController memberSelectController;
	    
	    public Stage addEventStage;
	    
	    public Member member;
	    
	    private long temporaryId;
	    @FXML
	    public void addMember(ActionEvent event)throws IOException{
	    	
	    	if(name.getText().equals("")|| personnumber.getText().equals("")){
	    		errrortext.setVisible(true);
	    	}
	    	else{
	    		
	    		
	    		  
		
		Member newMember = new Member(name.getText(),personnumber.getText());
		System.out.println(newMember.name + newMember.personNumber);
		System.out.println("hej");
		DB.members().save(newMember);
		  
		Parent root = null;
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("Start.fxml"));
    	root = (Parent) loader.load();
     
    	 Scene scene = new Scene(root);
    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	 stage.setScene(scene);
    	 stage.show();
    	
	    	}
	    }	


    @FXML
    private void updateMember(ActionEvent event)throws IOException{
    	String memberID = ID.getText();
    	Long id = Long.parseLong(memberID);
    	member = (Member) DB.members().findById(id);
    	String name =updateName.getText();
    	String newPersonNumber = personNumber.getText();
    	
    	member.updateMember(name,newPersonNumber);
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
    	String test = deleteTextField.getText();
    	Long id = Long.parseLong(test);
    	member = (Member) DB.members().findById(id);
        DB.members().delete(member);
        id =null;
        }
     }
	



