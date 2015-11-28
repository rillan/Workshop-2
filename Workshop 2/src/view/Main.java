package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import Controller.MemberController;
import Database.BoatDAO;
import Database.DB;
import Database.MemberDAO;
import javafx.application.Application;
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
	private ListView composeList;
	@FXML
	private ListView verboseList;
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
	private Button returnFirstPage;

	@FXML
	public static String verifyLogged ="false";

	@FXML
	private Button delete;

	// Items for the login page
	@FXML
	private Button view;

	@FXML
	private TextField password;

	@FXML
	private TextArea applicationInformation;

	@FXML
	private Button login;

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
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("Editmode.fxml"));
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
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Editmode.fxml"));

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
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("singleMemberView.fxml"));

			root = (Parent) loader.load();
		}
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Single member view");
		stage.show();

	}
	public void listEmployees( ){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List members = session.createQuery("FROM Member").list(); 


			tx.commit();}
		catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
	@Override
	public void stop() {
		DB.closeSessionFactory();
	}
	public long getId() { return id; }
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
