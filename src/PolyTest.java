import java.io.FileReader;
import java.io.FileNotFoundException;


public class PolyTest {
	
	public static void main (String[] args){
		PolyNode p1 = new PolyNode("2x^2-4.5x^3+3x-10");
		
		//for (PolyNode cursor = p1; cursor != null; cursor = cursor.getLink())
			//System.out.print(cursor);
		
		System.out.println();
		Polynomial pl1 = new Polynomial("x^7-3x^2+12.3x+15.4");
		Polynomial pl2 = new Polynomial(p1);
		
		System.out.println("p(x)          =   " + pl1);
		System.out.println("q(x)          =   " + pl2);
		
		//create a new polynomial
		Polynomial pl3;
		pl3 = pl1.add(pl2);
		
		//System.out.println("\nThe sum of p(x) and q(x) is" );
		System.out.println("p(x) + q(x)   =   " + pl3);
		
		try 
		{
			FileReader f = new FileReader("test.txt");
			Polynomial fileread = new Polynomial(f);
			char c = 'b';
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList())
					System.out.printf("%s(x)          =   %s\n", c++, x );
			}
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		
		
		
	}
}
