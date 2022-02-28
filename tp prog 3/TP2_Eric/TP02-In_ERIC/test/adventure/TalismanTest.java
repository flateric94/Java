package adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class TalismanTest {
    @Test
    public void testConstructor() {
    	// je crée mes talismans à tester
        Talisman t=Talisman.from("PlainTalisman");
        Talisman t2 = Talisman.from("test", 30);
        Talisman t4 = Talisman.from("test", 50);
        Talisman t5 = Talisman.from("test", 30);
        assertEquals(t.getName(),"PlainTalisman");
        assertEquals(t2.getName(),t4.getName());
        assertEquals(t2.getForce(),t5.getForce());
        assertNotSame(t2,t4);
    }
    
    @Test
    public void testloadTalismans() {
    	// on fait comme si on lançait le jeu mais avec le fichier
    	// CharactersFalse qui contient des erreurs
   		Boolean val_test_error = false;  // on suppose y'a pas d'erreurs
 		try {
 		// Il va reperer l'erreur dans le fichier et donc va 
    		String tab[] = {"Rooms.dat","Characters.dat","TalismansFalse.dat"};
    		Cave.main(tab);
    		val_test_error = true;
    		}	catch(GameInitializationException e) {
    		if (val_test_error == true) {
    			fail("erreur"); // fail "laisse passer" l'erreur
    		}
    	}
    }
}
