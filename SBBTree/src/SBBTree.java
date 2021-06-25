import java.util.Objects;

public class SBBTree{
    private Node initial;
    private Node aux;

    // constructors
    public SBBTree(Node node){
        initial = node;
        aux = initial;
    }
    public SBBTree(Item item){
        initial = new Node(item, null);
        aux = initial;
    }
    public SBBTree(int key){
        initial = new Node(key);
        aux = initial;
    }
    
    // add
    public Node add(int key){
        return add(new Item(key));
    }

    public Node add(Item item){
        Node init = aux;
        int situation = init.reg.compareTo(item);

        if(situation == 1){
            if(Objects.isNull(init.leftnNode)){
                init.leftnNode = new Node(item,init);
                init.slpLeft = true;
                aux = init.leftnNode;
                transform(true);
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
                init.rightnNode = new Node(item, init);
                init.slpRight = true;
                aux = init.rightnNode;
                transform(false);
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
    
    //transform
    private void transform(boolean left){
        if(!Objects.isNull(aux.parent.parent)){
            boolean lessThan = aux.parent.reg.getkey() < aux.parent.parent.reg.getkey();
            if(left){
                if(aux.parent.parent.slpLeft && lessThan){
                    ll();
                }
                else if(aux.parent.parent.slpRight && !lessThan){
                    rl();
                }
            }
            else{
                if(aux.parent.parent.slpLeft && lessThan){
                    lr();
                }
                else if(aux.parent.parent.slpRight && !lessThan){
                    rr();
                }
            }
        }
    }

    private Node ll(){
        Node init = aux.parent.parent, kid = aux.parent;
        init.leftnNode = kid.rightnNode;
        if(!Objects.isNull(kid.rightnNode))
            kid.rightnNode.parent = init;
        kid.rightnNode = init;  
        if(!Objects.isNull(init.parent)){    
            init.parent.leftnNode = kid;
            kid.parent = init.parent;
        }
        else{
            kid.parent = null;  
            initial = kid;
        }
        init.parent = kid;  

        kid.slpLeft = false;
        init.slpLeft = false;

        aux = initial;
        return kid;
    }

    private Node lr(){
        Node init = aux.parent.parent, prt = aux.parent, kid = aux;

        init.slpLeft = false; //passo 1
        prt.slpRight = false; 

        prt.rightnNode = kid.leftnNode;//passo 2
        if(Objects.nonNull(kid.leftnNode))
            kid.leftnNode.parent = prt;
        
        kid.leftnNode = prt;//passo 3
        prt.parent = kid;

        init.leftnNode = kid.rightnNode;//passo 4
        if(Objects.nonNull(kid.rightnNode))
            kid.rightnNode.parent = init;
        
        kid.parent = init.parent;//passo 5
        if(Objects.isNull(init.parent)){
            initial = kid;
        }
        else if(kid.reg.getkey() > init.parent.reg.getkey()){
            init.parent.rightnNode = kid;
        }
        else{
            init.parent.leftnNode = kid;
        }
        kid.rightnNode = init;
        init.parent = kid;


        aux = initial;
        return kid;
    }

    private Node rr(){
        Node init = aux.parent.parent, kid = aux.parent;
        init.rightnNode = kid.leftnNode;
        if(!Objects.isNull(kid.leftnNode))
            kid.leftnNode.parent = init;
        kid.leftnNode = init;  
        if(!Objects.isNull(init.parent)){    
            init.parent.rightnNode = kid;
            kid.parent = init.parent;
        }
        else{
            kid.parent = null;  
            initial = kid;
        }
        init.parent = kid;  

        kid.slpRight = false;
        init.slpRight = false;
        aux = initial;
        return kid;
    }

    private Node rl(){
        Node init = aux.parent.parent, prt = aux.parent, kid = aux;

        init.slpRight = false; //passo 1
        prt.slpLeft = false; 

        prt.leftnNode = kid.rightnNode;//passo 2
        if(Objects.nonNull(kid.rightnNode))
            kid.rightnNode.parent = prt;
        
        kid.rightnNode = prt;//passo 3
        prt.parent = kid;

        init.rightnNode = kid.leftnNode;//passo 4
        if(Objects.nonNull(kid.leftnNode))
            kid.leftnNode.parent = init;
        
        kid.parent = init.parent;//passo 5
        if(Objects.isNull(init.parent)){
            initial = kid;
        }
        else if(kid.reg.getkey() > init.parent.reg.getkey()){
            init.parent.rightnNode = kid;
        }
        else{
            init.parent.leftnNode = kid;
        }
        kid.leftnNode = init;
        init.parent = kid;


        aux = initial;
        return kid;
    }

    //search
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
    
    //print
    public void printTree(){
        Node node = aux;
        if(node.slpLeft)
            System.out.print(node.leftnNode.reg.getkey()+"\t"+node.reg.getkey());    
        else
            System.out.print("\t"+node.reg.getkey());
        if(node.slpRight)
            System.out.print("\t"+node.rightnNode.reg.getkey());
            System.out.println();
            
        if(!Objects.isNull(node.leftnNode) && !Objects.isNull(node.rightnNode)){
            if(!node.slpLeft)
                System.out.print(node.leftnNode.reg.getkey());
            if(!node.slpRight)
                System.out.print("\t\t"+node.rightnNode.reg.getkey());
            System.out.println();
            
            aux = node.leftnNode;
            printTree();
            aux = node.rightnNode;
            printTree();
        }
        else if(!Objects.isNull(node.rightnNode)){
            if(!node.slpRight)
                System.out.println("\t\t"+node.rightnNode.reg.getkey());
            aux = node.rightnNode;
            printTree();
        }
        else if(!Objects.isNull(node.leftnNode)){
            if(!node.slpLeft)
                System.out.println(node.leftnNode.reg.getkey());
            aux = node.leftnNode;
            printTree();
        }
        aux = initial;
    }
}