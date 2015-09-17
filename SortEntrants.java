import java.util.*;
import java.io.*;

public class SortEntrants {
  public static void main(String args[]) throws IOException{
  	ArrayList<Entrant> entrants = new ArrayList<Entrant>();
  	System.out.print("Enter the file name: ");
  	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  	String fileName;
  	fileName = br.readLine();
  	System.out.println(fileName);
  	String line = null;
  	FileReader fileReader = new FileReader(fileName);
  	BufferedReader bufferedReader = new BufferedReader(fileReader);
  	while ( (line = bufferedReader.readLine()) != null) {
      String[] parts = line.split(" ");
      entrants.add(new Entrant(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), ""));
  	}

    Collections.sort(entrants);

    int num_out = find_seeds(entrants);
    int num_pools = find_pools(entrants);

    ArrayList<ArrayList<Entrant>> pieces = new ArrayList<ArrayList<Entrant>>();

    pieces = Pools_To_Bracket(entrants, num_out, num_pools);
    ArrayList<Entrant> bracket = new ArrayList<Entrant>();
    bracket = merge_bracket(pieces, bracket);

    print(bracket, num_pools);
  }

  public static ArrayList<ArrayList<Entrant>> Pools_To_Bracket(ArrayList<Entrant> entrants, int num_out, int num_pools) {
    ArrayList<ArrayList<Entrant>> bracket = new ArrayList<ArrayList<Entrant>>();
    ArrayList<Integer> pools = new ArrayList<Integer>();
    Entrant entrant;
    int total = entrants.size();
    int index = 0;
    int pools_total = -1;
    while (entrants.size() > 0) {
      ArrayList<Entrant> list = new ArrayList<Entrant>();
      while (list.size() < num_pools) {
        int z = 1;
        int x = num_out;
        while (z < x) {
          if (pools.size() >= num_pools) {
            pools.clear();
          } 
          if (z == 1) {
            if( pools_total != -1) {
              //index = find_entrant(entrants, z, pools, index, num_pools, pools_total);
              index = find_entrant(entrants, z, pools);
              pools_total = -1;
            } else {
              index = find_entrant(entrants, z, pools, index);
              pools_total = entrants.get(index).pool;
            }
          } else {
            index = find_entrant(entrants, z, pools, index);
          }
          z++;
          list.add(entrants.get(index));
          entrants.remove(index);
          index = find_entrant(entrants, x, pools, index);
          x--;
          list.add(entrants.get(index));
          entrants.remove(index);
        }
      }
      bracket.add(list);
    }
    return bracket;
  }

  // Merge the bracket sections into one bracket
  public static ArrayList<Entrant> merge_bracket(ArrayList<ArrayList<Entrant>> pieces, ArrayList<Entrant> bracket) {
    if (pieces.size() <= 1) {
      return bracket;
    }
    for (Entrant ent: pieces.get(0)) {
      bracket.add(ent);
    }
    for (Entrant ent: pieces.get(pieces.size() - 1)) {
      bracket.add(ent);
    }
    pieces.remove(0);
    pieces.remove(pieces.size() - 1);
    return merge_bracket(pieces, bracket);
  }

  // Search for an Entrant from the start of the list
  public static int find_entrant(ArrayList<Entrant> entrants, int seed, ArrayList<Integer> pools) {
    for(int i = 0; i < entrants.size(); i++) {
      if (entrants.get(i).seed == seed) {
        if (pools.contains(new Integer(entrants.get(i).pool))) {
          continue;
        }
        pools.add(new Integer(entrants.get(i).pool));
        return i;
      }
    }
    return 0;
  }

  // Search for an Entrant using next best fit.
  public static int find_entrant(ArrayList<Entrant> entrants, int seed, ArrayList<Integer> pools, int index) {
    int i = index;
    while (true) {
      if (i >= entrants.size()) {
        i = 0;
      }

      if(entrants.get(i).seed == seed) {
        if (pools.contains(new Integer(entrants.get(i).pool))) {
          i++;
          continue;
        }
        pools.add(new Integer(entrants.get(i).pool));
        return i;
      }

      i++;

      if (i == index) {
        return 0;
      }
    }
  }

  // Search for an Entrant using next best fit.  For finding a first seed
  // that is a match for the last first seed
  public static int find_entrant(ArrayList<Entrant> entrants, int seed, ArrayList<Integer> pools, int index, int num_pools, int pool_total) {
    int i = index;
    while (true) {
      if (i >= entrants.size()) {
        i = 0;
      }

      if (entrants.get(i).seed == seed && entrants.get(i).pool + pool_total == num_pools + 1) {
        if (pools.contains(new Integer(entrants.get(i).pool))) {
          i++;
          continue;
        }
        pools.add(new Integer(entrants.get(i).pool));
        return i;
      }

      i++;
      if (i == index) {
        return 0;
      }
    }
  }

  // Find how many people made it out of pools
  public static int find_seeds(ArrayList<Entrant> entrants) {
    int max_seed = 0;
    for (Entrant entrant: entrants) {
      if (entrant.seed > max_seed) {
        max_seed = entrant.seed;
      }
    }
    return max_seed;
  }

  // Find how many pools there were.
  public static int find_pools(ArrayList<Entrant> entrants) {
    int max_pool = 0;
    for (Entrant entrant: entrants) {
      if (entrant.pool > max_pool) {
        max_pool = entrant.pool;
      }
    }
    return max_pool;
  }

  // print the list with gaps between bracket sections
  public static void print(ArrayList<Entrant> entrants, int pools) {
    int count = 0;
    for(Entrant entrant: entrants) {
      System.out.println(entrant);
      count++;
      if (count == pools) {
        System.out.println();
        count = 0;
      }
    }
  }
}

