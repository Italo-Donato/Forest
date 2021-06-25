
public class App {
    public static void main(String[] args) throws Exception {
        SBBTree tree = new SBBTree(4);
        tree.add(3);
        tree.add(6);
        tree.add(7);
        tree.add(9);
        tree.add(8);

        
        tree.printTree();
    }
}
