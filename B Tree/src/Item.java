public class Item implements Comparable<Item> {
    private int key;
    private static int cont = 0;

    public Item(int key) {
        this.key = key;
    }
    
    public int compareTo(Item it) {
        Item item = it;
        cont++;
        if (this.key < item.key) //to right
            return -1;
        else if (this.key > item.key) //to left
            return 1;
        return 0; //equal
    }

    public int getkey() {
        return key;
    }

    public static int getCont(){
        return cont;
    }

    public boolean equals(Item item){
        return key==item.getkey();
    }
}