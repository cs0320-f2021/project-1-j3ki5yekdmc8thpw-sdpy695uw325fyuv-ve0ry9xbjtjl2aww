package core;

import Tree.Coordinate;
import Tree.KD_tree;
import Tree.Tree_Builder;
import client.ApiClient;
import client.ClientRequestGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * The REPL class for this lab.
 */
public class Repl {

    /**
     * instantiates a REPL.
     *
     * param commands - a map of string command names to IReplMethod objects, which
     *                 are a wrapper for a method to be called
     */
    public Repl() {
    }

    /**
     * This run method for the REPL requires an ApiClient object.
     *
     * @param client
     */
    public void run(ApiClient client) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) { // parsing input loop
            try {
                String s = reader.readLine();
                if (s == null) { // ctrl-D (exit) would result in null input
                    break;
                }

                // initialize a StringTokenizer to help parse the input, broken by space or tabs
                StringTokenizer st = new StringTokenizer(s, " \t", false);
                ApiAggregator aggregator = new ApiAggregator();
                if (st.hasMoreTokens()) { // if the input is not blank, get the first token (the command)
                    String command = st.nextToken();
                    if (command.equals("users")) { // Basic GET request
                        // KD TREE JSON FILE (aggregator.getData("users"));
                        // client.makeRequest(ClientRequestGenerator.getSecuredRequest());
                        Tree_Builder<Double> builder = new Tree_Builder<Double>();


                        // @TODO: pass a file into builder
                         // builder.loadData();
                        KD_tree<Coordinate<Double>> tree = new KD_tree<Coordinate<Double>>(3,
                            builder.loadData(new File()));

                    } else if(command.equals("reviews")) {
                        // KD TREE JSON FILE (aggregator.getData("reviews"));
                    } else if(command.equals("rent")) {
                        // KD TREE JSON FILE (aggregator.getData("rent"));
                    }
                    else { // command unrecognized
                        System.out.println("ERROR: Unrecognized command.");
                    }
                }
            } catch (IOException e) { // some kind of read error, so the repl exits
                System.out.println("ERROR: Failed parsing input.");
                break;
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR: Failed to close reader.");
        }
    }
}
