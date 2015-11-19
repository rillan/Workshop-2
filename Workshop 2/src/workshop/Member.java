package workshop;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Database.DB;
import javafx.scene.control.ListView;
import workshop.Boat;

@Entity
@Table(name = "Member")


public class Member implements Iterator<Member> {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    public long id;
	@Column(name = "name")
	public String name;
	@Column(name = "personNumber")
	public String personNumber;
	@Column(name = "numberOfBoats")
	public int numberOfBoats = 0; 
	@Column(name = "listofBoats")
	public ArrayList <Boat> listofBoats = new ArrayList<Boat>();
	
	public Member(){
		
	}
	
	public Member(String name, String personNumber){
		
		this.name = name;
		this.personNumber = personNumber;
		
		

		
	}
	

	
	public  String getInfo(){
		return name +" " + personNumber +" "+ numberOfBoats;
		
	}
	
	public  String getVerbose(Member member){
		if(member.listofBoats.size() > 0){
		String info = "";
		
		for(Boat boat:listofBoats ){
		info += "Boattype "+boat.type +" Boatlength"+ boat.length+ ", ";
		
		}
		
		return "Memberinformation " +member.name +" " + member.personNumber +"\n Boatinformation "+ numberOfBoats+" "+ info ;
		}
		else{
			return "Memberinformation" +name +" " + personNumber + " Has 0 boats";
		}
	}
	
	public String getMemberAndBoatInfo(){
		return this.name +" " + this.personNumber +" "+ this.numberOfBoats;
	}
	
	
	
	public void updateMember(String name, String personNumber){
		this.name = name;
		this.personNumber = personNumber;
	}
	public void registerBoat(String type,int length){
		Boat boat = new Boat();
		boat.type = type;
		
		boat.length = length;
		this.numberOfBoats =numberOfBoats+1;
		this.listofBoats.add(boat);
		
	}
	public void deleteBoat(long id){
		DB.boats().findById(id);
		
		
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member next() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
