import java.io.FileReader;
import java.io.FileNotFoundException;


public class PolyTest {
	
	public static void main (String[] args){
		PolyNode p1 = new PolyNode("2x^2+3x-1");
		
		//for (PolyNode cursor = p1; cursor != null; cursor = cursor.getLink())
			//System.out.print(cursor);
		
		System.out.println();
		Polynomial pl1 = new Polynomial("x^2-3x+12");
		Polynomial pl2 = new Polynomial(p1);
		
		System.out.println("p(x)          =   " + pl1);
		System.out.println("q(x)          =   " + pl2);
		
		//create a new polynomial
		Polynomial pl3;
		pl3 = pl1.Add(pl2);
		
		//System.out.println("\nThe sum of p(x) and q(x) is" );
		System.out.println("p(x) + q(x)   =   " + pl3);
		
		//System.out.println("\nThe product of p(x) and q(x) is" );
		System.out.println("p(x) * q(x)   =   " + pl1.Multiply(pl2));
		
		//System.out.println("\nThe product of p(x) and q(x) is" );
		System.out.println("p'(x)         =   " + Polynomial.Derivative(pl1.Multiply(pl2)));
		
		try 
		{
			FileReader f = new FileReader("test.txt");
			Polynomial fileread = new Polynomial(f);
			char c = 'b';
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList())
					System.out.printf("%s(x)          =   %s\n", c++, x );
			}
			
			c = 'b';
			String str = "";
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList()){
					str = String.format("%s(5)          =>  %40s   =   %.2f\n", c++, x, x.Evaluate(5.0) );
					
					str = str.replaceAll("[x]", "5");
					System.out.print(str);
				}
			}
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		
		
		
	}
}
