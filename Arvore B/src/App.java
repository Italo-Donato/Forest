public class App {
    public static void main(String[] args) throws Exception {
        int contInit, contFinal, pagInit, pagFinal;
        ArvoreB tree2 = new ArvoreB(new Item(10000), 2);
        ArvoreB tree4 = new ArvoreB(new Item(10000), 4);
        ArvoreB tree6 = new ArvoreB(new Item(10000), 6);

        for(int i = 2; i<=10; i++ ){
            tree2.add(i*10000);
            tree4.add(i*10000);
            tree6.add(i*10000);
        }
        for(int i = 0; i<=10;i++){
            pagInit = ArvoreB.getCont();
            contInit = Item.getCont();
            tree6.search(i*10000+5000);
            contFinal = Item.getCont();
            pagFinal = ArvoreB.getCont();
            //System.out.println(contFinal-contInit);
            System.out.println(pagFinal-pagInit);
            

            /*pagInit = ArvoreB.getCont();
            contInit = Item.getCont();
            tree4.search(i*10000+5000);
            contFinal = Item.getCont();
            pagFinal = ArvoreB.getCont();
            System.out.println(contFinal-contInit);
            //System.out.println(pagInit-pagFinal);
            

            pagInit = ArvoreB.getCont();
            contInit = Item.getCont();
            tree4.search(i*10000+5000);
            contFinal = Item.getCont();
            pagFinal = ArvoreB.getCont();
            System.out.println(contFinal-contInit);
            //System.out.println(pagInit-pagFinal);*/
        }
    }
}
