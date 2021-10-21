package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KD_Coordinate<T extends Comparable<T>> {
  private final List<T> coords_;

  public KD_Coordinate (List<T> coords){
    coords_ = coords;
  }

  public T getCoord(int index){
    return coords_.get(index);
  }

  public List<T> getAllCoords() {

    return new ArrayList<T>(coords_);
  }

//  public Object getId() {
//    return null;
//  }
//
//  public List<Double> getCoordinates() {
//    return null;
//  }
}
