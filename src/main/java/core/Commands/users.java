package core.Commands;

import Tree.Coordinate;
import Tree.KD_tree;
import Tree.Tree_Builder;

import java.io.File;

public class users implements Command {
  public users(){
  }

  @Override
  public void executeCommand() {
     //KD_TREE JSON_FILE (aggregator.getData("users"));
     // client.makeRequest(ClientRequestGenerator.getSecuredRequest());
    Tree_Builder<double> builder = new Tree_Builder<double>();

    // @TODO: pass a file into builder
    // builder.loadData();
    KD_tree<Coordinate<Double>> tree = new KD_tree<Coordinate<Double>>(3,
        builder.loadData(new File()));
  }
}