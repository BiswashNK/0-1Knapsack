import java.util.Comparator;
/**
 * CompareByRatio class that compares two items by their ratio
 */
public class CompareByRatio implements Comparator<Item> {
  /**
   * Constructor for CompareByRatio
   */
  public CompareByRatio(){
    // empty constructor
  }
  /**
   * Compare two items by their ratio
   * @param i1 the first item
   * @param i2 the second item
   * @return the comparison result
   */
  @Override
  public int compare(Item i1, Item i2){
    return Double.compare(i2.ratio, i1.ratio);
  }
}