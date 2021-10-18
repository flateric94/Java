package tp3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class CircularListTest {

	@Test
	public void testEmpty() {
		CircularList list = new CircularList();
		assertTrue(list.isEmpty());
		list.append("A");
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testAppendNull() {
		CircularList list = new CircularList();
		list.append("A");
		assertEquals(list.first(),"A");
		assertEquals(list.last(),"A");
		
	}
	
	@Test
	public void testAppend() {
		CircularList list = new CircularList();
		list.append("A");
		list.append("B");
		assertEquals(list.first(),"A");
		assertEquals(list.last(),"B");
		list.append("C");
		list.append("D");
		assertEquals(list.first(),"A");
		assertEquals(list.last(),"D");
		
	}
	
	@Test
	public void testPushNull() {
		CircularList list = new CircularList();
		list.push("A");
		assertEquals(list.first(),"A");
		assertEquals(list.last(),"A");
	}
	
	@Test
	public void testPush() {
		CircularList list = new CircularList();
		list.push("D");
		list.push("C");
		list.push("B");
		list.push("A");
		assertEquals(list.first(),"A");
		assertEquals(list.last(),"D");
	}
	
	@Test
	public void testSize() {
		CircularList list = new CircularList();
		assertEquals(list.size(),0);
		list.push("D");
		assertEquals(list.size(),1);
		list.push("C");
		assertEquals(list.size(),2);
		list.push("B");
		assertEquals(list.size(),3);
		list.push("A");
		assertEquals(list.size(),4);
	}
	
	@Test
	public void testGet() {
		CircularList list = new CircularList();
		assertEquals(list.get(0),null);
		assertEquals(list.get(20),null);
		list.append("A");
		list.append("B");
		list.append("C");
		list.append("D");
		//création d'une liste circulaire {A,B,C,D}
		assertEquals(list.get(0),"A");
		assertEquals(list.get(8),"A");
		assertEquals(list.get(2),"C");
		assertEquals(list.get(6),"C");
		assertEquals(list.get(10),"C");
	}
	
}
