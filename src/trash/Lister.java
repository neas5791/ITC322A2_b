package trash;
import java.util.NoSuchElementException;
import polynomial.PolyNode;


public class Lister {
	PolyNode last;
	PolyNode current;
	
	public Lister(PolyNode head){
		this.current = head;
	}

	public boolean hasNext() {
		return (current != null);
	}

	public PolyNode next() {
		last = current;

		if (!hasNext())
			throw new NoSuchElementException("The Lister is empty.");
		
		current = current.getLink();
		return last;
	}

	public void remove() {
		throw new UnsupportedOperationException ("No remove operation!");
	}

}
