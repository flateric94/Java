package adventure;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

class RoomTest {

	@Test
	public void testConstructor() {
		Room r1 = Room.from("Room of anti-patterns,SE,false");
		Room r2 = Room.from("Room of anti-pattern,NE,true");
		Room r3 = Room.from("Room of anti-patterns,SE,false");
		assertEquals(r1.getName(), r3.getName());
		//assertEquals(r1.getName(), r2.getName()); //c'est faux du coup
	}
	
	@Test
	public void loadRoomsTest() {
		// on fait comme si on lançait le jeu mais avec le fichier
		// CharactersFalse qui contient des erreurs
		Boolean val_test_error = false;  // on suppose y'a pas d'erreurs
		try {
		// Il va reperer l'erreur dans le fichier et donc va 
			String tab[] = {"RoomsFalse.dat","Characters.dat","Talisman.dat"};
			Cave.main(tab);
			val_test_error = true;
		}	catch(GameInitializationException e) {
			if (val_test_error == true) {
				fail("erreur"); // fail "laisse passer" l'erreur
			}
		}
	}
	

}
