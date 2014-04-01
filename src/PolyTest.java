import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class PolyTest {
	
	
/*	static Polynomial add(Polynomial p2){
			
			PolyNode answer = ;
			answer = PolyNode.listCopy(this.head);
			
			for (PolyNode cursor = p2.head; cursor != null; cursor = cursor.getLink())
				answer.insert(cursor.getCoeff(), cursor.getExp());
			
			return new Polynomial(answer);

	}*/
	
	
	
	public static void main (String[] args){
		
		PolyNode a = null;
		a.insert(2., 5);
		a.insert(2, 4);
		System.out.println(a);
		
		double[][] ref = new double[][]{{2,2},{-3,1},{1,0}};
		
		PolyNode p1 = new PolyNode(ref);
		
		/*PolyNode p2 = new PolyNode(0,2);
		p2.insert(1, 1);
		p2.insert(2, 0);
		
		for (PolyNode c = p2; c!= null; c = c.getLink())
			System.out.println(c);
		
		Polynomial po1 = new Polynomial("3x^2-7x-6");
		
		System.out.println(po1);
		*/
		
		//System.out.println();
		Polynomial pl1 = new Polynomial("x^2-3x+12");
		Polynomial pl2 = new Polynomial(p1);
		
		char ch = 'a';
		System.out.printf("%s(x)          =   %s\n", ch++, pl1);
		System.out.printf("%s(x)          =   %s\n", ch++, pl2);
		
		pl1.InsertTerm("-3X^4");
		ch = 'a';
		System.out.printf("%s(x)          =   %s\n", ch++, pl1);
		
		ch = 'a';
		//create a new polynomial
		Polynomial pl3;
		pl3 = pl1.Add(pl2);
		
		//System.out.println("\nThe sum of p(x) and q(x) is" );
		System.out.printf("%s(x) + %s(x)   =   %s\n", ch++, ch++, pl3);
		
		ch ='a';
		//System.out.println("\nThe product of p(x) and q(x) is" );
		System.out.printf("%s(x) * %s(x)   =   %s\n", ch++, ch++, pl1.Multiply(pl2));
		
		ch='a';
		//System.out.println("\nThe derivative of a(x) " );
		System.out.printf("%s'(x)         =   %s\n", ch++, pl1 );

		clearConsole();
		
		try 
		{
			FileReader f = new FileReader("test.txt");
			Polynomial fileread = new Polynomial(f);
			ch = 'c' ;
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList())
					System.out.printf("%s(x)          =   %s\n", ch++, x );
			}
			
			ch = 'c';
			String str = "";
			if(fileread.getPolynomialList() != null){
				for (Polynomial x : fileread.getPolynomialList()){
					str = String.format("%s(5)          =>  %40s   =   %.2f\n", ch++, x, x.Evaluate(5.0) );
					
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
	
	static void clearConsole(){
		char c = '\n';
		int length = 50;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
}
