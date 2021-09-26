
import static org.junit.Assert.assertEquals;

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



}