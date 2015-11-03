	package workshop;
import workshop.Member;
public class Boat {
	
	String type;
	int length;
	private int memberid = 0;
	
	public Boat(String type, int length){
		this.type =type;
		this.length = length;
	}
	public Boat() {
		
	}
	public static String getBoatInfo(Member member){
		String information = "";
		information += member.name + member.id + member.personNumber;
		int totalBoat = member.numberOfBoats;
		for(int i = 0; i < totalBoat; i++){
			information += member.numberOfBoats;
		}
		return information;
	}
	
	public static void main(String[] args) {
		Member rolf = new Member("Rolf Sten", "23");
		Member rolf2 = new Member("Rolf Sten", "23");
//		rolf.registerBoat(Motor, 15);
		System.out.println(getBoatInfo(rolf));
		System.out.println(getBoatInfo(rolf2));
		System.out.println("test");
		
}
}