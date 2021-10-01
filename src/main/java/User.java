package main.java;

public class User {
  private String horoscope;
  private String[] headers;
  private double weight;
  private double height;
  private double age;
  private int userId;

  /**
   * User class constructor
   * @param horoscope - horoscope of the user
   * @param weight - weight of the user in lbs
   * @param height - height of the user in inches
   * @param age - age of the user in years
   * @param userId - user ID
   */
  public User(int userId, double weight, double height, double age, String horoscope) {
    this.horoscope = horoscope;
    this.weight = weight;
    this.height = height;
    this.age = age;
    this.userId = userId;
  }


  /**
   * basic getter functions
   */
  public String getHoroscope() {
    return horoscope;
  }

  public double getWeight() {
    return weight;
  }

  public double getHeight() {
    return height;
  }

  public double getAge() {
    return age;
  }

  public int getUserId() {
    return userId;
  }

}
