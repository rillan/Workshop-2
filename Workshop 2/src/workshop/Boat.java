	package workshop;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import workshop.Member;

@Entity
@Table(name = "Boat")


public class Boat implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    public long id;
    
	String type;
	int length;
	
	
	public Boat(String type, int length){
		this.type =type;
		this.length = length;
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
	
	public static void main(String[] args) {
	
		
}
}