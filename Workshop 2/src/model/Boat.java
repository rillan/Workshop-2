	package model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.Member;

@Entity
@Table(name = "Boat")


public class Boat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2483240910538198571L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    public long id;
    
	public String type;
	public int length;
	
	public long memberID;
	
	public Boat(String type, int length, long memberID){
		this.type =type;
		this.length = length;
		this.memberID = memberID;
	}
	public Boat() {
		
	}
	public static String getBoatInformation(Boat boat){
		return(boat.length+" "  +boat.type);
	}
	
	public void updateMember(String name, int length){
		this.type = name;
		this.length = length;
	}
	
	public static String getBoatOwner(Member member){
		String information = "";
		information += member.name + member.id + member.personNumber;
		int totalBoat = member.numberOfBoats;
		for(int i = 0; i < totalBoat; i++){
			information += member.numberOfBoats;
		}
		return information;
	}
	
	
}