package Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


//Trying to cut corners and be faster. Builds our specific tree with 3 coordinates (XYZ)
public class Tree_Builder {

  public Tree_Builder(){
  }

  //parses a data file: separates labels from data and stores data in nodes to be added to tree
  public LinkedList<KD_Coordinate> loadData(File file){

    LinkedList<KD_Coordinate> imported_coords = new LinkedList<KD_Coordinate>();

    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while((line = br.readLine()) != null) {
        String[] values = line.split(",");
        double[] coords = new double[3];
        for (int i = 0; i < values.length; i++){
          String[] attribute = values[i].split(":");
          switch(attribute[0]){
            case ("weight"):
              coords[0] = Double.parseDouble(attribute[1]);
              break;
            case ("height"):
              coords[1] = Double.parseDouble(attribute[1]);
              break;
            case ("age"):
              coords[2] = Double.parseDouble(attribute[1]);
              break;
          }
        }

        KD_Coordinate coordinate = new KD_Coordinate(coords);
        imported_coords.add(coordinate);
        //KD_node node = new KD_node<double[]>(coords, null, null);
        //imported_nodes.add(node);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error: File not found");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error: IOException");
    }
    return imported_coords;
  }
}
