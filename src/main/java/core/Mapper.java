package core;

import core.Commands.Command;
import core.Commands.rent;
import core.Commands.reviews;
import core.Commands.users;

import java.util.HashMap;
import java.util.LinkedList;

public class Mapper {
  HashMap<String, Command> map;

  public Mapper(){
    map = new HashMap<String, Command>();
    Command users_ = new users();
    Command rent_ = new rent();
    Command reviews_ = new reviews();

    map.put("users", users_);
    map.put("rent", rent_);
    map.put("reviews", reviews_);
  }

  public Command get(String key){
    return map.get(key);
  }

}




