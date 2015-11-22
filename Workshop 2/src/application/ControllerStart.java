package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Database.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Member;

public class ControllerStart implements Initializable {
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void showMemberinfo(){
	List<Member> allEvents = new ArrayList<>();
    allEvents.addAll(DB.members().findAll());
    System.out.println(allEvents.toString());
	}
}
