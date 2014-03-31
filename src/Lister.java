import java.util.Iterator;
import java.util.NoSuchElementException;


public class Lister implements Iterator {
	PolyNode last;
	PolyNode current;
	
	public Lister(PolyNode head){
		this.current = head;
	}

	@Override
	public boolean hasNext() {
		return (current != null);
	}

	@Override
	public PolyNode next() {
		last = current;

		if (!hasNext())
			throw new NoSuchElementException("The Lister is empty.");
		
		current = current.getLink();
		return last;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException ("No remove operation!");
	}

}
