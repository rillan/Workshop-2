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
    private Button view;

    @FXML
    private TextField password;

    @FXML
    private Button login;

   
	@FXML
	public static String verifyLogged ="false";

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
	// Different methods for log in to the application and verifying that a user is logged in.
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

					Main.setLoggedIn();
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
		// Methods to verify if the user is logged in to the database or not.
	// the setMyVariable is used to 
	@FXML
	public static String setLoggedIn() {
		verifyLogged ="true";
		return verifyLogged;
	}
	

	@FXML
	public static String getLoggedIn() {

		return verifyLogged;
	}
	@FXML
	public static String loggedOut() {
		verifyLogged ="false";
		return verifyLogged;
	}
	
	@Override
	public void stop() {
		DB.closeSessionFactory();
	}
	public long getId() { return id; }
	

}
