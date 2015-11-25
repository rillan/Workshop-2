package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.hibernate.Session;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Boat;
import model.Member;
import view.Main;

public class BoatController implements Initializable  {
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
	private static ObservableList<String> items =FXCollections.observableArrayList (
		    "Kayak/canoe", "Other", "Motorsailer", "Sailboat");
	@FXML
	public
	ListView<String> list = new ListView<String>();
	
	//	Method for updating a members boat
	@FXML
	private void updateBoat(ActionEvent event)throws IOException{
		
		if(boatID.getText().matches(numbers) && updateBoatLength.getText().matches(numbers) && updateBoatType.getText().matches(boatString)   ){
			try{
			String theBoatsID = boatID.getText();
			Long id = Long.parseLong(theBoatsID);
			System.out.println("ths is id"+ id);
			boat = (Boat) DB.boats().findById(id);

			String boatsNewLength = updateBoatLength.getText();
			int convertedLength = Integer.parseInt(boatsNewLength);
			String newType = updateBoatType.getText();
			String updateMemberID = updateMemberBoat.getText();
			Long memberId = Long.parseLong(updateMemberID);
			member = (Member) DB.members().findById(memberId);

			if(member.listofBoats.contains(boat)){
				member.listofBoats.remove(boat);
				member.numberOfBoats --;
				
			}
			DB.boats().delete(boat);
			boat = new Boat(newType,convertedLength,member.id);
			member.registerBoat(boat);
			DB.boats().save(boat);




		}
		
		catch(NumberFormatException | NullPointerException e){
			System.out.println("The information provided is incorrect. Please try again");
		}
		}
		else{
			System.out.println("The information provided is incorrect. Please try again");
		}
	}
	@Override
	  public void initialize(URL url, ResourceBundle rb) {
		 list.setItems(items);
	  }
	//	Method for adding a boat

	@FXML
	private void registerBoat(ActionEvent event)throws IOException{
		if(newBoat.getText().matches(numbers) && memberIDBoat.getText().matches(numbers)){
		try{
		String memberID = memberIDBoat.getText();
		Long id = Long.parseLong(memberID);
		member = (Member) DB.members().findById(id);
		
		String boatsLength = (newBoat.getText());
		
		String modifyBoatType = boatsLength.replaceAll("[^a-zA-Z0-9]", "");

		String[]boat = modifyBoatType.split("(?<=\\D)(?=\\d)");
		String boatname = list.getSelectionModel().getSelectedItem();
		
		int length = Integer.parseInt(boat[0]);
		
		

			Boat boatToAdd = new Boat(boatname,length,member.id);
			member.registerBoat(boatToAdd);
			DB.boats().save(boatToAdd);
		}
		
		
	catch (IllegalArgumentException | NullPointerException e){
			System.out.println("The information provided is incorrect. Please try again");
	}
	
		}
		else{
			System.out.println("The information provided is incorrect. Please try again");
		}
	}

	// Method to return to the main stage
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


	// Method to delete a boat
	@FXML
	private void deleteBoat(ActionEvent event) throws IOException{
		
		if(deleteTextField.getText().matches(numbers) && deleteMember.getText().matches(numbers) ){
			try{
			String theBoatToDelete = deleteTextField.getText();
			String memberID = deleteMember.getText();
			Long memberId = Long.parseLong(memberID);
			member = (Member) DB.members().findById(memberId);
			System.out.println("Removes a boat " + member.name);
			Long id = Long.parseLong(theBoatToDelete);

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
		
		catch (IllegalArgumentException | NullPointerException e){
			System.out.println("The information provided is incorrect. Please try again");
	}
		}
		
		else{
			System.out.println("The information provided is incorrect. Please try again");
		}
		
	}
}	
		
		
	  
	

