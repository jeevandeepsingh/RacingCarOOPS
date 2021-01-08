import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class RacingCars
{

    public static Map<Integer, Teams> team_id_mp = new HashMap<>();//Store Teams Object According to team_id
    public static Map<String, Cars> car_id_mp = new HashMap<>();//Store Cars Object According to Car_id
    public static Map<String, Parts> part_id_mp = new HashMap<>();//Store Parts Object According to part_id
    public static Map<Integer,Output> top_speed_car_list = new HashMap<>();//Store Output Object According to Top_speed

    public static void TeamsData(String a)//Handle Teams Data
    {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/jeevan/IdeaProjects/singhjeevandeep1999-racing-car/src/"+a));// create an instance of BufferedReader
            br.readLine();//To eliminate first line
            while ((line = br.readLine()) != null) // read the first line from the CSV file
            {
                String[] temp = line.split(splitBy);
                int key = parseInt(temp[0]);
                if (team_id_mp.containsKey(key))
                {
                    Teams t = team_id_mp.get(key);
                    t.car_list_id.add(temp[2]);
                    t.funds.add(parseInt(temp[3]));
                }
                else
                {
                    Teams t = new Teams(parseInt(temp[0]),temp[1]);
                    t.car_list_id.add(temp[2]);
                    t.funds.add(parseInt(temp[3]));
                    team_id_mp.put(key, t);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void CarsData(String a)//Handle Cars Data
    {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/jeevan/IdeaProjects/singhjeevandeep1999-racing-car/src/" + a));
            br.readLine();//To eliminate first line
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(splitBy);
                Cars c = new Cars(temp[0],temp[1],parseInt(temp[2]),parseInt(temp[3]),temp[4]);
                car_id_mp.put(temp[0],c);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static  void PartData(String a)//Handle Part Data
    {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/jeevan/IdeaProjects/singhjeevandeep1999-racing-car/src/" + a));
            br.readLine();//To eliminate first line
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(splitBy);
                if(part_id_mp.containsKey(temp[0]))
                {
                    part_id_mp.get(temp[0]).part_id.add(temp[1]);
                    part_id_mp.get(temp[0]).price.add(parseInt(temp[2]));
                    part_id_mp.get(temp[0]).speed_boost.add(parseInt(temp[3]));
                }
                else
                {
                    Parts p = new Parts(temp[0]);
                    p.part_id.add(temp[1]);
                    p.price.add(parseInt(temp[2]));
                    p.speed_boost.add(parseInt(temp[3]));
                    part_id_mp.put(temp[0],p);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static int LogicImpl()
    {
        int maximum_top_speed = 0;
        for(int i = 0 ; i < team_id_mp.size() ; i++)
        {
            Teams t = team_id_mp.get(i);
            List<String> car_list_id = t.car_list_id;
            List<Integer> funds = t.funds;
            for(int j = 0 ; j < car_list_id.size() ; j++)
            {
                Cars c = car_id_mp.get(car_list_id.get(j));
                int cost = funds.get(j);
                int base_speed = c.base_speed;
                int top_speed = c.top_speed;
                Parts p = part_id_mp.get(c.part_list_id);
                List<Integer> price = p.price;
                List<Integer> speed_boost = p.speed_boost;
                int n = price.size();

                int[][] k = new int[n+1][cost+1];

                for(int u = 0 ; u <= n ; u++)
                {
                    for(int v = 0 ; v <= cost ; v++)
                    {
                        if(u == 0 || v == 0)
                        {
                            k[u][v] = base_speed;
                        }
                        else if(price.get(u-1) <= v)
                        {
                            k[u][v] = max(speed_boost.get(u-1)+k[u-1][v-price.get(u-1)],k[u-1][v]);
                            if(k[u][v] > top_speed) k[u][v] = k[u-1][v];
                        }
                        else
                        {
                            k[u][v] = k[u-1][v];
                        }
                    }
                }
                List<String>a = new ArrayList<>();
                List<String>b = p.part_id;
                int x = n,y = cost,fund_spent = 0;

                while(x > 0 && y > 0)
                {
                    if(k[x][y] == k[x-1][y])    x--;
                    else
                    {
                        a.add(b.get(x-1));
                        fund_spent+=(price.get(x-1));
                        y -= price.get(x-1);
                        x--;
                    }
                }

                Output o = new Output(t.team_name,c.car_name,k[n][cost],fund_spent,a);
                top_speed_car_list.put(k[n][cost],o);
                maximum_top_speed = max(maximum_top_speed,k[n][cost]);
            }
        }
        return maximum_top_speed;
    }

    public static void main(String []args)
    {
        TeamsData(args[0]);
        CarsData(args[1]);
        PartData(args[2]);
        int maximum_top_speed = LogicImpl();
        top_speed_car_list.get(maximum_top_speed).PrintVal();
    }
}