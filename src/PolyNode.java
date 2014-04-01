import java.util.List;

public class PolyNode {
	
	private double coeff;
	private double exp;
	private PolyNode link;
	
	/**
	 * Constructor initializes object with default values
	 * @postcondition new object is created
	 *  
	 */
	public PolyNode(){
		coeff = 0;
		exp = 0;
		link = null;
	}
	
	/**
	 * Constructs new Polynomial Linked list from equation passed from system.in
	 * @param equation the polynomial expression to be placed into the linked list
	 */
	public PolyNode(String equation){
		
		List<String []> polynomial = Formatter.FormatStripper(equation);
		if (polynomial != null){
			boolean isHead = true;
			for (String[] x : polynomial){
				if (isHead) {
					coeff = Double.parseDouble(x[0]);
					exp = Double.parseDouble(x[1]);
					isHead = false;
				}
				else
					insert(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
			}
		}
	}
	
	/**
	 * Constructor initializes object with coefficient and exponent values
	 * @param co is the coefficient of the polynomial term
	 * @param ex is the exponent factor of the polynomial term
	 * @param next is the next term in the polynomial equation
	 * 
	 * @postcondition the object is 
	 */
	public PolyNode(double co, double ex, PolyNode next){
		
		// capture illegal zero inputs
		if (co == 0)
			throw new IllegalArgumentException ("The coefficient can not be zero pal!");
		
		coeff = co; 
		exp = ex;
		link = next;
		
	}
	
	/**
	 * Inserts a new polynomial term before current term
	 * @param co is the coefficient of the polynomial term
	 * @param ex is the exponent factor of the polynomial term
	 * @postcondition the list is increased in size and the new node is placed before the current node
	 */
	protected void insertBefore(double co, double ex){
		link = new PolyNode(coeff, exp, link);
		coeff = co;
		exp = ex;
	}
	
	/**
	 * Inserts a new polynomial term after current term
	 * @param co is the coefficient of the polynomial term
	 * @param ex is the exponent factor of the polynomial term
	 * @postcondition the list is increased in size and the new term is placed after the current term
	 */
	protected void insertAfter(double co, double ex){
		link = new PolyNode(co, ex, link);
	}
	
	/**
	 * Extends the current list by inserting the polynomial term in order of exponent (from largest to smallest)
	 * @param co is the coefficient of the polynomial term
	 * @param ex is the exponent factor of the polynomial term
	 * @postcondition the list is increased in size and the new term is placed ascending order of exponent magnitude.
	 */
	public void insert(double co, double ex){
		
		if (co == 0)
			return;
		
		if (exp == ex) {
			coeff += co;
			return;
		}
		
		if (exp > ex){
			if (link == null)
				insertAfter(co,ex);
			else
				link.insert(co,ex);
		}
		else
			insertBefore(co,ex);
	}
	
	/**
	 * Get the coefficient value of this polynomial term
	 * @return the coefficient value for this polynomial term.
	 */
	public double getCoeff(){
		return coeff;
	}
	
	/**
	 * Get the exponent value of this polynomial term
	 * @return the exponent value of this polynomial term
	 */
	public double getExp(){
		return exp;
	}
	
	/**
	 * Get the next referenced term in the list
	 * @return a reference to the next term in the list
	 */
	public PolyNode getLink(){
		return link;
	}
	
	/**
	 * Determines if the polynomial term has a larger exponent value, used to determine ordered place in list
	 * @param term is a polynomial term for comparison
	 * @return true if the term has a greater polynomial value than
	 */
	public boolean isGreater(PolyNode term){
		//trap null values being passed in
		if (term == null)
			return false;
		
		if (this.exp < term.exp)
			return true;
		return false;
	}


	public static PolyNode ListCopy(PolyNode source){
		
		PolyNode copyHead;
		PolyNode copyTail;
		
		try {
			if (source == null)
				return null;
			
			copyHead = new PolyNode(source.coeff, source.exp, null);
			copyTail = copyHead;
			
			while (source.link != null) {
				source = source.link;
				copyTail.insert(source.coeff, source.exp);
				copyTail = copyTail.link;
			}
			
			return copyHead;
		}
		catch (OutOfMemoryError oe)
		{
			oe.printStackTrace();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str;
		
		if (coeff > 0 )
			str = String.format("+ %s", coeff);
		else if (coeff < 0)
			str = String.format("- %s", Math.abs(coeff));
		else 
			return "";
		
		if (exp == 1)
			str = str + "x ";
		else if (exp < 0 || exp > 0)
			str = str + String.format("x^%s ", exp);
		
		return str;
	
	}

}
