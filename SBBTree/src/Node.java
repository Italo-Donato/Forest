
public class Node {
    public Item reg;
    public Node parent = null;
    public Node leftnNode = null, rightnNode = null;
    public boolean slpLeft = false, slpRight = false;//0 for vertical 1 for horizontal

    public Node(Item item,Node p){
        parent = p;
        reg = item;
    }

    public Node(int key){
        reg = new Item(key);
    }

    
}
