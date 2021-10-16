//import SearchAlgorithm.KdTreeSearch;
//import Tree.Coordinate;
//import Tree.KD_Coordinate;
//import Tree.KD_node;
//import Tree.KD_tree;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//
//public class KdTreeSearchTest {
//  double[] data1 = new double[3];
//  double[] data2 = new double[3];
//  double[] data3 = new double[3];
//  double[] data4 = new double[3];
//
//
//  Coordinate<double[]> c1;
//  KD_Coordinate c2;
//  KD_Coordinate c3;
//  KD_Coordinate c4;
//
//
//  List<KD_Coordinate> allCoords = new ArrayList<>();
//
//  @Before
//  public void setUp() {
//    data1[0] = 3.0;
//    data1[1] = 1.0;
//    data1[2] = 4.0;
//    c1 = new Coordinate<double[]>(data1);
//
//    data2[0] = 2.0;
//    data2[1] = 3.0;
//    data2[2] = 7.0;
//    c2 = new KD_Coordinate(data2);
//
//    data3[0] = 4.0;
//    data3[1] = 3.0;
//    data3[2] = 4.0;
//    c3 = new KD_Coordinate(data3);
//
//    data4[0] = 2.0;
//    data4[1] = 1.0;
//    data4[2] = 3.0;
//    c4 = new KD_Coordinate(data4);
//
//    allCoords.add(c1);
//    allCoords.add(c2);
//    allCoords.add(c3);
//    allCoords.add(c4);
//  }
//
//  @Test
//  public void testPerformKdTreeSearchNeighbors_kGreaterThanSize() {
//    // load tree
//    KdTree<Integer> tree = new KdTree<>(2, allCoords);
//
//    Node<Coordinate<Integer>> root = tree.getRoot();
//
//    int k = 200;
//    Coordinate<Integer> targetPoint = c8;
//
//    // create comparators and priority queues
//    Comparator<KeyDistance<Coordinate<Integer>>> byDistance
//        = Comparator.comparing(KeyDistance::getDistance);
//    Comparator<KeyDistance<Coordinate<Integer>>> byReverseDistance
//        = Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
//
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsQueue
//        = new PriorityQueue<>(byDistance);
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsReverse
//        = new PriorityQueue<>(byReverseDistance);
//
//    KdTreeSearch<Integer> search = new KdTreeSearch<>();
//
//    search.performKdTreeSearchNeighbors(root, k, targetPoint,
//        kNearestNeighborsQueue, kNearestNeighborsReverse, 1);
//
//    // convert resultant priority queue to list for comparison against known
//    // correct list of indices
//    List<Integer> kNearestNeighborsIndices = new ArrayList<>();
//    while (!kNearestNeighborsQueue.isEmpty()) {
//      KeyDistance<Coordinate<Integer>> keyDist = kNearestNeighborsQueue.remove();
//      kNearestNeighborsIndices.add(keyDist.getKey().getId());
//    }
//
//    List<Integer> expectedIndices = new ArrayList<>();
//    expectedIndices.add(8);
//    expectedIndices.add(10);
//    expectedIndices.add(5);
//    expectedIndices.add(11);
//    expectedIndices.add(9);
//    expectedIndices.add(3);
//    expectedIndices.add(7);
//    expectedIndices.add(6);
//    expectedIndices.add(4);
//    expectedIndices.add(2);
//    expectedIndices.add(1);
//
//    assertEquals(kNearestNeighborsIndices, expectedIndices);
//  }
//
//  @Test
//  public void testPerformKdTreeSearchNeighbors_kLessThanSize() {
//    KdTree<Integer> tree = new KdTree<>(2, allCoords);
//
//    Node<Coordinate<Integer>> root = tree.getRoot();
//
//    int k = 3;
//    Coordinate<Integer> targetPoint = c8;
//
//    // create comparators and priority queues
//    Comparator<KeyDistance<Coordinate<Integer>>> byDistance
//        = Comparator.comparing(KeyDistance::getDistance);
//    Comparator<KeyDistance<Coordinate<Integer>>> byReverseDistance
//        = Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
//
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsQueue
//        = new PriorityQueue<>(byDistance);
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsReverse
//        = new PriorityQueue<>(byReverseDistance);
//
//    KdTreeSearch<Integer> search = new KdTreeSearch<>();
//
//    search.performKdTreeSearchNeighbors(root, k, targetPoint,
//        kNearestNeighborsQueue, kNearestNeighborsReverse, 1);
//
//    // convert resultant priority queue to list for comparison against known
//    // correct list of indices
//    List<Integer> kNearestNeighborsIndices = new ArrayList<>();
//    while (!kNearestNeighborsQueue.isEmpty()) {
//      KeyDistance<Coordinate<Integer>> keyDist = kNearestNeighborsQueue.remove();
//      kNearestNeighborsIndices.add(keyDist.getKey().getId());
//    }
//
//    // avoiding one of the subtrees
//    List<Integer> expectedIndices = new ArrayList<>();
//    expectedIndices.add(8);
//    expectedIndices.add(10);
//    expectedIndices.add(5);
//    expectedIndices.add(11);
//    expectedIndices.add(9);
//    expectedIndices.add(3);
//    expectedIndices.add(7);
//    expectedIndices.add(6);
//    expectedIndices.add(4);
//
//    assertEquals(kNearestNeighborsIndices, expectedIndices);
//  }
//
//  @Test
//  public void testPerformKdTreeSearchNeighbors_0K() {
//    KdTree<Integer> tree = new KdTree<>(2, allCoords);
//
//    Node<Coordinate<Integer>> root = tree.getRoot();
//
//    int k = 0;
//    Coordinate<Integer> targetPoint = c8;
//
//    // create comparators and priority queues
//    Comparator<KeyDistance<Coordinate<Integer>>> byDistance
//        = Comparator.comparing(KeyDistance::getDistance);
//    Comparator<KeyDistance<Coordinate<Integer>>> byReverseDistance
//        = Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
//
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsQueue
//        = new PriorityQueue<>(byDistance);
//    PriorityQueue<KeyDistance<Coordinate<Integer>>> kNearestNeighborsReverse
//        = new PriorityQueue<>(byReverseDistance);
//
//    KdTreeSearch<Integer> search = new KdTreeSearch<>();
//
//    search.performKdTreeSearchNeighbors(root, k, targetPoint,
//        kNearestNeighborsQueue, kNearestNeighborsReverse, 1);
//
//    // convert resultant priority queue to list for comparison against known
//    // correct list of indices
//    List<Integer> kNearestNeighborsIndices = new ArrayList<>();
//    while (!kNearestNeighborsQueue.isEmpty()) {
//      KeyDistance<Coordinate<Integer>> keyDist = kNearestNeighborsQueue.remove();
//      kNearestNeighborsIndices.add(keyDist.getKey().getId());
//    }
//
//    List<Integer> expectedIndices = new ArrayList<>();
//
//    assertEquals(kNearestNeighborsIndices, expectedIndices);
//  }
//
//  @Test
//  public void testNearestNeighbors() {
//    // loading tree
//    KD_tree<KD_node<KD_Coordinate>> tree = new KD_tree<>(3, allCoords);
//
//    KD_node<KD_Coordinate> root = tree.getRoot();
//
//    int k = 5;
//    KD_Coordinate targetPoint = c4;
//
//    KdTreeSearch<Integer> search = new KdTreeSearch<>();
//
//    // map over nearest stars to get nearest indices
//    List<Integer> kNearestNeighborIndices
//        = search.nearestNeighbors(k, targetPoint, root, true).stream().map(Coordinate::getId).collect
//        (Collectors.toList());
//
//    List<Integer> expectedIndices = new ArrayList<>();
//    expectedIndices.add(10);
//    expectedIndices.add(5);
//    expectedIndices.add(11);
//    expectedIndices.add(9);
//    expectedIndices.add(3);
//
//    assertEquals(kNearestNeighborIndices, expectedIndices);
//  }
//}
