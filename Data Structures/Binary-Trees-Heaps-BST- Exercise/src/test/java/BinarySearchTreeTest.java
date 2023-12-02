import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest extends TestCase {

    private BinarySearchTree<Integer> tree;
    @Before
    public void setUp() {
        tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(2);
        tree.insert(4);
        tree.insert(1);
        tree.insert(15);
    }

    @Test
    public void testFloor() {
        Integer num = tree.floor(2);
        assertEquals(1, num.intValue());
    }

}