import java.util.Objects;

public class Node {

    private int bit;
    //indica qual o bit correspondente da palavra

    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private Word word;

    public Node (int bit)
    {
        this.bit = bit;
    }

    public Node (Word word, int bit, Node p){
        this.word = word;
        parent = p;
        this.leftChild = null;
        this.rightChild = null;
        this.bit = bit;
    }

    public Node (int bit, Node leftChild, Node rightChild)
    {
        this.bit = bit;
        this.leftChild = leftChild;
        this.rightChild = leftChild;
    }

    public void setLeftChild(Node leftChild)
    {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild)
    {
        this.rightChild = rightChild;
    }

    public void addNode(Node node){

        if (this.leftChild == null)
            setLeftChild(node);
        else if (this.rightChild == null)
            setRightChild(node);
        else
            System.out.println("ERRO: Nó já possui dois filhos");

    }

    public Node getChild(){
        final boolean leftbol = Objects.nonNull(this.leftChild);
        final boolean rightbol = Objects.nonNull(this.rightChild);

        if((!leftbol &&!rightbol) || //operador de coincidencia
                (leftbol && rightbol)){
            return null;
        }
        else if (leftbol)
            return leftChild;
        else
            return rightChild;
    }

    public Node getParent()
    {
        return this.parent;
    }

    public void setParent(Node p)
    {
        parent = p;
    }

    public void addWord (Word word)
    {
        this.word = word;
    }

    public Word getWord()
    {
        return this.word;
    }
    public void setWord(Word w)
    {
        this.word = w;
    }

    public void setPosition(int num){
        word.setPositions(num);
    }

    public Node getRightChild()
    {
        return (this.rightChild);
    }

    public Node getLeftChild()
    {
        return (this.leftChild);
    }

    public Integer getBit(){
        return bit;
    }

}