public class Item {
    private int key;

    public Item(int key) {
        this.key = key;
    }

    public int compareTo(Item it) {
        Item item = it;
        if (this.key < item.key) //to right
            return -1;
        else if (this.key > item.key) //to left
            return 1;
        return 0; //equal
    }

    public int getkey() {
        return key;
    }
}