package DataTransferObjects;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("user_id")
    int user_id;
    String weight;
    String bust_size;
    String height;
    String age;
    String body_type;
    String horoscope;
}
