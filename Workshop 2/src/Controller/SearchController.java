package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Search;
import view.Main;

public class SearchController implements Initializable{

	@FXML
    private TextField searchForYear;

    @FXML
    private TextField searchField;
    @FXML
    ObservableList<String> filteredData = FXCollections.observableArrayList();
    @FXML
    private Button younger;

    @FXML
    private Button bornThisYear;

    @FXML
    private Button queryDB;

    @FXML
    private Button older;
	@FXML
	private Main main;
	@FXML
	public Search search;
	@FXML
	private ListView<String> listWithFilteredData;
	 @FXML
	String input;
	 @FXML
		String loggedIn ="true";
	// The pattern as follows: 1: Clear the list of all members then repopulate it.
		//                         2: Get the value of the searchfield
		//						   3: Depending on the condition of the search perform a certain search. Generic search is a regular search, certain sign 
		//								looks for certain signs and the year search is either older, younger or born this year.
		//						   4: Search the variables and see if any of them matches the specified searchvalue. If it's a match add it to the
		//								observablelist

		
		
			
		// The generic search that matches certain characters.
		
		@FXML
		public void searchingDB(ActionEvent event)throws IOException, ParseException{
			input = searchField.getText().toString();
			Search test = new Search();
			listWithFilteredData.setItems(test.searchingDB(input));
			listWithFilteredData.setVisible(true);
			
		}
		
	



		// Method for searching for year specific conditions: older than, younger than or born a certain year.
		@FXML
		public void searchforYears(ActionEvent event) throws IOException, ParseException{
		input = searchForYear.getText().toString();
		if (event.getSource()== older) {
			Search searchOlder = new Search();
		listWithFilteredData.setItems(searchOlder.searchOlder(input));
		
}
		else if (event.getSource()== younger){
			Search searchYounger = new Search();
			listWithFilteredData.setItems( searchYounger.searchYounger(input));
			
		}
		else if (event.getSource()== bornThisYear){
			Search searchSameYear = new Search();
			listWithFilteredData.setItems( searchSameYear.searchSameYear(input));
			
		}
		
		listWithFilteredData.setVisible(true);
		
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			main.getLoggedIn();
			
		}
			

}
