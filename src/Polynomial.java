import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
	public Polynomial add(Polynomial p2){
		
		PolyNode newHead;
		newHead = PolyNode.ListCopy(this.head);
		
		for (PolyNode cursor = p2.head; cursor != null; cursor = cursor.getLink())
			newHead.insert(cursor.getCoeff(), cursor.getExp());
		
		return new Polynomial(newHead);
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
