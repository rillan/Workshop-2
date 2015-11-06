package application;

import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		Pattern patternB = Pattern.compile("[A-Za-z,0-9 ]+");
		String boat = patternB.toString();
		String e = "qeeee,23";
		if(e.matches(boat))
		System.out.println("t");
	}

}
