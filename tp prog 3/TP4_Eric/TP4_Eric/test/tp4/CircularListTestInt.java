package tp4;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Iterator;
import java.util.function.Consumer;


import org.junit.jupiter.api.Test;

class CircularListITestInt extends CircularListTestBase {

	@Test
	void testIterator() {
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		//creation de la circularlist
		CircularList<Integer> c1_isEmpty = new CircularList<>();
		Iterator<Integer> iterator_isEmpty = c1_isEmpty.iterator();
		//on vérifie qu'il n'y a rien
		assertFalse(iterator_isEmpty.hasNext());
		// on vérifie bien que ça renvoit une erreur ensuite pour next et remove
		checkNext(iterator_isEmpty::next);
		checkRemoveExc(iterator_isEmpty::remove);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<Integer> c1_seul = new CircularList<>();
		c1_seul.push(94);
		Iterator<Integer> iterator_seul = c1_seul.iterator();
		assertTrue(iterator_seul.hasNext());
		assertEquals(iterator_seul.next(),94);
		assertFalse(iterator_seul.hasNext());
		checkNext(iterator_seul::next);
		iterator_seul.remove();
		checkRemoveExc(iterator_seul::remove);
		assertTrue(c1_seul.isEmpty());
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<Integer> c1_isFull = new CircularList<>();
		c1_isFull.push(1);
		c1_isFull.append(2);
		c1_isFull.append(3);
		c1_isFull.append(4);
		c1_isFull.append(5);
		
		assertFalse(c1_isFull.isEmpty());
		Iterator<Integer> iterator_isFull = c1_isFull.iterator();
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),1);
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),2);
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),3);
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),4);
		assertTrue(iterator_isFull.hasNext());
		assertEquals(iterator_isFull.next(),5);
		assertFalse(iterator_isFull.hasNext());
		checkNext(iterator_isFull::next);		
	}
	
	@Test
	void testSort() {
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		CircularList<Integer> c1_isEmpty = new CircularList<>();
		checkSortExc(c1_isEmpty::sort);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<Integer> c1_Seul = new CircularList<>();
		c1_Seul.push(67);
		c1_Seul.sort();
		assertEquals(c1_Seul.first(), 67);
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<Integer> c1_isFull = new CircularList<>();
		c1_isFull.push(1);
		c1_isFull.append(4);
		c1_isFull.append(2);
		c1_isFull.append(5);
		c1_isFull.append(3);
		
		c1_isFull.sort();
		assertEquals(c1_isFull.get(0), 1);
		assertEquals(c1_isFull.get(1), 2);
		assertEquals(c1_isFull.get(2), 3);
		assertEquals(c1_isFull.get(3), 4);
		assertEquals(c1_isFull.get(4), 5);
	}
	
	@Test
	void testForEach() {
		Consumer<Integer> test = (Integer param) -> System.out.println(param+" was edited");
		
		// TEST POUR UNE CIRCULARLIST VIDE
		
		CircularList<Integer> c1_isEmpty = new CircularList<>();
		c1_isEmpty.forEach(test);
		
		// TEST POUR UNE CIRCULARLIST A UN SEUL ELEMENT
		
		CircularList<Integer> c1_Seul = new CircularList<>();
		// ça tourne en boucle...
		c1_Seul.push(43);
		c1_Seul.forEach(test);
		
		// TEST POUR UNE CIRCULARLIST A PLUSIEURS ELEMENTS
		
		CircularList<Integer> c1_isFull = new CircularList<>();
		c1_isFull.push(1);
		c1_isFull.append(2);
		c1_isFull.append(3);
		c1_isFull.append(4);
		c1_isFull.append(5);
		
		c1_isFull.forEach(test);
	}

}