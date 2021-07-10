public class App {
    public static void main(String[] args) throws Exception {
        Item []a = {new Item(40000), new Item(70000),new Item(20000),new Item(10000),new Item(90000),new Item(30000),new Item(100000), new Item(80000),new Item(50000),new Item(60000)};
        Item []c = {new Item(10000), new Item(20000),new Item(30000),new Item(40000),new Item(50000),new Item(60000),new Item(70000),new Item(80000),new Item(900000), new Item(100000)};
        Item []d = {new Item(100000), new Item(90000),new Item(80000),new Item(70000),new Item(60000),new Item(50000),new Item(40000),new Item(30000),new Item(20000), new Item(10000)};
        /*HeapSort aleatoria = new HeapSort(a);
        Item []c = {new Item(10000), new Item(20000),new Item(30000),new Item(40000),new Item(50000),new Item(60000),new Item(70000),new Item(80000),new Item(900000), new Item(100000)};
        HeapSort crescente = new HeapSort(c);
        Item []d = {new Item(100000), new Item(90000),new Item(80000),new Item(70000),new Item(60000),new Item(50000),new Item(40000),new Item(30000),new Item(20000), new Item(10000)};
        HeapSort decrescente = new HeapSort(d);
        decrescente.getHeap().printTree();*/
        HeapSort aleatoria = new HeapSort();
        HeapSort crescente = new HeapSort();
        HeapSort decrescente = new HeapSort();
        int i,f;
        int[] vet2 = {2,3,1,9,4,5,8,10,7,6};
        Item []v= new Item[10];
        for (int j = 0; j < 10; j++) {
            i = Item.cont;
            v[j]=new Item(vet2[j]*10000);
            aleatoria.setHeap(v[j]);
            f = Item.cont;
            System.out.println(f-i);
        }
        System.out.println("--------------");
        for(Item b: c){
            i = Item.cont;
            crescente.setHeap(b);
            f = Item.cont;
            //System.out.println(f-i);
        }
        System.out.println("--------------");
        for(Item b: d){
            i = Item.cont;
            decrescente.setHeap(b);
            f = Item.cont;
            //System.out.println(f-i);
        }
        //aleatoria.getHeap().printTree();
    }
}
