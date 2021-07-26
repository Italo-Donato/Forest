import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.PushbackInputStream;


public class Main{
    public static void main(String args[]){
        PushbackInputStream input;
        try {
            input = new PushbackInputStream(new FileInputStream("teste"));
            PTree tree = PTree.readText(input);
            tree.search(new Word("computacao")).getPositions().forEach( i ->System.out.println(i));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

    }
}