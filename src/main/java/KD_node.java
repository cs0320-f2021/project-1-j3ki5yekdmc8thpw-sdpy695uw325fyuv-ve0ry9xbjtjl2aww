import org.eclipse.jetty.xml.XmlParser;

public class KD_node {
  private double[] coords_;
  private KD_node left_ = null;
  private KD_node right_ = null;

  public KD_node(double[] coords) {
    coords_ = coords;
  }

//  public KD_node(double x, double y) {
//    this(new double[]{x, y});
//  }
//
//  public KD_node(double x, double y, double z) {
//    this(new double[]{x, y, z});
//  }

  //returns a single coordinate
  public double getCoord(int index) {
    return coords_[index];
  }

  //returns the entire coordinate set
  public double[] getAllCoords() {
    return coords_;
  }

  //computes and returns euclidean distance from self to another node
  private double distance(KD_node node) {
    double dist = 0;
    for (int i = 0; i < coords_.length; i++) {
      double d = coords_[i] - node.getCoord(i);
      dist += d * d;
    }
    return Math.sqrt(dist);
  }

  public KD_node getLeft(){
    return left_;
  }

  public KD_node getRight(){
    return right_;
  }

  public void setLeft(KD_node n){
    left_ = n;
  }

  public void setRight(KD_node n){
    right_ = n;
  }

  public void setCoord(int index, double d){
    coords_[index] = d;
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

