import java.util.Comparator;

/**
 * CompareNodes class that compares two nodes by their bound
 */
public class CompareNodes implements Comparator<Node>{
  /**
   * Constructor for CompareNodes
   */
  public CompareNodes(){
    // empty constructor
  }
  /**
   * Compare two nodes by their bound
   * @param n1 the first node
   * @param n2 the second node
   * @return the comparison result
   */
  @Override
  public int compare(Node n1, Node n2){
    return Double.compare(n2.bound, n1.bound);
  }
}
