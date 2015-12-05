package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import Database.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
@Entity
@Table(name = "Search")
public class Search {
	@FXML
    private TextField searchForYear;

    @FXML
    private TextField searchField;

    @FXML
    private Button younger;
    @FXML
    ObservableList<String> filteredData = FXCollections.observableArrayList();
    @FXML
    private Button bornThisYear;

    @FXML
    private Button queryDB;

    @FXML
    private Button older;

    @FXML
    private ListView<String> listWithFilteredData;
    @FXML
	List<Member> allMembersData = new ArrayList <Member>();
    
	@FXML
	String input;
	
// The pattern as follows: 1: Clear the list of all members then repopulate it.
	//                         2: Get the value of the searchfield
	//						   3: Depending on the condition of the search perform a certain search. Generic search is a regular search, certain sign 
	//								looks for certain signs and the year search is either older, younger or born this year.
	//						   4: Search the variables and see if any of them matches the specified searchvalue. If it's a match add it to the
	//								observablelist

	
	
		
	
	public Search(){
		this.listWithFilteredData = listWithFilteredData;
		this.allMembersData = allMembersData;
		this.filteredData = filteredData;
	}
	// The generic search that matches certain characters.
	@FXML
	public ObservableList<String> searchingDB(String test)throws ParseException{
		allMembersData.clear();
		filteredData.clear();
		
		allMembersData.addAll(DB.members().findAll());

		
		String filter = test.toLowerCase().trim(); 
		if(filter.length()!=0)
		for(Member mSearchText : allMembersData){
			String name = mSearchText.name.toLowerCase().trim();
			String personNumber = mSearchText.personNumber;
			String monthsName = mSearchText.month.toLowerCase().trim();
			String year = personNumber.substring(0,3);
			String  month = personNumber.substring(4,6);
			String  day = personNumber.substring(7,9);
			String boatInfo ="";
			for(Boat bMSearchText:mSearchText.listofBoats ){

				boatInfo = bMSearchText.type.toLowerCase().trim() + bMSearchText.length;
				
			}
			if(name.contains(filter) || monthsName.contains(filter)||  boatInfo.trim().contains(filter) || day.contains(filter)||month.contains(filter) || year.contains(filter)){
				filteredData.add(mSearchText.getVerbose(mSearchText));

				

			}
			

		}
		return filteredData;
	}
	
	// Methods for searching for year specific conditions: older than, younger than or born a certain year.
	@FXML
	public ObservableList<String>  searchOlder(String input) throws IOException, ParseException{
		filteredData.clear();

		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		List <Member> allMembersAge =DB.members().findAll();
		String inputYear = input.toLowerCase().trim();
		
		Date inputedValue = year.parse(inputYear);
		
			
			for(Member mSearchYear:allMembersAge){
				String olderYear = mSearchYear.personNumber.substring(0,4);
				System.out.println(olderYear);
				Date olderDate = year.parse(olderYear);
				if(olderDate.compareTo(inputedValue)<0){
					filteredData.add( mSearchYear.getVerbose( mSearchYear));
				
				}
			
		
			}
		
		return filteredData;
			}
		
		

	@FXML
	public ObservableList<String>  searchYounger(String input) throws IOException, ParseException{
		filteredData.clear();

		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		List <Member> allMembersAge =DB.members().findAll();
		String inputYear = input.toLowerCase().trim();
		
		Date inputedValue = year.parse(inputYear);
		
			for(Member m:allMembersAge){
				String youngerYear = m.personNumber.substring(0,4);
				Date youngerDate = year.parse(youngerYear);
				if(youngerDate.compareTo(inputedValue)>0){
					filteredData.add(m.getVerbose(m));
					
				}
				
			}
		
			
		
			
		
		
	
		
		
		return filteredData;
	}
	@FXML
	public ObservableList<String>  searchSameYear(String input) throws IOException, ParseException{
		filteredData.clear();

		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		List <Member> allMembersAge =DB.members().findAll();
		String inputYear = input.toLowerCase().trim();
		
		Date inputedValue = year.parse(inputYear);
		
			for(Member m:allMembersAge){
				String sameYear = m.personNumber.substring(0,4);
				Date sameDate = year.parse(sameYear );
				if(sameDate.compareTo(inputedValue)==0){
					filteredData.add(m.getVerbose(m));
					
				}
			}
		
			
		
		return filteredData;

}
}
