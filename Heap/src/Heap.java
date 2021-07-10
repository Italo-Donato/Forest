import java.util.Objects;

public class Heap {
    private int posicao = 1;
    private int ordem = 0;
    private int auxOrdem = 0;
    private Node initial;
    private Node aux;

    public Heap(Item [] vector){
        constroi(vector);
    }

    public Heap(){}
    
    private void constroi(Item [] vec){
        for(Item reg : vec){
            if(posicao == 1){
                initial = new Node(reg);
                aux = initial;
                posicao++;
            }
            else{
                add(reg);
            }
        }
    }

    public void clearSetHeap(Item [] vec){
        posicao = 1;
        ordem = 0;
        auxOrdem = 0;
        constroi(vec);
    }

    public void setHeap(Item reg){
        add(reg);
    }

    public void setHeap(Item [] vec){
        //vec.forEach(reg -> add(reg));
        for(Item reg: vec){
            add(reg);
        }
    }

    private void addPosicao(){
        posicao++;
        int pot = (int)Math.pow(2,(ordem+2));
        if(posicao >= pot){
            ordem++;
        }
        auxOrdem = ordem;
    }

    private Item add(Item reg){
        if(auxOrdem == 0){
            if((posicao & 1) == 0){
                aux.leftnNode = new Node(reg);
            }
            else{
                aux.rightnNode = new Node(reg);
            }
            aux = initial;
            addPosicao();
            return reg;
        }
        int direcao = (int)Math.pow(2,(auxOrdem));
        if((posicao & direcao)== direcao){
            aux = aux.rightnNode;
        }
        else{
            aux = aux.leftnNode;
        }
        auxOrdem--;
        return add(reg);
    }

    public void printTree(){
        Node node = aux;
        System.out.println("\t"+node.reg.getkey());
        if(!Objects.isNull(node.leftnNode) && !Objects.isNull(node.rightnNode)){
            System.out.println(node.leftnNode.reg.getkey()+"\t\t"+node.rightnNode.reg.getkey());
            aux = node.leftnNode;
            printTree();
            aux = node.rightnNode;
            printTree();
        }
        else if(!Objects.isNull(node.rightnNode)){
            System.out.println("\t\t"+node.rightnNode.reg.getkey());
            aux = node.rightnNode;
            printTree();
        }
        else if(!Objects.isNull(node.leftnNode)){
            System.out.println(node.leftnNode.reg.getkey());
            aux = node.leftnNode;
            printTree();
        }
        aux = initial;
    }
}
