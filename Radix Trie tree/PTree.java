import java.util.Objects;
import java.io.PushbackInputStream;


public class PTree {
    private Node initial;
    private Node aux;
    private static int count = 0;

    public PTree(Word word){
        this.initial = new Node(word, 0, null);
        this.aux = initial;
    }

    private boolean isIgnored(Node node){
           return (Objects.nonNull(aux.getLeftChild())&&!Objects.nonNull(aux.getRightChild()) ||
                   (!Objects.nonNull(aux.getLeftChild())&&Objects.nonNull(aux.getRightChild())));
    }

    private void toP(){
        final boolean leftbol = Objects.nonNull(aux.getLeftChild());
        final boolean rightbol = Objects.nonNull(aux.getRightChild());
        Node child;

        if(leftbol && rightbol){ //caso geral
            if(isIgnored(aux.getLeftChild())){//trabalha com o node da esquerda até não precisar mais
                child = aux.getLeftChild().getChild();
                child.setParent(aux);
                aux.setLeftChild(child);
                toP();
            }
            else if(isIgnored(aux.getRightChild())){//trabalha com o node da direita até não precisar mais
                child = aux.getRightChild().getChild();
                child.setParent(aux);
                aux.setRightChild(child);
                toP();
            }
            else{//passa para os próximos
                child = aux.getRightChild();
                aux = aux.getLeftChild();
                toP();
                aux = child;
                toP();
            }
        }
        else if(Objects.isNull(aux.getParent())){//caso seja o primeiro node
            initial = aux.getChild();
            initial.setParent(null);
            aux = initial;
            toP();
        }
        else{
            return;
        }
        aux = initial;
    }

    private static int getc(PushbackInputStream input){//este método age como getc() de c/c++
        try {                                           //ele retorna o proximo caractere do arquivo
            return input.read();
        } catch (Exception e) {
            System.err.println("impossível continuar a leitura");
            return 0;
        }
    }

    private static void ungetc(int c,PushbackInputStream input){//este método age como ungetc() de c/c++
        if (c != -1) {                                          //ele volta o arquivo em um caractere
            try {
                input.unread(c);
            } catch (Exception e) {
                System.err.println("impossível voltar a leitura");
            }
        }
    }

    private static boolean isNumeric(int num){ return (num>= 48 && num <= 57); }

    private static boolean isSpace(int c){return c == 32 || c == '\n' || c == '\t' || c == '\r';}

    public static PTree readText(PushbackInputStream input){ //Este método recolherá as palavras no texto, montará a
        PTree tree = new PTree(new Word(""));           // arvore e a retornará montada para ser usada
        int state = 1;
        while (true){
            state = 1;
            String word = "";
            while (state != 4) {
                int c = getc(input);
                switch (state) {
                    case 1:
                        if (isSpace(c))     //espaço e afins são ignorados
                            state = 1;
                        else if (Character.isLetter((char) c) || isNumeric(c)) {    //letras e números são coletados
                            word += (char) c;                                   //e são enviados para continuar a coleta
                            state = 2;
                        }
                        else if(c > 32){        //caracteres diferentes de letras e números são tratados
                            ungetc(c, input);
                            state = 3;
                        }
                        else {
                            tree.toP();
                            return tree;    //caso fim de arquivo a arvore é retornada
                        }
                        break;
                    case 2:     //a coleta das palavras e números continua aqui
                        if (Character.isLetter((char) c) || isNumeric(c) || c == '_') {
                            word += (char) c;
                        } else {        //assim que encontrado algo que não estaria numa palavra a coleta é encerrada
                            ungetc(c, input);
                            state = 4;
                        }
                        break;
                    case 3:     //coleta de caracteres especiais
                        word+=(char)c;
                        state = 4;
                        break;
                }
            }

            Word w = new Word(word, count); //ao final a palavra/caractere coletador é passado para a classe Word
            Word w2 = tree.search(w);   // e procurado na arvore que esta sendo montada

            if(Objects.isNull(w2)){ //caso não exista é adicionado na arvore
                tree.add(w);
            }
            else{               //caso exista seus dados são atualizados
                tree.search(w2, count);
            }
            count ++;   //este contador determina a posição de cada palavra/caractere
            //pois aumenta ao achar uma palavra
        }
    }

    private void addChild(Node parent, Node child, int position){
        if(position == 1)
            parent.setRightChild(child);
        else
            parent.setLeftChild(child);
    }

    public void add(Word word){
        aux = initial;
        add(word, true);
    }

    private void add(Word word, boolean boo){
        final boolean leftbol = Objects.nonNull(aux.getLeftChild());
        final boolean rightbol = Objects.nonNull(aux.getRightChild());
        Node child;

        if((!leftbol && rightbol) ||                                    //ignorados
            (leftbol &&!rightbol)){       //operador de ou exclusivo
            if((word.getCode().charAt(aux.getBit()) == '0' && !leftbol)||(word.getCode().charAt(aux.getBit()) == '1' && !rightbol)){ //caso tenha que adicionar
                child = new Node(word,aux.getBit()+1, aux);
                aux.addNode(child);
            }
            else{   //caso tenha apenas que passar
                aux = aux.getChild();
                add(word, boo);
            }
        }
        else if(leftbol && rightbol){                                   //decisão
            if(word.getCode().charAt(aux.getBit()) == '0')
                aux = aux.getLeftChild();
            else
                aux = aux.getRightChild();
            add(word,boo);
        }
        else{                                                           //resposta
            Node auxParent = null;
            if(Objects.nonNull(aux.getParent())) {
                auxParent = aux.getParent();

                child = new Node(auxParent.getBit() + 1);
                child.setParent(auxParent);
                int direction = (aux.getWord().getCode().charAt(auxParent.getBit()) == '0')? 0: 1;
                addChild(auxParent,child,direction);    //adiciona o novo nó na posição de aux
                direction = (aux.getWord().getCode().charAt(auxParent.getBit()+1) == '0')? 0: 1;
                addChild(child, aux, direction);      //adiciona aux como filho de child na posição de seu código
            }
            else{
                child = new Node(0);
                child.setParent(null);
                initial = child;
                int direction = (aux.getWord().getCode().charAt(0) == '0')? 0: 1;
                addChild(child, aux, direction);
            }

            aux.setParent(child);   //set child como aux.parent

            aux = child;    //faz a recursão na nova estrutura da arvore
            add(word, boo);
        }
        aux = initial;
    }

    public Word search(Word word){
        aux = initial;
        return search(word, -1);
    }


    public Word search(Word word, int num){
        final boolean leftbol = Objects.nonNull(aux.getLeftChild());
        final boolean rightbol = Objects.nonNull(aux.getRightChild());

        if(leftbol && rightbol){            //decisão
            if(word.getCode().charAt(aux.getBit()) == '0')
                aux = aux.getLeftChild();
            else
                aux = aux.getRightChild();
            return search(word, num);
        }
        else if(!leftbol && !rightbol){     //reposta
            boolean equal = aux.getWord().equals(word);
            if(equal){
                if(num >(-1)){
                    aux.setPosition(num);
                }
                return aux.getWord();
            }
            return null;
        }
        else {                              // ignorados
            aux = aux.getChild();
            return search(word, num);
        }
    }
}