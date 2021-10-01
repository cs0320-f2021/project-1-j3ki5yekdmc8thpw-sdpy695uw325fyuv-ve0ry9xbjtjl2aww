package test.java;

import main.java.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
  @Test
  public void testGetUserId() {
    User user = new User(123456, 150.0, 65.0, 25, "virgo");
    int id = user.getUserId();
    assertEquals(123456, id, 0.01);
  }

  @Test
  public void testGetHoroscope() {
    User user = new User(123456, 150.0, 65.0, 25, "virgo");
    String horoscope = user.getHoroscope();
    assertEquals("virgo", horoscope);
  }

  @Test
  public void testGetWeight() {
    User user = new User(123456, 150.0, 65.0, 25, "virgo");
    double weight = user.getWeight();
    assertEquals(150.0, weight, 0.01);
  }

  @Test
  public void testGetHeight() {
    User user = new User(123456, 150.0, 65.0, 25, "virgo");
    double height = user.getHeight();
    assertEquals(65.0, height, 0.01);
  }

  @Test
  public void testGetAge() {
    User user = new User(123456, 150.0, 65.0, 25, "virgo");
    double age = user.getAge();
    assertEquals(25, age, 0.01);
  }
}
