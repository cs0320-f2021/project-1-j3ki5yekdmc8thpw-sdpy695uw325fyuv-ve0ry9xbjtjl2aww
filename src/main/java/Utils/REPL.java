package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REPL {
  public void run() throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");

          // allow user command
          switch (arguments[0]) {

            case "similar":
//              if (Integer.parseInt(arguments[1]) <= 0) {
//                throw new Exception("ERROR: k must be greater than zero");
//              }
//              if (userData.isEmpty()) {
//                throw new Exception("ERROR: must provide user data");
//              } else {
//                UserHandler nearestNeighbors = new UserHandler(userData);
//              }
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
