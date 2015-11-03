package workshop;

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

	public int numberOfBoats = 0; 
	
	
	public Member(){
		
	}
	
	public Member(String name, String personNumber){
		
		this.name = name;
		this.personNumber = personNumber;
		
		

		
	}
	

	
	public  String getInfo(){
		return name +" " + personNumber +" "+ numberOfBoats;
		
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
