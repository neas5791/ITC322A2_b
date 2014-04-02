import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Driver {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main (String [] args){
		boolean quit = false;
		int menu;
		do{
			System.out.println("Welcome to the Polynomial Driver by Sean Matkovich #11187033\n"
							+ "=============================================================");
			System.out.print("This program will demonstrate the implemented features of the Polynomial Class.\n"
							+ "\nSelect from the following menu by entering menu number at the prompt."
							+ "\n1. Create a Polynomial from keyboard. "
							+ "\n2. Open a text file which contains polynomial expressions."
							+ "\n3. Find the sum of two polynomials and display result. (P(x) + Q(x))"
							+ "\n4. Find the product of two polynomials and display results. (P(x) * Q(x))"
							+ "\n5. Evaluate a polynomial given a value. (P(2.5))"
							+ "\n6. Find the dervitive of a polynomial expression. (P'(x))"
							+ "\n7. Insert a single term into a polynomial."
							+ "\n8. "
							+ "\n9. "
							+ "\nMake your selection: ");
			menu = scanner.nextInt();
						
			switch (menu){
			case 1:
				quit = createPolynomial();
				break;
			case 2:
				quit = readPolynomialFile();
				break;
			default:
				quit = !quit;
				break;
			}
		}while (!quit);
	}
	
	public static boolean createPolynomial(){
		String expression;
		System.out.print("Please enter a polynomial expression at the prompt.\n\n"
						+ "The expression must be in the general form ax^n+bx-c\n"
						+ "where values for a, n and c can be negative. Ensure the\n"
						+ "use of the carat (^) symbol to designate exponent\n"
						+ "values. N.B. this input reader is for use with single\n"
						+ "variable polynomials only, multiple algebric terms are\n"
						+ "not recognized.\n\nEnter your polynomial expression here: ");
		expression = scanner.next();
		
		Polynomial p1 = new Polynomial(expression);
		System.out.println("The output of Polynomial.toString() is\n" + p1);
		
		suspend("Press enter to return to main menu....");

		return false;
	}
	
	public static boolean readPolynomialFile(){
		System.out.print("This method opens a text file and reads in the Polynomial expressions.\n\n"
				+ "Each line represents a new polynomial expression.\n"
				+ "i.e. the following lines are an example of how the file should look\n"
				+ "2x^3-4x^2+x-40\n"
				+ "6x^2+4x+10\n"
				+ "-2x^5+2.3x^-2+9x+840\n"
				+ "As each line is read, a new Polynomial object is created and stored in \n"
				+ "a List<Polynomial> for later access.\n\n"
				+ "Again the expression must be in the general form ax^n+bx-c\n"
				+ "where values for a, n and c can be negative. Ensure the\n"
				+ "use of the carat (^) symbol to designate exponent\n"
				+ "values. N.B. this input reader is for use with single\n"
				+ "variable polynomials only, multiple algebric terms are\n"
				+ "not recognized.\n");
		
		suspend("*** Press enter to read the file....");
		
		try 
		{
			FileReader f = new FileReader("test.txt");
			Polynomial fileread = new Polynomial(f);
			char ch = 'C' ;
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList())
					System.out.printf("%s(x)          =   %s\n", ch++, x );
			}
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
			
		suspend("Press enter to return to main menu....");
		return false;
	}
	
	private static void suspend(String str){
		BufferedReader buf = new BufferedReader (new InputStreamReader (System.in));
		String ent = null;
		
		do{
			System.out.println(str);
			try {
				ent = buf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(ent.equals("\n"));	
	}
}
