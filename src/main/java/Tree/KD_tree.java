package Tree;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Class to create a Tree.KdTree of specified type ID.
 @param <T> Any type for the ID of the Coordinates that is specified
 when being used to construct a Tree.KdTree.
 */
public class KD_tree<T extends Comparable<T>> {
  private final int dimensions;
  private final KD_node<KD_Coordinate<T>> root;

  /** Set the Tree.KdTree to have the passed coordinates with the specified dimensions each.
   @param dimensions the dimension number, from 1 to n where n is a positive integer.
   @param coordinates a list of Coordinates of any identifier/id type
   */
  public KD_tree(int dimensions, List<KD_Coordinate<T>> coordinates) {
    this.dimensions = dimensions;
    this.root = createKdTree(new ArrayList<>(coordinates));
  }

  /** Create a KDTree as a series of binary connected nodes with appropriate
   number of cutting layers.
   @param coordinates a list of Coordinates of any identifier/id type
   @return a Node of same type as passed when constructing the Tree.KdTree.
   */
  public KD_node<KD_Coordinate<T>> createKdTree(List<KD_Coordinate<T>> coordinates) {
    return createNextLayer(1, coordinates);
  }

  /** Create a KDTree layer at the passed cutting dimension out of the passed coordinates.
   @param currentDim the current cutting dimension.
   @param remainingCoordinates the coordinates which should be inserted as order specifies
   within this sub-KDTree.
   @return a Node of same type as passed when constructing the Tree.KdTree.
   */
  private KD_node<KD_Coordinate<T>> createNextLayer(int currentDim,
                                              List<KD_Coordinate<T>> remainingCoordinates) {
    if (remainingCoordinates.size() == 0) {
      return new KD_node<>(null, null, null);
    } else {

      Comparator<KD_Coordinate<T>> byDimension
          //=  remainingCoordinates().getCoord(currentDim)
          = Comparator.comparing((coordinate1, coordinate2) ->
          coordinate1.getCoord(currentDim).compareTo(coordinate2.getCoord(currentDim)));
          //((coordinate -> coordinate.getCoord(currentDim));

      remainingCoordinates.sort(byDimension);

      int middleIndex = remainingCoordinates.size() / 2;

      // find median coordinate, coordinates lesser, coordinates greater than median
      KD_Coordinate medianCoordinate = remainingCoordinates.get(middleIndex);
      List<List<KD_Coordinate>> splitResult
          = Utils.Utils.splitList(remainingCoordinates, middleIndex);

      List<KD_Coordinate> lesserCoordinates = splitResult.get(0);
      List<KD_Coordinate> greaterCoordinates = splitResult.get(1);

      // calculate next dimension
      int nextDimension;
      if (currentDim + 1 > dimensions) {
        nextDimension = 1;
      } else {
        nextDimension = currentDim + 1;
      }

      return new KD_node<>(
          // value
          medianCoordinate,
          // recursive call to fill left subtree
          createNextLayer(nextDimension, lesserCoordinates),
          // recursive call to fill right subtree
          createNextLayer(nextDimension, greaterCoordinates));
    }
  }

  /** Get the root of the Tree.KdTree created.
   @return a Node with value being a Coordinate of the specified ID type.
   */
  public KD_node<KD_Coordinate<T>> getRoot() {
    return root;
  }

  /** Represent the Tree.KdTree as a String.
   @return a String representation of a Tree.KdTree.
   */
  @Override
  public String toString() {
    return "Tree.KdTree{"
        + "dimensions=" + dimensions
        + ", tree=" + root.toString()
        + '}';
  }

  /** Check if this Tree.KdTree is equal to the passed object.
   @param o Another object
   @return a Boolean ture/false if the objects are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KD_tree<?> kdTree = (KD_tree<?>) o;
    return dimensions == kdTree.dimensions && Objects.equals(root, kdTree.root);
  }

  /** Get a hashcode for a Tree.KdTree.
   @return an int representing the hash index.
   */
  @Override
  public int hashCode() {
    return Objects.hash(dimensions, root);
  }
}
