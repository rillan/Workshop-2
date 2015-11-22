package application;

import java.io.IOException;
import java.util.regex.Pattern;

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
	    private TextField memberIDBoat;
	 @FXML
	    private TextField newBoat;

	    @FXML
	    private Button returnBoat;
	Pattern patternA = Pattern.compile("[a-zA-Z]+");
	
	Pattern patternD = Pattern.compile("[0-9]+");
	Pattern boatType = Pattern.compile("Kayak"+"Canoe"+"Motorboat"+"Other");
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
		if(boatID.getText().matches(numbers) && updateBoatLength.getText().matches(numbers) && updateBoatType.getText().matches(boatString) ){
	    	String test = boatID.getText();
	    	Long id = Long.parseLong(test);
	    	System.out.println("ths is id"+ id);
	    	String newLength = updateBoatLength.getText();
	    	int convertedLength = Integer.parseInt(newLength);
	    	String newType = updateBoatType.getText();
	    	boat = (Boat) DB.boats().findById(id);
	    	boat = new Boat(newType,convertedLength);
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
        	member.registerBoat(boatname, length);
        	Boat boatToAdd = new Boat(boatname,length);
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
		 if(deleteTextField.getText().matches(numbers)){
    	String test = deleteTextField.getText();
    	Long id = Long.parseLong(test);
    	System.out.println("ths is id"+ id);
    	boat = (Boat) DB.boats().findById(id);
    	
        DB.boats().delete(boat);
        id =null;
        }
		 else{
			 System.out.println("Enter a number");
	 }
     }
}
