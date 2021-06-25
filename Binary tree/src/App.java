public class App {
    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree(4000);
        BinaryTree tree2 = new BinaryTree(1000);
        tree.add(7000);
        tree.add(2000);
        tree.add(1000);
        tree.add(9000);
        tree.add(3000);
        tree.add(8000);
        tree.add(5000);
        tree.add(6000);

        tree2.add(2000);
        tree2.add(3000);
        tree2.add(4000);
        tree2.add(5000);
        tree2.add(6000);
        tree2.add(7000);
        tree2.add(8000);
        tree2.add(9000);

        long tempoInicial, tempoFinal;// = System.nanoTime();
        int o, f;
        for(int i=500; i<=9500; i+=1000){
            o=Item.cont;
            tempoInicial = System.nanoTime();
            tree.search(i);
            tempoFinal = System.nanoTime();
            f=Item.cont;
            System.out.println((tempoFinal-tempoInicial)+"  "+(f-o));
        }
        System.out.println("------------------");
        for(int i=500; i<=9500; i+=1000){
            o=Item.cont;
            tempoInicial = System.nanoTime();
            tree2.search(i);
            tempoFinal = System.nanoTime();
            f=Item.cont;
            System.out.println((tempoFinal-tempoInicial)+"  "+(f-o));
        }

    }
}
