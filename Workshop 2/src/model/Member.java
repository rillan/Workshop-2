package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Database.DB;
import javafx.scene.control.ListView;
import model.Boat;

@Entity
@Table(name = "Member")


public class Member {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    public long id;
	@Column(name = "name")
	public String name;
	@Column(name = "personNumber")
	public String personNumber;
	@Column(name = "month")
	public String month;
	@Column(name = "numberOfBoats")
	public int numberOfBoats = 0; 
	@Column(name = "listofBoats")
	
	public ArrayList <Boat> listofBoats = new ArrayList<Boat>();
	
	public Member(){
		
	}
	public void removeBoat(long memberid, long boatID){
		Member member = DB.members().findById(memberid);
		Boat boat = (Boat)DB.boats().findById(boatID);
		
			member.listofBoats.remove(boat);
	}
	
	public Member(String name, String personNumber){
		
		this.name = name;
		this.personNumber = personNumber;
		if(personNumber.substring(4, 6).equals("01") ){
			this.month ="January";
		}
		if(personNumber.substring(4, 6).equals("02") ){
			this.month ="Februari";
		}
		if(personNumber.substring(4, 6).equals("03") ){
			this.month ="Mars";
		}
		if(personNumber.substring(4, 6).equals("04") ){
			this.month ="April";
		}
		if(personNumber.substring(4, 6).equals("05") ){
			this.month ="May";
		}
		if(personNumber.substring(4, 6).equals("06") ){
			this.month ="June";
		}
		if(personNumber.substring(4, 6).equals("07") ){
			this.month ="July";
		}
		if(personNumber.substring(4, 6).equals("08") ){
			this.month ="August";
		}
		if(personNumber.substring(4, 6).equals("09") ){
			this.month ="September";
		}
		if(personNumber.substring(4, 6).equals("10") ){
			this.month ="October";
		}
		if(personNumber.substring(4, 6).equals("11") ){
			this.month ="November";
		}
		if(personNumber.substring(4, 6).equals("12") ){
			this.month ="December";
		}
		
		

		
	}
	

	
	public  String getCompose(){
		return "Name: " +name +" " +" Personnumber: "+ personNumber +" Number of Boats: " + numberOfBoats;
		
	}
	
	public  String getVerbose(Member member){
		if(member.listofBoats.size() > 0){
		String info = "";
		
		for(Boat boat:listofBoats ){
		info += "Boattype " +boat.type +" Boatlength "+ boat.length+ ", " +"Boatid " + boat.id;
		
		}
		
		return "Membername: "+ member.name + " Personnumber: " +member.personNumber +" Id: " + member.id +"\n Boatinformation "+ numberOfBoats+" "+ info ;
		}
		else {
			return "Membername: " +name +" Personnumber: " + personNumber + " Id: "+member.id+ " Has 0 boats";
		
	}
	}
	public String getMemberAndBoatInfo(){
		return this.name +" " + this.personNumber +" "+ this.numberOfBoats;
	}
	
	
	
	public void updateMember(String name){
		this.name = name;
		
	}
	public void registerBoat(Boat boat){
		
		this.numberOfBoats =numberOfBoats+1;
		this.listofBoats.add(boat);
		
	}
	

	
	public long getId(){
		return this.id;
	}

	

	

	

	
}
