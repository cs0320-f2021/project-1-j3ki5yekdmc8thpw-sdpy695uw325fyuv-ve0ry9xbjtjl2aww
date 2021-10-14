package Tree;

import java.util.Objects;

/**
 * Class to represent nodes in the KD tree
 * @param <T> a type T for the value held in the node
 */
public class KD_node<T> {
  private final T value;
  private final KD_node<T> left_;
  private final KD_node<T> right_;

  /**
   * Constructor for Tree.KD_node.
   * @param val: type of value stored in kd_node
   * @param l: left child
   * @param r: right child
   */
  public KD_node (T val, KD_node<T> l, KD_node<T> r) {
    value = val;
    left_ = l;
    right_ = r;
  }

  //compares one node with any other object. returns true if the same node
  //or if node with same value and same children
  public boolean equals(Object o){
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()){
      return false;
    }
    KD_node<?> node = (KD_node<?>) o;
    return Objects.equals(value, node.getValue())
        && Objects.equals(left_, node.getLeft())
        && Objects.equals(right_, node.getRight());
  }

  /** Get a hashcode for a Node.
   @return an int representing the hash index.
   */
  @Override
  public int hashCode() {
    return Objects.hash(value, left_, right_);
  }
//
///* takes in an index of specific coordinate, returns that single coordinate
//    ie: parameter: 0 --> x-coordinate of node
// */
//  public double getCoord(int index) {
//    return coords_[index];
//  }
//
//  //returns the entire coordinate set
//  public double[] getAllCoords() {
//    return coords_;
//  }
//
//  //computes and returns euclidean distance from self to another node
//  private double distance(KD_node<T> node) {
//    double dist = 0;
//    for (int i = 0; i < coords_.length; i++) {
//      double d = coords_[i] - node.getCoord(i);
//      dist += d * d;
//    }
//    return Math.sqrt(dist);
//  }

  //returns left child
  public KD_node<T> getLeft(){
    return left_;
  }

  //returns right child
  public KD_node<T> getRight(){
    return right_;
  }


//
//  //updates single coordinate
//  public void setCoord(int index, double d){
//    coords_[index] = d;
//  }

  //returns type of value stored in node
  private T getValue(){
    return value;
  }

//
//  public String toString() {
//    StringBuilder s = new StringBuilder("(");
//    for (int i = 0; i < coords_.length; ++i) {
//      if (i > 0)
//        s.append(", ");
//      s.append(coords_[i]);
//    }
//    s.append(')');
//    return s.toString();
//  }
}

