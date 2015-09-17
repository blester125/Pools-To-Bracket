public class Entrant implements Comparable<Entrant> {
  public int pool;
  public int seed;
  public String name;
  
  public Entrant(int poolNumber, int seedNumber, String playerName) {
    pool = poolNumber;
    seed = seedNumber;
    name = playerName;
  }
  
  public String toString() {
    return "Pool: " + pool +" Seed: " + seed + " " + name;
  }
  
  public int compareTo(Entrant other) {
    if(this.pool == other.pool){
      return this.seed - other.seed;
    }
    return this.pool - other.pool;
  }
}
