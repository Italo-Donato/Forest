import java.util.Objects;

public class ArvoreB {
    private Bloco initial;
    private Bloco auxBlock;
    private int ordem;
    static private int cont;

    public ArvoreB(Item item, int ordem){
        this.ordem = ordem;
        initial = new Bloco(item, ordem, null);
        auxBlock = initial;
    }

    private void preencheNovos(Bloco left, Bloco right){
        left.haveKids = auxBlock.haveKids;
        right.haveKids = auxBlock.haveKids;
        for(int i = 0; i< auxBlock.blockList.size(); i++){
            if(i < (int)auxBlock.blockList.size()/2){
                left.addItem(auxBlock.blockList.get(i));
            }
            else{
                right.addItem(auxBlock.blockList.get(i));
            }
        }
        if(!Objects.isNull(auxBlock.kidsList)){
            for(int i = 0; i< auxBlock.kidsList.size(); i++){
                if(i < (int)auxBlock.kidsList.size()/2){
                    left.addKid(auxBlock.kidsList.get(i));
                }
                else{
                    right.addKid(auxBlock.kidsList.get(i));
                }
            }
        }
    }

    private void cresce(){
        if(auxBlock.blockList.size() > ordem){
            Item aux = auxBlock.blockList.get((ordem/2)+1);

            auxBlock.blockList.remove(aux); //1

            if(!Objects.isNull(auxBlock.parent)){ //have pai
                Bloco auxParent = auxBlock.parent;//2
                auxParent.kidsList.remove(auxBlock); 

                auxParent.addItem(aux);//2.5

                Bloco left = new Bloco(ordem, auxParent);//3
                Bloco right = new Bloco(ordem, auxParent);

                preencheNovos(left, right);

                if(!Objects.isNull(auxBlock.kidsList)){
                    auxParent.addKid(left);//4
                    auxParent.addKid(right);
                }
                
                auxBlock = auxParent; //5
                cresce();
            }
            else{                //!have pai
                Bloco auxParent = new Bloco(aux, ordem,null);//1.5
                auxParent.haveKids = true;

                Bloco left = new Bloco(ordem, auxParent);//2
                Bloco right = new Bloco(ordem, auxParent);

                preencheNovos(left, right);

                if(!Objects.isNull(auxBlock.kidsList)){
                    auxParent.addKid(left);//3
                    auxParent.addKid(right);
                }
                initial = auxParent;
                auxBlock = initial;
            }
        }
        auxBlock = initial;
    }

    public void add(int reg){
        add(new Item(reg));
    }

    public void add(Item item){
        if(!auxBlock.haveKids){
            auxBlock.addItem(item);
            cresce();
            return;
        }

        int kid = auxBlock.rank(item);
        auxBlock = auxBlock.kidsList.get(kid);
        add(item);

        auxBlock = initial;
    }

    public boolean search(int reg){
        return search(new Item(reg));
    }

    public boolean search(Item item){
        cont++;
        int situation = auxBlock.rank(item);
        if(situation == -1){
            auxBlock = initial;
            return true;
        }
        if(auxBlock.haveKids){
            auxBlock = auxBlock.kidsList.get(situation);
            return search(item);
        }
        auxBlock = initial;
        return false;
    }

    public static int getCont(){
        return cont;
    }
}
