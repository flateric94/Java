package tp4;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import tp4.CircularList;

public class CircularListSimpleOpsTest extends CircularListTestBase {
   
    /*
     * Test demo
     */
    @Test
    public void demoTest() {
        CircularList<String> list=new CircularList(); // on crée une liste
        assertTrue(list.isEmpty()); // Elle devrait être vide
        list.push("aaa"); // on y ajoute quelque chose
        assertFalse(list.isEmpty()); // Elle ne devrait plus être vide
        assertEquals("aaa",list.first()); // L'élément ajouté est le first
        assertEquals("aaa",list.last()); // L'élément ajouté est aussi le last
        assertEquals("aaa",list.pop()); // L'élément ajouté est celui qu'on récupère
        assertTrue(list.isEmpty()); // Elle devrait être vide puiqueon a retiré le seul élement
    }
    
    
    /* 
     * Test très basique, sert surtout a vérifier first/last
     */
    @Test 
    public void basic() {
        final String stored="Something";
        assertTrue(cl.isEmpty());
        cl.append(stored);
        assertTrue(stored==cl.first());
        assertTrue(stored==cl.last());
        assertFalse(cl.isEmpty());
        var retrieved=cl.pop();
        assertTrue(stored==retrieved); // works because it is the same object all along.
        assertEquals(stored,retrieved); 
        assertTrue(cl.isEmpty());
    }
    
    /*
     * Tests push et append, on vérifie les invariants après chaque opération
     */
    @Test
    public void pushEasy() {
        // Push and check
        cl.push(testItems[0]);
        checkSame(0,cl.first());
        checkSame(0,cl.last());
        cl.push(testItems[1]);
        checkSame(1,cl.first());
        checkSame(0,cl.last());
        cl.push(testItems[2]);
        checkSame(2,cl.first());
        checkSame(0,cl.last());
        
        // Pop and check
        checkSame(2,cl.pop());
        checkSame(1,cl.first());
        checkSame(0,cl.last());
        checkSame(1,cl.pop());
        checkSame(0,cl.first());
        checkSame(0,cl.last());
        checkSame(0,cl.pop());
        
        // Oublié personne?
        assertTrue(cl.isEmpty());
    }

    @Test
    public void appendEasy() {
        // Append and check
        cl.append(testItems[0]);
        checkSame(0,cl.first());
        checkSame(0,cl.last());
        cl.append(testItems[1]);
        checkSame(0,cl.first());
        checkSame(1,cl.last());
        cl.append(testItems[2]);
        checkSame(0,cl.first());
        checkSame(2,cl.last());

        // Pop and check
        checkSame(0,cl.pop());
        checkSame(1,cl.first());
        checkSame(2,cl.last());
        checkSame(1,cl.pop());
        checkSame(2,cl.first());
        checkSame(2,cl.last());
        checkSame(2,cl.pop());
        
        // Oublié personne?
        assertTrue(cl.isEmpty());
    }

    /*
     * On verifie que pop() laisse la liste dans éta compatible
     * avec des push/append ultérieurs
     */
    @Test
    public void appendHard() {
        pushNFillers(3);
        appendNElements(3);
        popN(3);
        for (int i=0;i<3;i++) {
            checkSame(i,cl.pop());
        }
        assertTrue(cl.isEmpty());
    }
    
    @Test
    public void pushHard() {
        pushNElements(6);
        popN(3);
        appendNFillers(3);
        for (int i=2;i>=0;i--) {
            checkSame(i,cl.pop());
        }
        assertFalse(cl.isEmpty());
        popN(3);
        assertTrue(cl.isEmpty());
    }
    
    /*
     * Test de size
     */
    @Test void size() {
        pushNElements(6);
        assertEquals(cl.size(),6);
        popN(3);
        assertEquals(cl.size(),3);
        popN(3);
        assertEquals(cl.size(),0);
        assertTrue(cl.isEmpty());
        checkPopExc(cl::pop);
    }

    /*
     * Test de get()
     * - pas d'index négatif
     * - liste vide
     * - petits index qui peuvent avoir un traitement spécial
     * - général
     * - on vérifie qu'on  ne peut pas obtenir l'élement n+1
     * - on vérifie qu'on  ne peut pas obtenir l'élement n+2 (par ex, un test == qui aurait du être >=)
     */
    @Test void getEmpty() {
        // Ce teste marche sans daoute oar ce que de toute facon la liste est vide
        checkGetExc(()->cl.get(-1));
        checkGetExc(()->cl.get(-1));
        checkGetExc(()->cl.get(-1));
    }
    
    @Test void getNegative() {
        // Ce teste marche sans daoute oar ce que de toute facon la liste est vide
        checkGetExc(()->cl.get(-1));
        appendNFillers(1); // On ajoute qqchose
        // Est-ce qu'il y a vraiment un test sur le signe?
        checkGetExc(()->cl.get(-1));
    }
    
    @Test void get1() {
        appendNElements(1);
        assertEquals(cl.size(),1); // Ceinture et bretelles
        checkSame(0,cl.get(0));
        checkGetExc(()->cl.get(1));
        checkGetExc(()->cl.get(2));
    }

    @Test void get2() {
        appendNElements(2);
        assertEquals(cl.size(),2); // Ceinture et bretelles
        checkSame(0,cl.get(0));
        checkSame(1,cl.get(1));
        checkGetExc(()->cl.get(2));
        checkGetExc(()->cl.get(3));
    }
    
    @Test void getBig() {
        checkGetExc(()->cl.get(-1));
        checkGetExc(()->cl.get(0));
        appendNElements(6);
        assertEquals(cl.size(),6); // Ceinture et bretelles
        checkSame(0,cl.get(0));
        checkSame(3,cl.get(3));
        checkSame(5,cl.get(5));
        checkGetExc(()->cl.get(6));
        checkGetExc(()->cl.get(9));
    }
}
