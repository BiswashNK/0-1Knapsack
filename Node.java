import java.util.ArrayList;
/**
 * Node class that represents a node in the search tree
 
 */
public class Node {
  int level;
  int profit;
  int weight;
  double bound;
  ArrayList<Item> selectedItems;
  /**
   * Constructor for the Node class that initializes the level, profit, weight, bound and selected items
  
  */
  Node (){
    this.level = 0;
    this.profit = 0;
    this.weight = 0;
    this.bound = 0;
    this.selectedItems = new ArrayList<>();
  }

  /**
   * Constructor for the Node class that initializes the level, profit, weight, bound and selected items based on the given node
   * @param level the level
   * @param profit the profit
   * @param weight the weight
   * @param bound the bound
   * @param selectedItems the selected items
   */
  Node(Node node){
    this.level = node.level;
    this.profit = node.profit;
    this.weight = node.weight;
    this.bound = node.bound;
    this.selectedItems = new ArrayList<>(node.selectedItems);
  }
}