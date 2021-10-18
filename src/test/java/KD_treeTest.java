
import KDTreeSearch.KDTreeSearch;
import Tree.KD_Coordinate;
import Tree.KD_node;
import Tree.KD_tree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KD_treeTest {

  @Test
  public void testCreateKdTree() {
    // creating coordinates
    double[] data1 = new double[3];
    data1[0] = 3.0;
    data1[1] = 1.0;
    data1[2] = 4.0;
    KD_Coordinate c1 = new KD_Coordinate(data1);

    double[] data2 = new double[3];
    data2[0] = 2.0;
    data2[1] = 3.0;
    data2[2] = 7.0;
    KD_Coordinate c2 = new KD_Coordinate(data2);

    double[] data3 = new double[3];
    data3[0] = 4.0;
    data3[1] = 3.0;
    data3[2] = 4.0;
    KD_Coordinate c3 = new KD_Coordinate(data3);

    double[] data4 = new double[3];
    data4[0] = 2.0;
    data4[1] = 1.0;
    data4[2] = 3.0;
    KD_Coordinate c4 = new KD_Coordinate(data4);

    // creating list of coordinates
    List<KD_Coordinate> allCoords = new ArrayList<>();
    allCoords.add(c1);
    allCoords.add(c2);
    allCoords.add(c3);
    allCoords.add(c4);

    // loading tree
    KD_tree<KD_node<KD_Coordinate>> tree = new KD_tree<KD_node<KD_Coordinate>>(3, allCoords);

    KD_node<KD_Coordinate> root = tree.getRoot();

    assertEquals(root.getValue().getAllCoords()[0], 3, 0);
    assertEquals(root.getValue().getAllCoords()[1], 1, 0);
    assertEquals(root.getValue().getAllCoords()[2], 4, 0);

    assertEquals(root.getLeft().getValue().getAllCoords()[0], 2, 0);
    assertEquals(root.getLeft().getValue().getAllCoords()[1], 3, 0);
    assertEquals(root.getRight().getValue().getAllCoords()[0], 4, 0);
    assertEquals(root.getRight().getValue().getAllCoords()[1], 3, 0);

    assertEquals(root.getLeft().getLeft().getValue().getAllCoords()[0], 2, 0);

    assertNull(root.getRight().getRight().getValue());
  }

  @Test
  public void testEquals() {
    // creating coordinates

    double[] data1 = new double[3];
    data1[0] = 10.0;
    data1[1] = 0.0;
    data1[2] = 0.0;
    KD_Coordinate c1 = new KD_Coordinate(data1);

    double[] data2 = new double[3];
    data2[0] = 0.0;
    data2[1] = 5.0;
    data2[2] = 8.0;
    KD_Coordinate c2 = new KD_Coordinate(data2);

    double[] data3 = new double[3];
    data3[0] = 0.0;
    data3[1] = 0.0;
    data3[2] = 4.0;
    KD_Coordinate c3 = new KD_Coordinate(data3);

    double[] data4 = new double[3];
    data4[0] = 10.0;
    data4[1] = 10.0;
    data4[2] = 0.0;
    KD_Coordinate c4 = new KD_Coordinate(data4);


    // creating list of coordinates
    List<KD_Coordinate> allCoords = new ArrayList<>();
    allCoords.add(c1);
    allCoords.add(c2);
    allCoords.add(c3);
    allCoords.add(c4);

    // loading tree
    KD_tree tree1 = new KD_tree(3, allCoords);
    KD_tree tree2 = new KD_tree(3, allCoords);
    KD_tree tree3 = new KD_tree(1, allCoords);

    assertEquals(tree1, tree2);
    assertEquals(tree1, tree1);
    assertNotEquals(tree1, tree3);
  }


}
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//import Tree.KD_node;
//import org.junit.Test;
//
//import java.io.File;
//
//public class KD_treeTest {
//
//  @Test
//  public void testSimpleTree() {
//    File file = new File("data/project-1/basickdtreetest.json");
//    KD_tree tree = new KD_tree(3, file);
//    KD_node root = tree.getRoot_();
//    assertEquals(100, root.getCoord(0), 0.01);
//    assertEquals(72, root.getCoord(1), 0.01);
//    assertEquals(90, root.getCoord(2), 0.01);
//  }
//
//  @Test
//  public void testBigTree() {
//    File file = new File("data/project-1/bigKDTreeTest.json");
//    KD_tree tree = new KD_tree(3, file);
//    KD_node root = tree.getRoot_();
//    System.out.println(root.getCoord(0));
//    System.out.println(root.getLeft().getCoord(0));
//    System.out.println(root.getRight().getCoord(0));
//    System.out.println(root.getLeft().getLeft().getCoord(0));
//    System.out.println(root.getLeft().getRight().getCoord(0));
//    System.out.println(root.getRight().getLeft().getCoord(0));
//
//    assertEquals(2, root.getCoord(0), 0.01);
//    assertEquals(61, root.getCoord(1), 0.01);
//    assertEquals(40, root.getCoord(2), 0.01);
//    assertEquals(2, root.getLeft().getCoord(0), 0.01);
//    assertEquals(63, root.getLeft().getCoord(1), 0.01);
//    assertEquals(70, root.getLeft().getCoord(3), 0.01);
//    assertEquals(6, root.getRight().getLeft().getCoord(0), 0.01);
//    assertEquals(64, root.getRight().getLeft().getCoord(1), 0.01);
//  }
//
//  @Test
//  public void testNegTree() {
//    File file = new File("data/project-1/negkdtreetest.json");
//    KD_tree tree = new KD_tree(3, file);
//    KD_node root = tree.getRoot_();
//    assertEquals(-200, root.getCoord(0), 0.01);
//    assertEquals(6, root.getCoord(1), 0.01);
//    assertEquals(-90, root.getCoord(2), 0.01);
//    assertEquals(-1000, root.getLeft().getCoord(0), 0.01);
//    assertEquals(-100, root.getRight().getCoord(0), 0.01);
//  }
//
//  @Test
//  public void testErrorTree() {
//    File file = new File("data/project-1/errorTree.json");
//    assertThrows(NullPointerException.class, () -> new KD_tree(3, file));
//  }

//}