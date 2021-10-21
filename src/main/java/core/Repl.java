package core;

import client.ApiClient;
import core.Commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

        LinkedList<Command> commands = new LinkedList<Command>();

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
                    Mapper mapper = new Mapper();
                    mapper.get(command).executeCommand();
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
