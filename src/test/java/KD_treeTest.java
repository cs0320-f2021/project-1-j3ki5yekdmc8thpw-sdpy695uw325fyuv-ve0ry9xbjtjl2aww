
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.io.File;
import java.net.URL;

public class KD_treeTest {

  @Test
  public void testSimpleTree() {
    File file = new File("data/project-1/basickdtreetest.json");
    KD_tree tree = new KD_tree(3, file);
    KD_node root = tree.getRoot_();
    assertEquals(100, root.getCoord(0), 0.01);
    assertEquals(72, root.getCoord(1), 0.01);
    assertEquals(90, root.getCoord(2), 0.01);
  }

  @Test
  public void testBigTree() {
    File file = new File("data/project-1/bigKDTreeTest.json");
    KD_tree tree = new KD_tree(3, file);
    KD_node root = tree.getRoot_();
    System.out.println(root.getCoord(0));
    System.out.println(root.getLeft().getCoord(0));
    System.out.println(root.getRight().getCoord(0));
    System.out.println(root.getLeft().getLeft().getCoord(0));
    System.out.println(root.getLeft().getRight().getCoord(0));
    System.out.println(root.getRight().getLeft().getCoord(0));

    assertEquals(2, root.getCoord(0), 0.01);
    assertEquals(61, root.getCoord(1), 0.01);
    assertEquals(40, root.getCoord(2), 0.01);
    assertEquals(2, root.getLeft().getCoord(0), 0.01);
    assertEquals(63, root.getLeft().getCoord(1), 0.01);
    assertEquals(70, root.getLeft().getCoord(3), 0.01);
    assertEquals(6, root.getRight().getLeft().getCoord(0), 0.01);
    assertEquals(64, root.getRight().getLeft().getCoord(1), 0.01);
  }

  @Test
  public void testNegTree() {
    File file = new File("data/project-1/negkdtreetest.json");
    KD_tree tree = new KD_tree(3, file);
    KD_node root = tree.getRoot_();
    assertEquals(-200, root.getCoord(0), 0.01);
    assertEquals(6, root.getCoord(1), 0.01);
    assertEquals(-90, root.getCoord(2), 0.01);
    assertEquals(-1000, root.getLeft().getCoord(0), 0.01);
    assertEquals(-100, root.getRight().getCoord(0), 0.01);
  }

  @Test
  public void testErrorTree() {
    File file = new File("data/project-1/errorTree.json");
    assertThrows(NullPointerException.class, () -> new KD_tree(3, file));
  }

}