import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * BestBoundKnapsack class that solves the knapsack problem using the best bound method
 * 
 */
public class BestBoundKnapsack {
  private ArrayList<Item> items;
  private int capacity;
  private int numItems;
  private Node bestNode;
  /**
   * Constructor for BestBoundKnapsack
   * @param filename the input file name
   */
public BestBoundKnapsack(String filename){ 
    readFromFile(filename);
}

/**
 * Read from file
 * @param filename the input file name
 */
private void readFromFile(String filename){
  File inputFile = new File(filename);
  items = new ArrayList<>();
  try{
    Scanner scanner = new Scanner(inputFile);
    numItems = Integer.parseInt(scanner.nextLine().trim());
    capacity = Integer.parseInt(scanner.nextLine().trim());
    while (scanner.hasNextLine()){
      Scanner lineScanner = new Scanner(scanner.nextLine());
      String label = lineScanner.next();
      int value = lineScanner.nextInt();
      int weight = lineScanner.nextInt();
      Item item = new Item(label, weight, value);
      items.add(item);
      lineScanner.close();  
    }
    scanner.close();

  }
  catch (FileNotFoundException e){
    e.printStackTrace();
  }

}

/**
 * Solve the knapsack problem
*/

public void solve(){
 
  Collections.sort(this.items, new CompareByRatio());

  
  Node root = new Node();
  root.bound = bound(root);
  PriorityQueue<Node> queue = new PriorityQueue<>(new CompareNodes());

  queue.add(root);
  this.bestNode = findBestNode(queue);
  
  
 
  
}

/**
 * Find the best node
 * @param queue the priority queue
 * @return the best node
 */
private Node findBestNode(PriorityQueue<Node> queue){
  Node bestOfAll = new Node();
  while (!queue.isEmpty()){
    Node currentNode = queue.remove();

    if (bound(currentNode) > bestOfAll.profit && currentNode.level < items.size()){
      Node addedNext = new Node(currentNode);
      addedNext.level = currentNode.level + 1;
      addedNext.weight += items.get(addedNext.level -1).weight;
      addedNext.profit += items.get(addedNext.level -1).value;
      addedNext.selectedItems.add(items.get(addedNext.level -1));
      bestOfAll = updateBest(queue, addedNext, bestOfAll);



      Node notAddedNext = new Node(currentNode);
      notAddedNext.level = currentNode.level + 1;
      notAddedNext.weight = currentNode.weight;
      notAddedNext.profit = currentNode.profit;
      notAddedNext.bound = bound(notAddedNext);
      notAddedNext.selectedItems = new ArrayList<>(currentNode.selectedItems);
      bestOfAll = updateBest(queue, notAddedNext, bestOfAll);
    }
  }
  return bestOfAll;
}
/**
 * Update the best node
 * @param queue the priority queue
 * @param currNode the current node
 * @param bestOfAll the best node
 * @return the best node
 */
private Node updateBest(PriorityQueue<Node> queue, Node currNode, Node bestOfAll){
  if (currNode.profit > bestOfAll.profit && currNode.weight <= capacity){
    bestOfAll = currNode;
  }
  if (bound(currNode) > bestOfAll.profit){
    
    queue.add(currNode);
  }
  return bestOfAll;
}

/**
 * Calculate the bound
 * @param node the node
 * @return the bound of the node
 */

private double bound(Node node){
  if (node.weight >= capacity){
    return 0;
  }
  double profitBound = node.profit;
  int weightBound = node.weight;
  int level = node.level;
  while (level < numItems && weightBound + items.get(level).weight <= capacity){
    
    weightBound += items.get(level).weight;
   
    profitBound += items.get(level).value;
    level++;
  }
 
  if (level < numItems){
    profitBound += (capacity - weightBound) * items.get(level).ratio;
  }
  return profitBound;
}

/**
 * Print the item
 * @param label the label
 * @return the item with that label
 */

public String printItem(String label){
  for (Item each : items){
    if (each.label.equals(label)){
      return each.label + " " + each.weight + " " + each.value + " " + each.ratio;
    }
  }
  return "Item not found";
}
/**
 * Print the item
 * @param index the index
 * @return the item at that index
 */
public String printItem(int index){
  if (index < items.size()){
    Item item = items.get(index);
    return item.label + " " + item.weight + " " + item.value + " " + item.ratio;
  }
  return "Item not found";
}
/**
 * Print the solution
 * @return the solution consisting of the items included in the knapsack, the total weight, and the total value
 */

public String stdOutput (){
  StringBuilder sb = new StringBuilder();
  
  sb.append("Items included in knapsack: ");
  
  for (Item each : bestNode.selectedItems){
    sb.append(each.label);
    sb.append(", ");
  }
  sb.deleteCharAt(sb.length() - 2);
  sb.append("\n");
  sb.append("Total weight = ");
  sb.append(bestNode.weight);
  sb.append("\n");
  sb.append("Total value = ");
  sb.append(bestNode.profit);
  sb.append("\n");
  
  
  return sb.toString();
}



}