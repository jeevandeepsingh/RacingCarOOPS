import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//To store values of Teams.csv
public class Teams
{
    public int team_id;
    public String team_name;
    public List<String> car_list_id = new ArrayList<>();
    public List<Integer> funds = new ArrayList<>();

    public Teams(int a,String b)
    {
        team_id = a;
        team_name = b;
    }
}