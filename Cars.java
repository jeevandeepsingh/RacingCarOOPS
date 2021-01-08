import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//To store values of Cars.csv
public class Cars
{
    public String car_id;
    public String car_name;
    public int base_speed;
    public int top_speed;
    public String part_list_id;

    public Cars(String a,String b,int c,int d,String e)
    {
        car_id = a;
        car_name = b;
        base_speed = c;
        top_speed = d;
        part_list_id =e;
    }
}