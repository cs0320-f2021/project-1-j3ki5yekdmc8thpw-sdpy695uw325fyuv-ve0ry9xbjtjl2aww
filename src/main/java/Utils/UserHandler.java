/**
 * User Handler class that finds nearest neighbors of given user ID or coordinate
 * replaced with KD Tree Search class
 */


//package src.main.java;
//package Utils;
//
//import Tree.Coordinate;
//import Tree.KD_Coordinate;
//import Tree.KD_node;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//
//public class UserHandler {
//  private static List<User> userData;
//  private List<List<String>> jsonParse = new ArrayList<>();
//
//  public UserHandler(List<User> userData) {
//    this.userData = userData;
//  }
//
//  /**
//   * TODO: Need to create a method that will parse json files and store data into userData
//   * @param filename - file path for the desired json file
//   */
//  //userData = this.LoadFile(new File());
//
//  /**
//   * TODO: Need to create userData before using this method
//   * Helper method that converts user coordinate to user IDs
//   * @param coord - the coordinate of the user that we want to find
//   * @return a user with an ID that matches input coordinate
//   * @throws IllegalArgumentException if the input coordinate does not exist in userData
//   */
//  public static User getUser(double[] coord) throws IllegalArgumentException {
//    for (User user: userData) {
//      if (user.getWeight() == coord[0] &&
//          user.getHeight() == coord[1] &&
//          user.getAge() == coord[2]) {
//        return user;
//      }
//    }
//    throw new IllegalArgumentException("ERROR: input coordinate does not exist in user data");
//  }
//
//
//  /**
//   * Method that returns nearestNeighbors
//   * @param input - String input that user provides
//   * @return list of users nearest the input user info
//   */
//  public static <T> List<User> nearestNeighbors(String input) {
//    String[] arguments = input.split(" ");
//    List<User> neighborUsers = new ArrayList<>();
//
//    //KD_node<Coordinate<T>> node = new KD_node(User, null, null);// initiate root of the KD tree here
//
//    // if user input is similar <k> <weight> <height> <age>,
//    // it uses the node coordinates to find the nearest neighbors
//    if (arguments.length == 5 && !input.contains("\"")) {
//      double[] targetPoint = toCoord(Double.parseDouble(arguments[2]), Double.parseDouble(arguments[3]),
//          Double.parseDouble(arguments[4]));
//      neighborUsers = findNearest1(Integer.parseInt(arguments[1]), node, targetPoint);
//    } else {
//      // if user input is similar <k> <user id>,
//      // it uses the user id to find the coordinate to find the nearest neighbors
//      String userId = arguments[2];
//      double[] targetPoint = idToCoord(Integer.parseInt(userId));
//      neighborUsers = findNearest1(Integer.parseInt(arguments[1]), node, targetPoint);
//    }
//    return neighborUsers;
//  }
//
//
//  /**
//   * Method that finds the euclidean distance between the current node and the target node
//   * @param currentNode - node we are currently evaluating
//   * @param targetPoint - node we want to measure the distance to
//   * @return the distance between two nodes in double
//   */
//  // Need to be able to get each axis from the node
//  // index 0 == weight axis, 1 == height axis, 2 == age axis?
//  public static double findDistance(KD_node<KD_Coordinate> currentNode, double[] targetPoint) {
//    double x = currentNode.getValue().getCoord(0);
//    double y = currentNode.getValue().getCoord(1);
//    double z = currentNode.getValue().getCoord(2);
//    return Math.sqrt(Math.pow(x - targetPoint[0], 2) + Math.pow(y - targetPoint[1], 2)
//        + Math.pow(z - targetPoint[2], 2));
//  }
//
//
//  /**
//   * Method that finds the euclidean distance between the target point and the farthest of the
//   * current best neighbors
//   * @param targetPoint - base node to measure distance
//   * @param neighbors - list of node coordinates that are close to the targetPoint
//   * @return the farthest distance between the targetPoint and nearest neighbors
//   */
//  public static double farthestNeighbor(double[] targetPoint, List<double[]> neighbors) {
//    double maxDistance = 0.0;
//    //double[] maxCoord = new double[3];
//    for (double[] coord: neighbors) {
//      double x2 = coord[0];
//      double y2 = coord[1];
//      double z2 = coord[2];
//      double distance = Math.sqrt(Math.pow(targetPoint[0] - x2, 2)
//          + Math.pow(targetPoint[1] - y2, 2) + Math.pow(targetPoint[2] - z2, 2));
//      if (distance > maxDistance) {
//        maxDistance = distance;
//        //maxCoord[0] = x2;
//        //maxCoord[1] = y2;
//        //maxCoord[2] = z2;
//      }
//    }
//    return maxDistance;
//  }
//
//
//  /**
//   * Helper method that converts user input to coordinate so findNearest1 method can be called
//   * @param weight - value in the x-axis of the coordinate
//   * @param height - value in the y-axis of the coordinate
//   * @param age - value in the z-axis of the coordinate
//   * @return the coordinate form of the given user values
//   */
//  public static double[] toCoord(double weight, double height, double age) {
//    double[] targetPoint = new double[3];
//    targetPoint[0] = weight;
//    targetPoint[1] = height;
//    targetPoint[2] = age;
//    return targetPoint;
//  }
//
//
//  /**
//   * TODO: Need to create userData before using this method
//   * Helper method that converts user id to coordinate so findNearest2 method can be called
//   * @param id - user id that user inputs into commands
//   * @return the coordinate form of the giver user id
//   */
//  public static double[] idToCoord(int id) {
//    User targetUser = null;
//    for (User user: userData) {
//      if (user.getUserId() == id) {
//        targetUser = user;
//      }
//    }
//    double weight = targetUser.getWeight();
//    double height = targetUser.getHeight();
//    double age = targetUser.getAge();
//    return toCoord(weight, height, age);
//  }
//
//
//  /**
//   * TODO: find out how to initialize currentNode as the root of the KD tree (line 167)
//   * TODO: need to incorporate how to limit neighbors to k
//   * Method that finds nearest neighbors of the target node
//   * @param targetPoint - coordinate of the target node
//   * @return list of coordinates of the nearest users
//   */
//  // Need to be able to get coords from each node
//  public static List<User> findNearest1(int k, KD_node<KD_Coordinate> node, double[] targetPoint) {
//    List<double[]> neighborUsers = new ArrayList<>();
//    double minDistance = Double.POSITIVE_INFINITY;
//    KD_node<KD_Coordinate> currentNode = node;
//    double currentDistance = findDistance(currentNode, targetPoint);
//
//    if (currentDistance < minDistance) {
//      minDistance = currentDistance;
//      neighborUsers.add(currentNode.getValue().getAllCoords());
//    }
//    /**
//     * TODO: How do I find the relevant axis according to depth? needed for line 178, 179
//     * TODO: find relevant axis distance (distance between two axis) here (double)
//     */
//    double currentNodeAxis = currentNode.getValue(); // x, y, or z coordinate (need a # inside parenthesis)
//    double targetNodeAxis = targetPoint[]; // x, y, or z coordinate (need a # inside bracket)
//    double axisDistance = targetNodeAxis - currentNodeAxis;
//
//    // should this be nested inside the first if?
//    if (farthestNeighbor(targetPoint, neighborUsers) > axisDistance) {
//      // recur on both children (How do I traverse through both children simultaneously?)
//      findNearest1(k, node.getLeft(), targetPoint);
//      findNearest1(k, node.getRight(), targetPoint);
//    }
//    else {
//      if (currentNodeAxis < targetNodeAxis) {
//        findNearest1(k, node.getRight(), targetPoint);
//      }
//      else {
//        findNearest1(k, node.getLeft(), targetPoint);
//      }
//    }
//    // use a helper method that converts list of coordinates to list of users
//    List<User> finalNeighbors = new ArrayList<>();
//    for (double[] coord: neighborUsers) {
//      finalNeighbors.add(getUser(coord));
//    }
//    return finalNeighbors;
//  }
//
//
//  //parses a JSON file: separates labels from data and stores data in a list of nodes
//  private LinkedList<KD_node<KD_Coordinate>> loadData(File file){
//    LinkedList<KD_node<KD_Coordinate>> imported_nodes = new LinkedList<KD_node<KD_Coordinate>>();
//    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//      String line;
//      while((line = br.readLine()) != null) {
//        String[] values = line.split(",");
//        double[] coords = new double[3];
//        for (int i = 0; i < values.length; i++){
//          String[] attribute = values[i].split(":");
//          switch(this.clean(attribute[0])){
//            case "weight":
//              coords[0] = Double.parseDouble(this.clean(attribute[1]));
//              break;
//            case "height":
//              String height = this.clean(attribute[1]);
//              String feet = height.split("'")[0];
//              String in = height.split("'")[1];
//              coords[1] = Math.addExact(Math.multiplyExact(12,Integer.parseInt(feet)), Integer.parseInt(in));
//              break;
//            case "age":
//              String age = this.clean(attribute[1]);
//              coords[2] = Double.parseDouble(age);
//              break;
//          }
//        }
//        KD_node<KD_Coordinate> node = new KD_node<KD_Coordinate>(new KD_Coordinate(coords), null, null);
//        imported_nodes.add(node);
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//      System.out.println("Error: File not found");
//    } catch (IOException e) {
//      e.printStackTrace();
//      System.out.println("Error:");
//    }
//    return imported_nodes;
//  }
//
//  private String clean(String s){
//    return s.replace("{", "")
//        .replace("}", "")
//        .replaceAll(" ", "")
//        .replaceAll("\"", "")
//        .replaceAll("\\\\", "")
//        .replace("[", "")
//        .replace("]", "")
//        .replace("lbs", "");
//  }
//
//
//}