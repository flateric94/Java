package tp3;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestListCirculaire {

	@Test
	public void test() {
		CircularList Test1 = new CircularList();
		Test1.push("2eme elem");
		Test1.push("1er elem");
		Test1.append("3eme elem");
		Test1.dump(System.out);
		// On vérifie que Test1 n'est pas vide
		assertFalse(Test1.isEmpty());
		// Verifions size()
		assertEquals(Test1.size(), 3);
		// Verifions first()
		assertEquals(Test1.first(), "1er elem");
		// Verifions pop()
		assertEquals(Test1.pop(), "1er elem");
		// Reverifions size()
		assertEquals(Test1.size(), 2);
		// Reverifions pop()
		assertEquals(Test1.pop(), "2eme elem");
		// Revirifions size()
		assertEquals(Test1.size(), 1);
		// Reverifions pop()
		assertEquals(Test1.pop(), "3eme elem");
		// Revirifions size()
		assertEquals(Test1.size(), 0);	
		assertThrows(java.util.EmptyStackException.class, () -> {
			Test1.pop();
		});
		// On vérifie que Test1 est vide
		assertTrue(Test1.isEmpty());

	}
}
