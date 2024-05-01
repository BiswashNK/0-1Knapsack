
/**
 * Item class to represent an item with label, weight, value and ratio
 */
public class Item{
  String label;
  int weight;
  int value;
  double ratio;
  /**
   * Constructor for the Item class
   * @param label the label
   * @param weight the weight
   * @param value the value
   */
  Item(String label, int weight, int value){
    this.label = label;
    this.weight = weight;
    this.value = value;
    this.ratio = Double.parseDouble(String.format("%.1f",(double) value / weight));
  }
}