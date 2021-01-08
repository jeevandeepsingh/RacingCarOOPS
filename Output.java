import java.util.ArrayList;
import java.util.List;

//To store values of Top Speed Cars
public class Output
{

    public String team;
    public String car;
    public Integer speed_achieved;
    public Integer funds_spent;
    public List<String> parts = new ArrayList<>();

    public Output(){}

    public Output(String a, String b,Integer c,Integer d,List<String>e)
    {
        team = a;
        car = b;
        speed_achieved = c;
        funds_spent = d;
        parts = e;
    }

    public void PrintVal()
    {
        System.out.println("Winner");
        System.out.println("=====");
        System.out.println("Team:");
        System.out.println(team);
        System.out.println("Car");
        System.out.println(car);
        System.out.println("Speed achieved");
        System.out.println(speed_achieved);
        System.out.println("Funds spent");
        System.out.println(funds_spent);
        System.out.println("Parts");
        for(int i = 0 ; i < parts.size() ; i++)
        {
            System.out.println(parts.get(i));
        }
    }
}