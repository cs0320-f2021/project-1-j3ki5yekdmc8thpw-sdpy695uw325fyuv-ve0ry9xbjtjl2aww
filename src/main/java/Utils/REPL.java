package Utils;

import KDTreeSearch.KDTreeSearch;
import Tree.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class REPL {
  public void run() throws IOException {
    KDTreeSearch search = new KDTreeSearch();
    UserHandler user = new UserHandler();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");

          // allow user command
          switch (arguments[0]) {

            case "similar":
              if (Integer.parseInt(arguments[1]) <= 0) {
                throw new Exception("ERROR: k must be greater than zero");
              }
              if (arguments.length < 3) {
                throw new Exception("ERROR: must provide user data");
              } else if (arguments.length == 3) {
/**
 * TODO: need to initiate KD Tree in Main, then use it to call the root node within the search algorithm
 * TODO: convert the result into user IDs
 * fill in ROOTNODE so the search algorithm can be initiated
 */
                List<Coordinate<double[]>> results = search.nearestNeighbors(arguments[1],
                    user.idToCoord(Integer.parseInt(arguments[2])), ROOTNODE, true);
                // covert each result to id, then print the user IDs (use getUser?)
                System.out.println(results);
              } else if (arguments.length == 5) {
                List<Coordinate<double[]>> results = search.nearestNeighbors(arguments[1],
                    user.toCoord(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]),
                        Integer.parseInt(arguments[4])), ROOTNODE, true);
                // covert each result to id, then print the user IDs (use getUser?)
                System.out.println(results);
              } else {
                throw new Exception("ERROR: invalid command input");
              }
              break;
            default:
              System.out.println(arguments[0]);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }


}
