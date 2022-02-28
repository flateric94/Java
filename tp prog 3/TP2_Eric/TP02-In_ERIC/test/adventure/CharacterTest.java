package adventure;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import org.junit.jupiter.api.Test;

class CharacterTest {

	@Test
	void ConstructorTest() {
		Character eric = Character.from("eric,anneau,23");
		Character eric_clone = Character.from("eric_clone,anneau,89");
		assertEquals(eric.getShield(),eric_clone.getShield());
		assertNotEquals(eric, eric_clone);
				
	}
	
	public void loadCharactersTest() {
		// on fait comme si on lançait le jeu mais avec le fichier
		// CharactersFalse qui contient des erreurs
		Boolean val_test_error = false;  // on suppose y'a pas d'erreurs
		try {
			// Il va reperer l'erreur dans le fichier et donc va 
			String tab[] = {"Rooms.dat","CharactersFalse.dat","Talisman.dat"};
			Cave.main(tab);
			val_test_error = true;
			}	catch(GameInitializationException e) {
				if (val_test_error == true) {
					fail("erreur"); // fail "laisse passer" l'erreur
			}
		}
	}
}
