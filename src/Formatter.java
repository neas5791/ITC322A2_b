
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class Formatter {
	
	public static List<Double[]> FormatStripper(String equation){
		
		// set up an array for the equation terms
		// these are the individual terms of the polynomial equation
		// (x^2)  (+  2x)  (+  1)
		String[] terms;
		
		// to simplify splitting changed all negative to +-
		// (?<!\\^|^) makes sure that any exponent values or leading negatives are not changed 
		equation = equation.replaceAll("(?<!\\^|^)[-]", "+-"); 
		// split the polynomial expression into individual terms
		terms = equation.split("\\+"); 
		
		/**
		 * Output of general equation after this will reflect the below example
		 * 		=> 		2x^-2+3x-1						* the actual equation should be in this format
		 * 		=>		2x^-2 		+ 3x 	- 1			* padded with white space for visibility only in this example
		 * 		=>		2x^-2   	+ 3x 	+- 1		* note the negative exponent doesn't change
		 *  	=>		[2x^-1]		[3x] 	[-1]		* terms[] contains three items of data
		 *  
		 */
		
		// this loop further refines the terms into general forms
		// for consistency
		for (int i = 0; i < terms.length; i++) {

			// checks for coefficients of the value +/- 1
			// replaces +/- with +1 or -1 respectively
			if (terms[i].matches("^[-+]?[a-zA-Z]+\\^?\\d*\\.?\\d*|^[-+]?[a-zA-Z]+"))
				terms[i] = terms[i].replaceFirst("[a-zA-Z]{1}", "1x");
			
			// Looks for terms with exponents of value 1 and appends "^1" to the string
			if ( terms[i].matches("^[-+]?\\d*\\.?\\d*[a-zA-Z]+"))
				terms[i]+= "^1";				
			// Looks for terms with exponents of value 0 and appends "x^0" to the string
			else if ( terms[i].matches("^[-+]?\\d*\\.?\\d+"))
				terms[i] += "x^0";
	
			try {
				// Checks for terms with incorrect formating
				if (!terms[i].matches("^[-+]?\\d*\\.?\\d*[a-zA-Z]+\\^{1}\\(?[-+]?\\d*\\.?\\d+\\)?|^[a-zA-Z]+$|^\\d+$"))
					throw new NumberFormatException ("Line formating is corrupt, trying to decode " + terms[i]);
			}
			catch (NumberFormatException nfe) {
				return null;
			}
		}
		
		// List of double[] will contain the coefficient and exponent data for the respective 
		// term of the polynomial equation parsed. Each term will be represented as a double[] 
		// in the form {coefficient, exponent}
		List<Double[]> stripedEquation = new ArrayList<Double[]>();

		// Creates a temporary string array to hold coefficients and exponent data in string form after String.split operation
		// String values are then parse as double values to the double[]
		// and adds these to the List<String
		String[] temp = new String[2];
		Double[] monomial;

		for (int i = 0; i< terms.length ; i++){
			// splits the string into coefficient and exponent strings
			temp = terms[i].split("[a-zA-Z]+\\^?");
			monomial = new Double[] {Double.parseDouble(temp[0]),Double.parseDouble(temp[1])};
			stripedEquation.add(monomial);
		}

		return stripedEquation;
	}
}
