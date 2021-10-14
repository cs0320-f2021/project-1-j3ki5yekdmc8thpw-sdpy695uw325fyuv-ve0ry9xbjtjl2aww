import Tree.KD_node;
import org.w3c.dom.Node;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

//takes the index of one coordinate and compares two nodes based on that value
public class NodeComparator implements Comparator<Node> {
  private int index_;

  public NodeComparator(int index) {
    index_ = index;
  }

  /* takes two kd nodes and compares their given indexes.
   * returns:
   *   0: if d1 is numerically equal to d2.
   *   Negative value: if d1 is numerically less than d2.
   *   Positive value: if d1 is numerically greater than d2.
   */
  public int compare(KD_node n1, KD_node n2) {
    return Double.compare(n1.getCoord(index_), n2.getCoord(index_));
  }

  @Override
  public int compare(Node o1, Node o2) {
    return 0;
  }

  @Override
  public Comparator<Node> reversed() {
    return Comparator.super.reversed();
  }

  @Override
  public Comparator<Node> thenComparing(Comparator<? super Node> other) {
    return Comparator.super.thenComparing(other);
  }

  @Override
  public <U> Comparator<Node> thenComparing(Function<? super Node, ? extends U> keyExtractor,
                                            Comparator<? super U> keyComparator) {
    return Comparator.super.thenComparing(keyExtractor, keyComparator);
  }

  @Override
  public <U extends Comparable<? super U>> Comparator<Node> thenComparing(
      Function<? super Node, ? extends U> keyExtractor) {
    return Comparator.super.thenComparing(keyExtractor);
  }

  @Override
  public Comparator<Node> thenComparingInt(ToIntFunction<? super Node> keyExtractor) {
    return Comparator.super.thenComparingInt(keyExtractor);
  }

  @Override
  public Comparator<Node> thenComparingLong(ToLongFunction<? super Node> keyExtractor) {
    return Comparator.super.thenComparingLong(keyExtractor);
  }

  @Override
  public Comparator<Node> thenComparingDouble(ToDoubleFunction<? super Node> keyExtractor) {
    return Comparator.super.thenComparingDouble(keyExtractor);
  }
}