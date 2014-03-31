
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class Formatter {
	
	public static List<String[]> FormatStripper(String equation){
		// these are the individual terms of the polynomial equation
		String[] terms;
		// List of String[] containing the coefficient and exponent data
		// Corresponding to the equation string passed in
		List<String[]> termList = new ArrayList<String[]>();

		try
		{
			
			// to simplify splitting changed all negative to +-
			// (?<!\\^) makes sure that any exponent values are not changed
			equation = equation.replaceAll("(?<!\\^)[-]", "+-"); 
			// split the polynomial expression into individual terms
			terms = equation.split("\\+"); 
			
			for (int i = 0; i < terms.length; i++) {

				// checks for coefficients of the value +/- 1
				// replaces +/- with +1 or -1 respectively
				if (terms[i].matches("^[-+]?[a-zA-Z]+\\^?\\d*\\.?\\d*|^[-+]?[a-zA-Z]+"))
					terms[i] = terms[i].replaceFirst("[a-zA-Z]{1}", "1x");
				
				// checks for exponents of value 1
				if ( terms[i].matches("^[-+]?\\d*\\.?\\d*[a-zA-Z]+"))
					terms[i]+= "^1";				
				// checks for exponents of value 1
				else if ( terms[i].matches("^[-+]?\\d*\\.?\\d+"))
					terms[i] += "x^0";
				
				// Checks for terms with incorrect formating
				if (!terms[i].matches("^[-+]?\\d*\\.?\\d*[a-zA-Z]+\\^{1}\\(?[-+]?\\d*\\.?\\d+\\)?|^[a-zA-Z]+$|^\\d+$"))
					throw new NumberFormatException ("Line formating is corrupt, trying to decode " + terms[i]);
			}
			
			// creates a string array of coefficients and exponent data per term of the equation
			// and adds these to the List<String
			String[] temp = new String[2];
			for (int i = 0; i< terms.length ; i++){
				temp = terms[i].split("[a-zA-Z]+\\^?");
				termList.add(temp);
			}
		}
		catch (NumberFormatException nfe)
		{
			return null;
		}
		return termList;
	}
}
