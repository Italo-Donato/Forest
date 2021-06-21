public class Node {
    public Item reg;
    public Node leftnNode = null, rightnNode = null;

    public Node(Item item){
        reg = item;
    }

    public Node(int key){
        reg = new Item(key);
    }
}
