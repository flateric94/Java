package tp4;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;


import org.junit.jupiter.api.Test;

class CircularListITestString extends CircularListTestBase {

	@Test
	void testIterator() {
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		//creation de la circularlist
		CircularList<String> c1_isEmpty = new CircularList<>();
		Iterator<String> iterator_isEmpty = c1_isEmpty.iterator();
		//on vérifie qu'il n'y a rien
		assertFalse(iterator_isEmpty.hasNext());
		// on vérifie bien que ça renvoit une erreur ensuite pour next et remove
		checkNext(iterator_isEmpty::next);
		checkRemoveExc(iterator_isEmpty::remove);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<String> c1_seul = new CircularList<>();
		c1_seul.push("coucou");
		Iterator<String> iterator_seul = c1_seul.iterator();
		assertTrue(iterator_seul.hasNext());
		assertEquals(iterator_seul.next(),"coucou");
		assertFalse(iterator_seul.hasNext());
		checkNext(iterator_seul::next);
		iterator_seul.remove();
		checkRemoveExc(iterator_seul::remove);
		assertTrue(c1_seul.isEmpty());
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<String> c1_isFull = new CircularList<>();
		c1_isFull.push("A");
		c1_isFull.append("B");
		c1_isFull.append("C");
		c1_isFull.append("D");
		c1_isFull.append("E");
		
		assertFalse(c1_isFull.isEmpty());
		Iterator<String> iterator_isFull = c1_isFull.iterator();
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),"A");
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),"B");
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),"C");
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),"D");
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),"E");
		assertFalse(iterator_isFull.hasNext());
		checkNext(iterator_isFull::next);		
	}
	
	@Test
	void testSort() {
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		CircularList<String> c1_isEmpty = new CircularList<>();
		checkSortExc(c1_isEmpty::sort);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<String> c1_Seul = new CircularList<>();
		c1_Seul.push("coucou");
		c1_Seul.sort();
		assertEquals(c1_Seul.first(), "coucou");
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<String> c1_isFull = new CircularList<>();
		c1_isFull.push("1");
		c1_isFull.append("4");
		c1_isFull.append("2");
		c1_isFull.append("5");
		c1_isFull.append("3");
		
		c1_isFull.sort();
		assertEquals(c1_isFull.get(0), "1");
		assertEquals(c1_isFull.get(1), "2");
		assertEquals(c1_isFull.get(2), "3");
		assertEquals(c1_isFull.get(3), "4");
		assertEquals(c1_isFull.get(4), "5");
	}
	
	@Test
	void testForEach() {
		Consumer<String> test = (String param) -> System.out.println(param+" was edited");
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		CircularList<String> c1_isEmpty = new CircularList<>();
		c1_isEmpty.forEach(test);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<String> c1_Seul = new CircularList<>();
		// ça tourne en boucle...
		c1_Seul.push("coucou");
		c1_Seul.forEach(test);
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<String> c1_isFull = new CircularList<>();
		c1_isFull.push("1");
		c1_isFull.append("2");
		c1_isFull.append("3");
		c1_isFull.append("4");
		c1_isFull.append("5");
		
		c1_isFull.forEach(test);
	}

}