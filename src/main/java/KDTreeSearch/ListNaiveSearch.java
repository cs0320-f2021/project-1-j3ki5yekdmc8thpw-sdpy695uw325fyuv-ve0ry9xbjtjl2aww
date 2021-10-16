package KDTreeSearch;

import Tree.KeyDistance;
import Tree.KeyDistanceList;
import Utils.Utils;
import org.checkerframework.checker.units.qual.K;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class ListNaiveSearch<T> extends KeyDistanceList<T> {
  /**
   * Construct a KeyDistanceList from a List of KeyDistance.
   *
   * @param l a list of KeyDistance to be converted to KeyDistanceList.
   */
  public ListNaiveSearch(List<KeyDistance<T>> l) {
    super(new ArrayList<>(l));
  }

  /**
   * adds all coordinates with common key distances from the target coordinate
   * @param k number of coordinates we want
   * @param commonDistances an array of coordinates with same key distances
   * @param filledK number of coordinates that have been added to the list
   * @param nearestCoords an array of IDs
   * @return the number of filled positions (max = k)
   */
  int addCommonDistances(int k, List<KeyDistance<T>> commonDistances, int filledK, List<T> nearestCoords) {
    List<KeyDistance<T>> shuffleDistances = Utils.shuffleList(commonDistances);
    int index = 0;
    while (index < shuffleDistances.size() && filledK < k) {
      nearestCoords.add(shuffleDistances.get(index).getKey());
      filledK++;
      index++;
    }
    return filledK;
  }

  public List<T> naiveNearestNeighbors(int k) {
    List<KeyDistance<T>> keyDistances = super.getL();
    List<T> nearestCoords = new ArrayList<>();
    List<KeyDistance<T>> commonDistances = new ArrayList<>();
    double prevDist = 0;
    int index = 0;
    int filledK = 0;
    try {
      while (filledK < k) {
        if (keyDistances.get(index).getDistance() == prevDist) {
          commonDistances.add(keyDistances.get(index));
        } else {
          if (commonDistances.size() == 1) {
            nearestCoords.add(commonDistances.get(0).getKey());
            filledK++;
          } else {
            filledK = addCommonDistances(k, commonDistances, filledK, nearestCoords);
          }
          commonDistances = new ArrayList<>();
          commonDistances.add(keyDistances.get(index));
          prevDist = keyDistances.get(index).getDistance();
        }
        index++;
        if (index == keyDistances.size() && commonDistances.size() > 0) {
          filledK = addCommonDistances(k, commonDistances, filledK, nearestCoords);
        }
      }
      return nearestCoords;
    } catch (IndexOutOfBoundsException e) {
      return nearestCoords;
    }
  }
}

