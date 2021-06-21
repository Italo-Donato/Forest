import java.util.Objects;

public class BinaryTree{
    private Node initial;
    private Node aux;

    public BinaryTree(Node node){
        initial = node;
        aux = initial;
    }
    public BinaryTree(Item item){
        initial = new Node(item);
        aux = initial;
    }
    public BinaryTree(int key){
        initial = new Node(key);
        aux = initial;
    }

    public Node add(int key){
        return add(new Item(key));
    }

    public Node add(Item item){
        Node init = aux;
        int situation = init.reg.compareTo(item);

        if(situation == 1){
            if(Objects.isNull(init.leftnNode)){
                init.leftnNode = new Node(item);
                aux = initial;
                return init;
            }
            else{
                aux = init.leftnNode;
                return add(item);
            }
        }
        else if(situation == -1){
            if(Objects.isNull(init.rightnNode)){
                init.rightnNode = new Node(item);
                aux = initial;
                return init;
            }
            else{
                aux = init.rightnNode;
                return add(item);
            }
        }
        aux = initial;
        return null;
    }
    
    public Item search(int key){
        return search(new Item(key));
    }

    public Item search(Item item){
        Node init = aux;
        int situation = init.reg.compareTo(item);

        switch (situation){
            case 0:
                return item;
            case 1:
                if(!Objects.isNull(init.leftnNode)){
                    aux = init.leftnNode;
                    return search(item);
                    }
                aux = initial;
                return null;
            case -1:
                if(!Objects.isNull(init.rightnNode)){
                    aux = init.rightnNode;
                    return search(item);
                }
                aux = initial;
                return null;
        }
        aux = initial;
        return null;
    }

    public void pritTree(){
        Node node = aux;
        System.out.println("\t"+node.reg.getkey());
        if(!Objects.isNull(node.leftnNode) && !Objects.isNull(node.rightnNode)){
            System.out.println(node.leftnNode.reg.getkey()+"\t\t"+node.rightnNode.reg.getkey());
            aux = node.leftnNode;
            pritTree();
            aux = node.rightnNode;
            pritTree();
        }
        else if(!Objects.isNull(node.rightnNode)){
            System.out.println("\t\t"+node.rightnNode.reg.getkey());
            aux = node.rightnNode;
            pritTree();
        }
        else if(!Objects.isNull(node.leftnNode)){
            System.out.println(node.leftnNode.reg.getkey());
            aux = node.leftnNode;
            pritTree();
        }
        aux = initial;
    }
}