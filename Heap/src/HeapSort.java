import java.util.ArrayList;
import java.util.Arrays;


public class HeapSort {
    private Heap heap;
    private ArrayList<Item> list;
    private int ordem = -1;
    private boolean decresce = true;

    public HeapSort(Item item){
        list = new ArrayList<Item>();
        list.add(item);
        ordem++;
        heap = new Heap(list.toArray(new Item[ordem]));
    }
    public HeapSort(){
        list = new ArrayList<Item>();
        heap = new Heap();
    }
    public HeapSort(Item [] vec){
        list = new ArrayList<>(Arrays.asList(vec));
        ordem= list.size()-1;
        heapSort((ordem-1)/2);
        heap = new Heap(list.toArray(new Item[ordem]));
    }

    public HeapSort(int reg){
        list = new ArrayList<Item>();
        list.add(new Item(reg));
        ordem++;
        heap = new Heap(list.toArray(new Item[ordem]));
    }

    public void setHeap(Item item){
        list.add(item);
        ordem++;
        heapSort((ordem-1)/2);
        heap.clearSetHeap(list.toArray(new Item[ordem]));
    }

    public void setHeap(int reg){
        list.add(new Item(reg));
        ordem++;
        heapSort((ordem-1)/2);
        heap.clearSetHeap(list.toArray(new Item[ordem]));
    }

    public Heap getHeap(){
        return heap;
    }
    
    private void atualista(ArrayList<Item>li){
        list.clear();
        list = li;
    }

    private void heapSort(int atual){
        int pMaior = (atual+1)*2, pMenor = atual*2+1;
        Item [] vec = new Item[ordem];
        vec = list.toArray(vec);
        int tamanho = vec.length;
        int situacao;

        if(pMenor >= tamanho){
            decresce = true;
            return;
        }

        if(pMaior < tamanho){
            int maior = (vec[pMaior].getkey() > vec[pMenor].getkey()) ? 
            pMaior:
            pMenor;
            situacao = vec[maior].compareTo(vec[atual]);// atual > p = -1, atual < p = 1
            if(situacao == 1){
                decresce = false;
                Item aux = vec[maior];
                vec[maior] = vec[atual];
                vec[atual] = aux;
                atualista(new ArrayList<>(Arrays.asList(vec)));
                heapSort(maior);
            }
            else if(!decresce){
                decresce = true;
                return;
            }
            if(atual != 0 && decresce)
                heapSort(atual-1);
        }
        else{
            situacao = vec[pMenor].compareTo(vec[atual]);// atual > p = -1, atual < p = 1
            if(situacao == 1){
                decresce = false;
                Item aux = vec[pMenor];
                vec[pMenor] = vec[atual];
                vec[atual] = aux;
                atualista(new ArrayList<>(Arrays.asList(vec)));
                heapSort(pMenor);
            }
            else if(!decresce){
                decresce = true;
                return;
            }
            if(atual != 0 && decresce)
                heapSort(atual-1);
        }

        decresce = true;        
    }

    
}


