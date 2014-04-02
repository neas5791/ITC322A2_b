import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import polynomial.Polynomial;


public class Driver {
	
	private static final String POLY1 = "12x^9+5x^6+13x^5-4x^4+2x^3+11x^2-6";
	private static final String POLY2 = "7x^9+2x^8+5x^6+2x^5+2x^3+9x^2-7x";
	
	private static Polynomial POLYNOMIAL1 = new Polynomial(POLY1);
	private static Polynomial POLYNOMIAL2 = new Polynomial(POLY2);
		
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
							+ "\n8. Quit."
							+ "\nMake your selection: ");
			menu = scanner.nextInt();
						
			switch (menu){
			case 1:
				createPolynomial();
				break;
			case 2:
				readPolynomialFile();
				break;
			case 3:
				sumPolynomials();
				break;
			case 4:
				multiplyPolynomial();
				break;
			case 5:
				evaluatePolynomial();
				break;
			case 6:
				derivePolynomial();
				break;
			case 7:
				insertPolynomialTerm();
				break;
			default:
				quit = !quit;
				System.out.println("See you later :) hope you had fun!!");
				break;
			}
		}while (!quit);
	}
	
	/**
	 * Static method to demonstrate creating a polynomial object from string input.
	 */
	public static void createPolynomial(){
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

	}
	
	/**
	 * Static method to demonstrate reading polynomial strings from a file and creating 
	 * polynomial objects.
	 */
	public static void readPolynomialFile(){
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
		
		suspend("*** Press enter to read the file and display results ***");
		
		try 
		{
			// FileReader f is the file name (including path) to open
			FileReader f = new FileReader("test.txt");
			Polynomial fileread = new Polynomial(f);
			char ch = 'C' ;
			
			System.out.println("The file contained " + fileread.getPolynomialList().size() + " lines of equations\n"
					+ "These are listed below\n");
			
			// if the file had no readable polynomials the list could be null
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList())
					System.out.printf("%s(x)          =   %s\n", ch++, x );
			}
			System.out.println();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
		suspend("Press enter to return to main menu....");

	}
	
	/**
	 * Static method to demonstrate polynomial addition using the Polynomial.Add() implementation 
	 */
	public static void sumPolynomials(){
		// This char is for function identification ie C(x) 
		char ch = 'C' ;
		System.out.println("Find the sum of the following polynomials");
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL1);
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL2);
		ch = 'C';
		System.out.printf("%s(x) + %s(x)   =   %s\n", ch++, ch++, POLYNOMIAL1.Add(POLYNOMIAL2));
		
		System.out.println();
			
		suspend("Press enter to return to main menu....");
	}
	
	/**
	 * Static method to demonstrate polynomial multiplication using the Polynomial.Multiply() implementation
	 */
	public static void multiplyPolynomial(){
		// This char is for function identification ie C(x)	
		char ch = 'C' ;
		System.out.println("Find the product of the following polynomials");
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL1);
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL2);
		ch = 'C';
		System.out.printf("%s(x) * %s(x)   =   %s\n", ch++, ch++, POLYNOMIAL1.Multiply(POLYNOMIAL2));
		
		System.out.println();
			
		suspend("Press enter to return to main menu....");
	}
	
	/**
	 * Static method evaluates a polynomial equation for a given decimal value
	 */
	public static void evaluatePolynomial(){
		// value will be the figure substituted into the polynomial
		double value = 0;
		// This char is for function identification ie C(x)		
		char ch = 'C' ;
		System.out.println("Evaluate a polynomial for a given input value\n"
				+ "Enter the value of x: ");
		// run the input in a loop so that we can capture any illegal input such as letters
		boolean test = true;
		do{
			try{
				// Read value from the console input
				value = scanner.nextDouble();
				test = false;
			} catch(InputMismatchException e){
				System.out.println("You must input a number! please try again\nEnter the value of x: ");
				test = true;;
				scanner.next();
			}
		}while(test);
		
		System.out.printf("%s(x)          =   %s\n", ch, POLYNOMIAL1);
		System.out.printf("%s(%.2f)       =   %.2f\n", ch, value,  POLYNOMIAL1.Evaluate(value));
				
		System.out.println();
			
		suspend("Press enter to return to main menu....");
	}
	
	public static void derivePolynomial(){
		
		char ch = 'C' ;
		System.out.println("Find the derivatie of the following polynomials");
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL1);
		System.out.printf("%s(x)          =   %s\n", ch++, POLYNOMIAL2);
		ch = 'C';
		System.out.printf("%s'(x)         =   %s\n", ch++, Polynomial.Derivative(POLYNOMIAL1));
		System.out.printf("%s'(x)         =   %s\n", ch++, Polynomial.Derivative(POLYNOMIAL2));
		
		System.out.println();
			
		suspend("Press enter to return to main menu....");
	}
	
	public static void insertPolynomialTerm(){
		String term;
		char ch = 'C' ;
		System.out.println("Insert a polynomial term into an existing equation.\n"
				+ "Enter the polynomial term in the general from  ax^n:");
		term = scanner.next();
		System.out.printf("%s(x)          =   %s\n", ch, POLYNOMIAL1);
		POLYNOMIAL1.InsertTerm(term);
		System.out.printf("%s(x)          =   %s\n", ch, POLYNOMIAL1);

		System.out.println();
			
		suspend("Press enter to return to main menu....");		
	}
		
	/**
	 * Method to halt screen an wait for input before proceeding
	 * @param str is the String contents for display when halted
	 */
	private static void suspend(String str){
		// create a buffer to capture system.in activity
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
