package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aquafx_project.AquaFx;

import Database.BoatDAO;
import Database.DB;
import Database.MemberDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Boat;
import model.Member;
import view.Main;

public class ApplicationViewController implements Initializable {
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
	private Button updateMemberButton;
	@FXML
	private Button registerBoat;
	@FXML
	private Button compact;
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
	String loggedIn ="true";
	@FXML
	private Main main;
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
			System.out.println("The members name " + m.name+ " and id: "+ m.id );
		}
		Parent root = null;
		if(event.getSource()== delete){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("DeleteMember.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Delete a member");
		stage.show();
	}
	


	@FXML
	private void updateMember(ActionEvent event) throws IOException{
		List<Member> allMembers = DB.members().findAll();

		for (Member m: allMembers){
			System.out.println("The members name " + m.name+" and id: " +m.id);
		}
		Parent root = null;
		if(event.getSource()== updateMemberButton){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("UpdateMember.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Update a member");
		stage.show();
	}  

	// Methods to add, delete and change a boat

	@FXML
	private void registerBoat(ActionEvent event) throws IOException{
		allMembersData = DB.members().findAll();
		for(Member mBRegister: allMembersData ){
			
			System.out.println("The members name " + mBRegister.name+" and id: " +mBRegister.id);

		}
		Parent root = null;
		if(event.getSource()== registerBoat){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddBoat.fxml"));

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
		List<Boat> allBoatsDelete = DB.boats().findAll();

		for(Boat bDelete: allBoatsDelete){
			Member mBDelete = DB.members().findById(bDelete.memberID);
			System.out.println("The boats id: "+bDelete.id +" The members name " + mBDelete.name+" and id: " +mBDelete.id);

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
	public void updateBoat(ActionEvent event) throws IOException{
		List<Boat> allBoatUpdate = DB.boats().findAll();
		for(Boat bUpdate:allBoatUpdate ){
			Member mBUpdate = DB.members().findById(bUpdate.memberID);
			System.out.println("The boats id "+ bUpdate.id+" and the members name "+ mBUpdate .name+" and id " +bUpdate.memberID);
		}
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
	
	// Different methods for log in to the application and returning from other scenes
	

	
	// Return to the loginpage
	@FXML 
	public void returnToLogin (ActionEvent event) throws IOException{
		if(event.getSource()== returnLogin){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Login.fxml"));
			main.loggedOut();
			root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Workshop 2");
			stage.show();
		}
	}
	
	// The viewmode method
	@FXML
	public void viewMode (ActionEvent event) throws IOException{
		if(event.getSource()== view){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Application.fxml"));

			root = loader.load();
			// Hides the components that are used in the editmode
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
	
	
	// Methods to show the different kinds of information
	
	@FXML
	public void showCompact(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== compact){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("CompactView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Compact view");
		stage.show();

	}

	
	 // Show the verbose view


	@FXML
	public void showVerbose(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== verbose){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("VerboseView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Verbose view");
		stage.show();

	}
	
	@FXML
	public void search(){
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Search.fxml"));
		
		try {
			

		
			       
			root = (Parent) loader.load();
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Search view");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	
 // Show one members single information
	@FXML
	public void showOneMember(ActionEvent event) throws IOException{

		Parent root = null;
		if(event.getSource()== singleMemberButton){
			List<Member> list = DB.members().findAll();

			for(Member mShowOne: list){
				System.out.println("The members name and id: "+mShowOne.name +" "+mShowOne.id);
			}
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("SingleMemberView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Single member view");
		stage.show();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		main.getLoggedIn();
		
	}

	
}
