package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.aquafx_project.AquaFx;

import Database.BoatDAO;
import Database.DB;
import Database.MemberDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Boat;
import model.Member;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	@FXML
	private AnchorPane pane;
	@FXML
	Parent root;
	@FXML
	MemberDAO membersData;
	@FXML
	BoatDAO boatData;
	@FXML
	private static Session session;
	@FXML
	private static SessionFactory factory; 
	@FXML
	private Button verbose;
	@FXML
	private Button updateButton;
	@FXML
	private Button registerBoat;
	@FXML
	private Button compose;
	@FXML
	private Button returnLogin;
	@FXML
	public boolean decideLoggedin = false;
	@FXML
	private ListView <Member> composeList;
	@FXML
	private ListView <Member> verboseList;
	@FXML
	private Button deleteBoat;
	@FXML
	private GridPane grid;
	@FXML
	private Button singleMemberButton;
	@FXML
	private Button create;
	@FXML
	private Button changeBoat;
	   @FXML
	    private Button queryDB;
	   @FXML
	   private Button returnFromSearch;
	@FXML
	private Button returnFirstPage;
	@FXML
	private TextField searchField;
	@FXML
	public static String verifyLogged ="false";
	  @FXML
	    public ComboBox searchAgeComboBox;

	@FXML
	private Button delete;
	@FXML
	List<Member> allMembersData = new ArrayList <Member>();
	@FXML
	ObservableList<String> filteredData = FXCollections.observableArrayList();;
	// Items for the login page
	@FXML
	private Button view;
	@FXML
	public ListView listWithFilteredData;
	@FXML
	private TextField password;

	@FXML
	private TextArea applicationInformation;
	@FXML
	
	private Button login;
	@FXML
	private TextField searchForYear;
	   @FXML
	    private Button younger;

	    @FXML
	    private Button bornThisYear;
	    @FXML
	    private Button older;
	@FXML
	private TextField username;
	public static void main(String[] args) {



		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Login.fxml"));
		AquaFx.style();
		root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setTitle("Workshop 2");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	@FXML
	private void createMember(ActionEvent event) throws IOException{


		Parent root = null;
		if(event.getSource()== create){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddMember.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Add a member");
		stage.setScene(scene);
		stage.show();
	}


	@FXML
	private void deleteMember(ActionEvent event) throws IOException{
		List<Member> allMembers = DB.members().findAll();

		for (Member m: allMembers){
			System.out.println("The members id: "+ m.id);
		}
		Parent root = null;
		if(event.getSource()== delete){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Delete.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Delete a member");
		stage.show();
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
	private void updateMember(ActionEvent event) throws IOException{
		List<Member> allMembers = DB.members().findAll();

		for (Member m: allMembers){
			System.out.println("The members id: "+ m.id);
		}
		Parent root = null;
		if(event.getSource()== updateButton){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("UpdateMember.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Update a member");
		stage.show();
	}  

	// Boat methods

	@FXML
	private void registerBoat(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== registerBoat){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegisterBoat.fxml"));

			root = (Parent) loader.load();
		}

		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Register Boat");
		stage.show();
	}  
	@FXML
	private void deleteBoatMethod(ActionEvent event) throws IOException{
		List<Boat> allBoats = DB.boats().findAll();

		for(Boat b: allBoats){
			System.out.println("The boats id: "+b.id +" and the member id is: "+ b.memberID);

		}
		Parent root = null;
		if(event.getSource()== deleteBoat){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("DeleteBoat.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Delete a boat");
		stage.show();
	}  
	@FXML
	public void login(ActionEvent event) throws IOException{
		Connection con = null;

		String dbPassword = "user";
		String dbUsername = "user";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost/new_schema", dbUsername, dbPassword);
			if (username.getText().matches(dbUsername)&& password.getText().matches(dbPassword)){
				if(event.getSource()== login){
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("Application.fxml"));
					root = (Parent) loader.load();

					Main.setMyVariable();
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.setTitle("Member Registry Editing Mode");
					stage.show();
				} 
			}

			else{
				System.out.println("Incorrect login information");
			}
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace(System.err);
		}

		catch(SQLException sql)
		{
			sql.printStackTrace(System.err);
		}

		finally{
			try{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace(System.err);
			}
		}
	}
	@FXML 
	public void returnToLogin (ActionEvent event) throws IOException{
		if(event.getSource()== returnLogin){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Login.fxml"));

			root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Workshop 2");
			stage.show();
		}
	}
	@FXML
	public void viewMode (ActionEvent event) throws IOException{
		if(event.getSource()== view){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Application.fxml"));

			root = loader.load();

			((GridPane) root).getChildren().remove(6);
			((GridPane) root).getChildren().remove(2);
			((GridPane) root).getChildren().remove(5);
			((GridPane) root).getChildren().remove(0);
			((GridPane) root).getChildren().remove(2);
			((GridPane) root).getChildren().remove(2);
			Scene scene = new Scene(root);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Member Registry View Mode");

			stage.show();
		}
	} 



	@FXML
	public void updateBoat(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== changeBoat){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("UpdateBoat.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Update a Boat");
		stage.show();

	}
	@FXML
	public void showCompose(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== compose){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("compactView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Compose view");
		stage.show();

	}

	@FXML
	public static String setMyVariable() {
		verifyLogged ="true";
		return verifyLogged;
	}
	@FXML
	public static String getMyVariable() {

		return verifyLogged;
	}



	@FXML
	public void showVerbose(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== verbose){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("verboseView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Verbose view");
		stage.show();

	}

	@FXML
	public void showOneMember(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== singleMemberButton){
			List<Member> list = DB.members().findAll();

			for(Member m: list){
				System.out.println("The members id: "+m.id);
			}
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("singleMemberView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Single member view");
		stage.show();

	}
	
	

	// The pattern as follows: 1: Clear the list of all members then repopulate it.
	//                         2: Get the value of the searchfield
	//						   3: For every member in the data base add the value of the members name, personnumber, month and 
	//							  in the members arraylist of boats to strings with a suitable name e.g String name = the members name.
	//						   4: Search the variables and see if any of them matches the specified searchvalue. If it's a match add it to the
	//								observablelist

	@FXML
	public void searchingDB(){
		allMembersData.clear();
		filteredData.clear();
		listWithFilteredData.setItems(filteredData);
		allMembersData.addAll(DB.members().findAll());

		String boatInfo = "";

		String filter = searchField.getText().toLowerCase().trim(); 
		System.out.println(filter);
		for(Member member : allMembersData){
			String name = member.name.toLowerCase().trim();
			String personNumber = member.personNumber;
			String monthsName = member.month.toLowerCase().trim();
			String year = personNumber.substring(0,3);
			String  month = personNumber.substring(4,6);
			String  day = personNumber.substring(7,9);
			for(Boat boat:member.listofBoats ){

				boatInfo = boat.type.toLowerCase().trim() + boat.length;

			}
			if(name.contains(filter) || monthsName.contains(filter)||  boatInfo.contains(filter) || day.contains(filter)||month.contains(filter) || year.contains(filter)){
				filteredData.add(member.getVerbose(member));
				listWithFilteredData.setItems(filteredData);


			}
			

		}
	}
	@FXML
	public void searchforYears(ActionEvent event) throws IOException, ParseException{
		filteredData.clear();
		listWithFilteredData.setItems(filteredData);
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		List <Member> allMembersAge =DB.members().findAll();
		String inputYear = searchForYear.getText();
	
		Date inputedValue = year.parse(inputYear);
		if(event.getSource()== older){
			
			for(Member m:allMembersAge){
				String olderYear = m.personNumber.substring(0,4);
				System.out.println(olderYear);
				Date olderDate = year.parse(olderYear);
				if(olderDate.compareTo(inputedValue)<0){
					filteredData.add(m.getVerbose(m));
					listWithFilteredData.setItems(filteredData);
				}
			}
		}
		else if(event.getSource()== younger){
			for(Member m:allMembersAge){
				String youngerYear = m.personNumber.substring(0,4);
				Date youngerDate = year.parse(youngerYear);
				if(youngerDate.compareTo(inputedValue)>0){
					filteredData.add(m.getVerbose(m));
					listWithFilteredData.setItems(filteredData);
				}
			}
		}
		else if (event.getSource()== bornThisYear){
			for(Member m:allMembersAge){
				String sameYear = m.personNumber.substring(0,4);
				Date sameDate = year.parse(sameYear );
				if(sameDate.compareTo(inputedValue)==0){
					filteredData.add(m.getVerbose(m));
					listWithFilteredData.setItems(filteredData);
		}
			
		}
		}
			}
		
		
	

//	 Sends you to the search scene
	@FXML
	public void search(){
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Search.fxml"));
		
		try {
			

		
			       
			root = (Parent) loader.load();
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Single member view");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}





	@Override
	public void stop() {
		DB.closeSessionFactory();
	}
	public long getId() { return id; }
	public void initialize(URL arg0, ResourceBundle arg1) {
		searchAgeComboBox.getItems().addAll(
	            "jacob.smith@example.com",
	            "isabella.johnson@example.com",
	            "ethan.williams@example.com",
	            "emma.jones@example.com",
	            "michael.brown@example.com");

	}

}
