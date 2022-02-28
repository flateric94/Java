package tp4;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import tp4.CircularList;


public class CircularListTestBase {
    
    static final protected Class<? extends Exception> GET_EXCEPTION=NoSuchElementException.class; 
    static final protected Class<? extends Exception> POP_EXCEPTION=EmptyStackException.class;
    static final protected Class<? extends Exception> REMOVE_EXCEPTION=IllegalStateException.class;
	
    
    static int MAX_TEST_ITEMS=10;
    static String[] testItems=new String[MAX_TEST_ITEMS];
    static {
        for (int i=0;i<MAX_TEST_ITEMS;i++) {
            testItems[i]=String.format("Element %03d",i);
        }
    }
    
    /**
     * Quelques méthodes pour se faciliter la vie et éviter de trop se répéter dans les tests
     */
    
    CircularList<String> cl; // on adapte
    
    @BeforeEach
    public void initEach() {
        cl=new CircularList();
    }
    
    /* 
     * Append N éléments numérotés
     */
    protected void appendNElements(int n) {
        for (int i=0; i<n;i++) {
            cl.append(testItems[i]);
        }
    }
    
    /* 
     * Push N éléments numérotés
     */
    protected void pushNElements(int n) {
        for (int i=0; i<n;i++) {
            cl.push(testItems[i]);
        }
    }
    
    /*
     * Push N cochonneries
     */
    protected void pushNFillers(int n) {
        for (int i=0; i<n;i++) {
            cl.push("#*#*#*#*#*#*#*#");
        }
    }

    /*
     * append N cochonneries
     */
    protected void appendNFillers(int n) {
        for (int i=0; i<n;i++) {
            cl.append("#*#*#*#*#*#*#*#");
        }
    }

    /*
     * Pop et oublie N elements 
     */
    protected void popN(int n) {
        for (int i=0; i<n;i++) {
            cl.pop();
        }
    }

    protected void checkSame(int i,String s) {
        assertEquals(testItems[i],s);
    }
    
    protected void checkPopExc(Executable e) {
        assertThrows(POP_EXCEPTION,e);
    }
    
    protected void checkGetExc(Executable e) {
        assertThrows(GET_EXCEPTION,e);
    }
    
    // ajout de CheckNext, checkRemoveExc et checkSortExc
    protected void checkNext(Executable e) {
        assertThrows(GET_EXCEPTION,e);
    }    

    protected void checkRemoveExc(Executable e) {
        assertThrows(REMOVE_EXCEPTION,e);
    }
    
    protected void checkSortExc(Executable e) {
        assertThrows(REMOVE_EXCEPTION,e);
    }    
    
}
