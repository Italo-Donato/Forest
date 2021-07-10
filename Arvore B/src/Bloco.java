import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bloco implements Comparable<Bloco>{
    private int ordem;
    public boolean haveKids = false;
    public Bloco parent; //militei
    public List<Item> blockList;
    public List<Bloco> kidsList;


    public Bloco(Item item, int od, Bloco p){
        blockList = new ArrayList<>();
        kidsList = new ArrayList<>();
        blockList.add(item);
        ordem = od;
        parent = p;
        
    }
    public Bloco(int od, Bloco p){
        ordem = od;
        parent = p;
        kidsList = new ArrayList<>();
        blockList = new ArrayList<>();
    }

    public void addItem(final Item item) {
        if(!blockList.contains(item)){
            blockList.add(item);
            blockList = blockList.stream()
                .sorted()
                .collect(Collectors.toList());
        }
    }

    public void addKid(final Bloco bloco){
        kidsList.add(bloco);
        kidsList = kidsList.stream()
        .sorted()
        .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Bloco o) {
        return blockList.get(blockList.size()-1).compareTo(o.blockList.get(o.blockList.size()-1));
    }

    public int rank(Item item){
        int cont = 0;
        for(Item aux: blockList){
            int situation = aux.compareTo(item);
            if(situation == 0){
                return -1;
            }
            if(situation == 1)
                break;
            cont++;
        }
        return cont;
    }

    public int getOrdem(){
        return ordem;
    }
}
