package frontend.others;

import java.util.ArrayList;
import java.util.Collections;

public final class Validate {

    /***
     * Checks if the RUT is valid
     * @param rut
     * @return
     */
	public static boolean checkRUT(String rut) {
		
		char num = rut.charAt(rut.length() - 1);
		char[] aux = rut.substring(0, rut.length() - 1).toCharArray();
		
		int sum = 0;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		for (char i : aux)
			digits.add(i -'0');
		
		Collections.reverse(digits);
		int k = 2;
		for (int i : digits) {		
			sum += i*k; k++;
			if (k > 7)
				k = 2;
		}
		
		int ver = 11 - sum % 11;
		char fn = 'k';
		
		switch (ver) {
		case 11:
			fn = '0';
		break;
		case 10:
			fn = 'k';
			break;
		default:
			fn = Integer.toString(ver).toCharArray()[0];
		break;
		}
		
		return num == fn;
	}
}
