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
import workshop.Boat;
import workshop.Member;

public class BoatController {
	public Boat boat ;
	@FXML
	Button deleteOk;
	@FXML
	private TextField deleteTextField;
	Pattern patternA = Pattern.compile("[a-zA-Z]+");
	Pattern patternB = Pattern.compile("[A-Za-z0-9,]+");
	Pattern patternD = Pattern.compile("[0-9]+");
	@FXML
	private String alphabet = patternA.toString() ;
	@FXML
	private String numbers = patternD.toString();
	
	
	
	@FXML
    private void updateBoat(ActionEvent event)throws IOException{
		
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
    	boat = (Boat) DB.boats().findById(id);
        DB.boats().delete(boat);
        id =null;
        }
		 else{
			 System.out.println("Enter a number");
	 }
     }
}
