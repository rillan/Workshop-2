package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Session;

import Database.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Boat;
import model.Member;

public class BoatController {
	public Boat boat ;
	 public Member member;
	 
	@FXML
	Button deleteOk;
	@FXML
	private TextField deleteTextField;
	 @FXML
	    private TextField deleteMember;
	 @FXML
	    private TextField memberIDBoat;
	 @FXML
	    private TextField newBoat;
	 @FXML
	    private TextField updateMemberBoat;

	    @FXML
	    private Button returnBoat;
	Pattern patternA = Pattern.compile("[a-zA-Z]+");
	
	Pattern patternD = Pattern.compile("[0-9]+");
	Pattern boatType = Pattern.compile("Kayak|Canoe|Motorboat|Other");
	@FXML
	private String alphabet = patternA.toString() ;
	@FXML
	private String numbers = patternD.toString();
	
	@FXML
	private String boatString = boatType.toString();
	
	  @FXML
	    private TextField updateBoatLength;

	    @FXML
	    private TextField updateBoatType;

	    @FXML
	    private TextField error;

	    @FXML
	    private Button UpdateBoat;

	    @FXML
	    private TextField boatID;
	   
	
	
	@FXML
    private void updateBoat(ActionEvent event)throws IOException{
		if(boatID.getText().matches(numbers) && updateBoatLength.getText().matches(numbers) && updateBoatType.getText().matches(boatString)   ){
	    	String test = boatID.getText();
	    	Long id = Long.parseLong(test);
	    	System.out.println("ths is id"+ id);
	    	boat = (Boat) DB.boats().findById(id);
//	    	System.out.println(boat.type);
	    	String newLength = updateBoatLength.getText();
	    	int convertedLength = Integer.parseInt(newLength);
	    	String newType = updateBoatType.getText();
	    	String updateMemberID = updateMemberBoat.getText();
	    	Long memberId = Long.parseLong(updateMemberID);
	    	member = (Member) DB.members().findById(memberId);
	    	ArrayList<Boat> list = new ArrayList<Boat>();
	    	if(member.listofBoats.contains(boat)){
	    		member.listofBoats.remove(boat);
	    		member.numberOfBoats --;
	    		System.out.println(member.listofBoats.size());
	    	}
	    	DB.boats().delete(boat);
	    	boat = new Boat(newType,convertedLength,member.id);
	    	member.registerBoat(boat);
	    	DB.boats().save(boat);
	    	
	    	
	    	
	    	
	}
		else
    		error.setVisible(true);
	}
	@FXML
	private void registerBoat(ActionEvent event)throws IOException{
   		String memberID = memberIDBoat.getText();
    	Long id = Long.parseLong(memberID);
    	member = (Member) DB.members().findById(id);
    	
   		String test1 = (newBoat.getText());
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
    	}
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
	 private void deleteBoat(ActionEvent event) throws IOException{
		
		 	 if(deleteTextField.getText().matches(numbers) && deleteMember.getText().matches(numbers) ){
			    	String test = deleteTextField.getText();
			    	String memberID = deleteMember.getText();
			    	Long memberId = Long.parseLong(memberID);
			    	member = (Member) DB.members().findById(memberId);
			    	System.out.println("Removes a boat " + member.name);
			    	Long id = Long.parseLong(test);
			    	System.out.println("ths is id "+ id);
			    	boat = (Boat) DB.boats().findById(id);	    	
			    	if(boat.memberID == member.id){
			     		member.numberOfBoats--;
			    		
			   	}	
			        DB.boats().delete(boat);
			        member.listofBoats.clear();
			       
			     List<Boat> boatsToAdd = DB.boats().findAll();
			     for(Boat boats : boatsToAdd ){
			    	 if(boats.memberID == member.id){
			    		 member.listofBoats.add(boats);
			    	 }
			     }

        }
		 else{
			 System.out.println("Enter a number");
	 }
     }
}
