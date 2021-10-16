package KDTreeSearch;

import Tree.Coordinate;
import Tree.KD_node;
import Tree.KeyDistance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KDTreeSearch<T> {
  /**
   * Traverse the KD Tree recursively on right and/or left children to store k nearest coordinates
   * into the priority queue
   * @param rootNode root node of the KD Tree
   * @param k number of coordinates we want to find
   * @param targetCoord the coordinate of the target user
   * @param kNearestNeighbors priority queue of k coordinates in order of the closest relative
   *                               distance from the target coordinate
   * @param knnReverse priority queue of coordinates in reverse order of kNearestNeighbors
   * @param dim dimension of the given node (will always start at node = 1)
   */
  void performKDTreeSearchNeighbors(KD_node<Coordinate<T>> rootNode, int k, Coordinate<T> targetCoord,
                                    PriorityQueue<KeyDistance<Coordinate<T>>> kNearestNeighbors,
                                    PriorityQueue<KeyDistance<Coordinate<T>>> knnReverse,
                                    int dim) {
    if (!(rootNode.getValue() == null || k == 0)) {
      double currentNodeDistSqr = 0;
      double currentNodeDist;
      // keeps track of the dimension
      int index = 1;
      try {
        while (true) {
          currentNodeDistSqr += Math.pow(rootNode.getValue().getCoordinateVal(index) -
              targetCoord.getCoordinateVal(index), 2);
          index++;
        }
        // catches index out of bounds when traversal reaches a leaf or when k = 0
      } catch (IndexOutOfBoundsException e) {
        currentNodeDist = Math.sqrt(currentNodeDistSqr);
      }
      Coordinate<T> rootCoord = rootNode.getValue();
      KeyDistance<Coordinate<T>> rootDist = new KeyDistance<>(rootCoord, currentNodeDist);
      if (kNearestNeighbors.size() < k || currentNodeDist <= knnReverse.peek().getDistance()) {
        kNearestNeighbors.add(rootDist);
        knnReverse.add(rootDist);
      }
      int nextDim;
      if (dim + 1 > targetCoord.getCoordinates().size()) {
        nextDim = 1;
      } else {
        nextDim = dim + 1;
      }
      // if kNearestNeighbors is not filled, perform current method recursively on left and right node
      if (kNearestNeighbors.size() < k) {
        // recursion occurs here for left node
        performKDTreeSearchNeighbors(rootNode.getLeft(), k, targetCoord, kNearestNeighbors,
            knnReverse, nextDim);
        // recursion occurs here for right node
        performKDTreeSearchNeighbors(rootNode.getRight(), k, targetCoord, kNearestNeighbors,
            knnReverse, nextDim);
      } else {
        // if it has been filled, calculate the euclidean and axis distance to decide
        // which node to recur on
        double maxEuclideanDist = knnReverse.peek().getDistance();
        double axisDist = Math.abs(targetCoord.getCoordinateVal(dim) -
            rootNode.getValue().getCoordinateVal(dim));
        // if the euclidean distance is greater than the axis distance, perform current method
        // recursively on left and right node
        if (maxEuclideanDist >= axisDist) {
          // recursion occurs here for left node
          performKDTreeSearchNeighbors(rootNode.getLeft(), k, targetCoord, kNearestNeighbors,
              knnReverse, nextDim);
          // recursion occurs here for right node
          performKDTreeSearchNeighbors(rootNode.getRight(), k, targetCoord, kNearestNeighbors,
              knnReverse, nextDim);
        } else {
          // compare the coordinate values of the given axis; if the current coordinate is less than
          // the target coordinate, perform current method recursively only on the right node
          if (rootNode.getValue().getCoordinateVal(dim) < targetCoord.getCoordinateVal(dim)) {
            performKDTreeSearchNeighbors(rootNode.getRight(), k, targetCoord, kNearestNeighbors,
                knnReverse, nextDim);
          }
          // compare the coordinate values of the given axis; if the current coordinate is greater than
          // the target coordinate, perform current method recursively only on the left node
          else if (rootNode.getValue().getCoordinateVal(dim) > targetCoord.getCoordinateVal(dim)) {
            performKDTreeSearchNeighbors(rootNode.getLeft(), k, targetCoord, kNearestNeighbors,
                knnReverse, nextDim);
          }
        }
      }
    }
  }
  /**
   * Method that finds k coordinates that are closest in distance of the given target coordinate
   * @param k number of coordinates we want to find
   * @param targetCoord coordinate of the target user
   * @param rootNode root node of the KD Tree
   * @param excludeTarget boolean defining exclusion of target coordinate
   * @return list of coordinates that are the nearest neighbors of the target coordinate
   */
  public List<Coordinate<T>> nearestNeighbors(int k, Coordinate<T> targetCoord,
                                              KD_node<Coordinate<T>> rootNode,
                                              Boolean excludeTarget) {
    // target coordinate will be excluded
    if (excludeTarget) {
      k = k + 1;
    }
    Comparator<KeyDistance<Coordinate<T>>> byDistance = Comparator.comparing(KeyDistance::getDistance);
    Comparator<KeyDistance<Coordinate<T>>> byReverseDistance =
        Comparator.comparing(keyDist -> -1 * keyDist.getDistance());
    // create a priority queue by relative distance from the targetCoord
    PriorityQueue<KeyDistance<Coordinate<T>>> kNearestNeighborsQueue = new PriorityQueue<>(byDistance);
    // create a priority queue in reverse order to track the maximum distance
    PriorityQueue<KeyDistance<Coordinate<T>>> knnReverseQueue = new PriorityQueue<>(byReverseDistance);

    performKDTreeSearchNeighbors(rootNode, k, targetCoord, kNearestNeighborsQueue,
        knnReverseQueue, 1);
    List<KeyDistance<Coordinate<T>>> kNearestNeighborsList = new ArrayList<>();
    while (!kNearestNeighborsQueue.isEmpty()) {
      KeyDistance<Coordinate<T>> keyDist = kNearestNeighborsQueue.remove();
      // exclude the target coordinate
      if (excludeTarget) {
        if (!(keyDist.getKey().getId().equals(targetCoord.getId()))) {
          kNearestNeighborsList.add(keyDist);
        }
      } else {
        kNearestNeighborsList.add(keyDist);
      }
    }
    ListNaiveSearch<Coordinate<T>> knn = new ListNaiveSearch<>(kNearestNeighborsList);
    if (excludeTarget) {
      return knn.naiveNearestNeighbors(k - 1);
    } else {
      return knn.naiveNearestNeighbors(k);
    }
  }
}
