package core;

import client.ApiClient;


/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

    private static final int DEFAULT_PORT = 4567;

    /**
     * The initial method called when execution begins.
     *
     * @param args An array of command line arguments
     */
    public static void main(String[] args) {
        ApiClient client = new ApiClient();
        Repl repl = new Repl();
        repl.run(client);

    }
}
