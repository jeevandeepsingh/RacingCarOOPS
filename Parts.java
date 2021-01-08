import java.util.ArrayList;
import java.util.List;

//To store values of Parts.csv
public class Parts
{
    public String part_list_id;
    List<String> part_id = new ArrayList<>();
    List<Integer> price = new ArrayList<>();
    List<Integer> speed_boost = new ArrayList<>();

    public Parts(String a)
    {
        part_list_id = a;
    }
}