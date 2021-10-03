
//takes in a data set and builds a kd tree


import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;


public class KD_tree {
  private LinkedList<KD_node> tree;
  private int dimensions_;
  private KD_node root_ = null;
  private KD_node best_ = null;
  private double bestDistance_ = 0;
  private int visited_ = 0;

  //KD_tree builder: takes in a JSON file and builds a KD tree with specified dimensinos
  public KD_tree (int dimensions, File file){
    dimensions_ = dimensions;
    LinkedList<KD_node> nodes = this.loadData(file);
    root_ = this.buildTree(nodes, 0, nodes.size(), 0);
  }

  //takes in a list of nodes and orders into a balanced KD tree. returns head node
  private KD_node buildTree(LinkedList<KD_node> nodes, int begin, int end, int index){
    if (end <= begin)
      return null;
    int n = begin + (end - begin)/2;

    //sorts nodes by first index
    KD_node node = QuickSelect.select(nodes, begin, end - 1, n, new NodeComparator(index));

    //recurs, building tree of subnodes using next coordinate
    index = (index + 1) % dimensions_;
    System.out.println("index: " + index + " out of " + dimensions_ +" dimensions");
    node.setLeft(buildTree(nodes, begin, n, index));
    node.setRight(buildTree(nodes, n + 1, end, index));
    return node;
  }

  //parses a data file: separates labels from data and stores data in nodes to be added to tree
  private LinkedList<KD_node> loadData(File file){
    LinkedList<KD_node> imported_nodes = new LinkedList<KD_node>();
    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while((line = br.readLine()) != null) {
        String[] values = line.split(",");
        double[] coords = new double[dimensions_];
        for (int i = 0; i < values.length; i++){
          String[] attribute = values[i].split(":");
          switch(this.clean(attribute[0])){
            case "weight":
              coords[0] = Double.parseDouble(this.clean(attribute[1]));
              break;
            case "height":
              String height = this.clean(attribute[1]);
              String feet = height.split("'")[0];
              String in = height.split("'")[1];
              coords[1] = Math.addExact(Math.multiplyExact(12,Integer.parseInt(feet)), Integer.parseInt(in));
              break;
            case "age":
              String age = this.clean(attribute[1]);
              coords[2] = Double.parseDouble(age);
              break;
          }
        }
        KD_node node = new KD_node(coords);
        imported_nodes.add(node);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error: File not found");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error:");
    }
    return imported_nodes;
  }


  //takes the index of one coordinate and compares two nodes based on that value
  private static class NodeComparator implements Comparator<KD_node> {
    private int index_;

    private NodeComparator(int index) {
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
  }

  public KD_node getRoot_(){
    return root_;
  }

  private String clean(String s){
    return s.replace("{", "")
        .replace("}", "")
        .replaceAll(" ", "")
        .replaceAll("\"", "")
        .replaceAll("\\\\", "")
        .replace("[", "")
        .replace("]", "")
        .replace("lbs", "");
  }

}