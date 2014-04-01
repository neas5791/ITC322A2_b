import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Polynomial {
	private PolyNode head;
	private List<Polynomial> polynomialList;
	
	/**
	 * Constructs new Polynomial given an equation passed in as a string format
	 * 
	 * Correct format takes the form  ==>  		3x^2-4x+5
	 *  
	 * @param equation the string which represents the polynomial
	 * @postcondition Polynomial is constructed as a linked list. manyNodes is incremented to count the
	 */
	public Polynomial (String equation){
		head = new PolyNode(equation);
	}
	
	/**
	 * Construct which create a new list given the head reference of a PolyNode object
	 * @param head
	 */
	public Polynomial (PolyNode head){
		this.head = head;
	}

	/**
	 * Constructor opens a text file which contains polynomial equations. 
	 * The equations are formatted and new Polynomial list are created from each
	 * they are store in a List<Polynomial> object for future iteration
	 * 
	 * If typographical error exist in the file the given line will be disregarded 
	 * and consequently not appear in the polynomialList
	 * @param file is the File which is to be open
	 * @postcondition the polynomialList object contains reference to all equations within the 
	 * file
	 */
	public Polynomial(FileReader file){

		List<String> equationList = LineReader(file);
		if (equationList.size() == 1){
			head = new PolyNode(equationList.get(0));
			return;
		}
		polynomialList = new ArrayList<Polynomial>();
		for (String equation : equationList)
			polynomialList.add(new Polynomial(equation));
	}
		
	/**
	 * This private method is for opening a file and returning the equation contents
	 * line by line.
	 * @param file is the File which is to be open
	 * @return List<String> containing each line of text which represents an equation
	 */
	private List<String> LineReader(FileReader file){
		
		List<String> equationList = new ArrayList<String>();
		
		try {
			BufferedReader buffer = new BufferedReader(file);
			String str_line;

			while ((str_line = buffer.readLine()) != null)
			{
				str_line = str_line.trim();
				
				// makes sure the string isn't an empty line
				if ((str_line.length())!=0)
					equationList.add(str_line);
			}
			buffer.close();
		}
		catch (IOException e) 	{
			e.printStackTrace();
		}
	
		return equationList;
	}
	
	/**
	 * performs polynomial addition in the form
	 * 
	 * PolynomialThree= PolynomialOne.add(PolynomialTwo);
	 * 
	 * The rules for the addition of polynomials are as follows:
	 * 1. If the powers are equal, the coefficients are algebraically added.
	 * 2. If the powers are unequal, the term with the higher power is inserted in the new polynomial.
	 * 3. If the exponent is 0, it represents x0, which is 1. The value of the term is therefore the value of the coefficient.
	 * 4. If the result of adding the coefficients results in 0, the term is dropped (0 times anything is 0).
	 * For example, the polynomial sum (27x3+15x2+12x)+(18x2+10x+8)is calculated as
	 * 
	 * 									27x3+33x2+22x+8
	 *
	 * @param p2 is the second polynomial expression to add
	 * @return a new polynomial linked list
	 */
	public Polynomial Add(Polynomial p2){
		
		PolyNode answer;
		answer = PolyNode.ListCopy(this.head);
		
		for (PolyNode cursor = p2.head; cursor != null; cursor = cursor.getLink())
			answer.insert(cursor.getCoeff(), cursor.getExp());
		
		return new Polynomial(answer);
	}

	/**
	 * Evaluates the product of polynomials
	 * @param factor is the polynomial expression to multiply by
	 * @return the product of two polynomial
	 */
	public Polynomial Multiply(Polynomial factor){
		// The logic provide in the below code represents this worked example
		//	term1 	= (x^2 + 7x + 10)
		//	term2 	= (x^2 + 4x + 3)
		//
		//	answer 	= term1 * term2
		//         	= (x^2 + 7x + 10)(x^2 + 4x + 3)
		//         	= x^2(x^2 + 4x + 3) + 7x(x^2 + 4x + 3) + 10(x^2 + 4x + 3)
		//		   	= (x^4) + (4x^3) + (3x^2) + (7x^3) + (28x^2) + (21x) + (10x^2) + (40x) + (30)
		// the last line is shows the polynomial terms which would be inserted into the PolyNode answer
		// **The PolyNode collects like terms upon insertion
		
		PolyNode term1, term2; 
		PolyNode answer = null;
		
		double co;
		double ex;
		
		for ( term1 = head; term1 != null; term1 = term1.getLink()) {
			for (term2 = factor.head; term2!= null; term2 = term2.getLink()){
				
				co = term1.getCoeff()* term2.getCoeff();
				ex = term1.getExp()+term2.getExp();
				
				if (answer == null)
					answer = new PolyNode(co, ex, null);
				else
					answer.insert(co, ex);
			}
		}
		return new Polynomial(answer);
	}

	/**
	 * Static method finds the derivative of polynomial object passed in
	 * @param f is the object which represents the polynomial equation
	 * @return the derivative of the polynomial expression
	 */
	public static Polynomial Derivative(Polynomial f){
		PolyNode derivative = null;
		
		double co;
		double ex;
		
		for (PolyNode cursor = f.head; cursor != null; cursor = cursor.getLink()){
			
			co = cursor.getCoeff()*cursor.getExp();
			ex = cursor.getExp()-1;
			
			if (co == 0)
				continue;
			else if(derivative == null)
				derivative = new PolyNode(co, ex, null);
			else
				derivative.insert(co, ex);
		}

		return new Polynomial (derivative);
	}
	
	/**
	 * Gets the polynomial List which is constructed after reading
	 * file with multiple equation lines 
	 * @return the list of Polynomials read from a file 
	 */
	public List<Polynomial> getPolynomialList(){
		if (polynomialList != null)
			return polynomialList;
		
		return null;
	}

	/**
	 * Evaluates the polynomial expression given for a given value.
	 * @param x the value for which to evaluate the polynomial
	 * @return the result of substituting the givn value into the polynomial expression
	 */
	public double Evaluate(double x){
		double result;
		
		if (head == null)
			throw new NoSuchElementException ("This polynomial expression doesn't exist, please add an equation!");
		
		result = 0;
		
		for (PolyNode cursor = head; cursor != null; cursor = cursor.getLink()){
			result += (cursor.getCoeff()*(Math.pow(x, cursor.getExp())));
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = "";
		for (PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
			str = str + cursor;
		
		return str;
	}

	
	/********************************************************************************************************/	

	
}
