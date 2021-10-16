package Tree;

import java.util.LinkedList;

public class KD_Coordinate {
  private double[] coords_;

  public KD_Coordinate (double[] coords){
    coords_ = coords;
  }

  public double getCoord(int index){
    return coords_[index];
  }

  public double[] getAllCoords() {
    return coords_;
  }

//  public Object getId() {
//    return null;
//  }
//
//  public List<Double> getCoordinates() {
//    return null;
//  }
}
