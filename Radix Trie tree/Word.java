import java.util.ArrayList;
import java.util.List;

public class Word {
    
    // essa classe armazena as palavras do arquivo
    private String name;                // a palavra 
    private String code;                // código que era pra ser binário mas é string
    private List<Integer> positions;    // posições que tem as coordenadas das palavras no texto

    public Word(String name){
        this.name = name;
        this.positions = new ArrayList<Integer>();
        this.code = createCode(0);
    }
    public Word(String name, int position){
        this.name = name;
        this.positions = new ArrayList<Integer>();
        positions.add(position);
        this.code = createCode(0);
    }
    
    public void setName(String name){
        this.name = name;    
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }
    
    public List<Integer> getPositions(){
        return positions;
    }

    public void setPositions(int num){
        positions.add(num);
    }

    public void addPosition(Integer pos){
        positions.add(pos);
    }

    public boolean equals(Word w){
        return w.code.equals(code);
    }



    private String createCode(Integer var){
        if(var < 16){                   // numero limite de caracteres de cada palavra
            if(var < name.length()){    // enquanto houver caracteres
                String binary = Integer.toString((int)name.charAt(var), 2);   // catando os bagui binario
                while(binary.length() < 8){         // preenche com 0 se binary for menor que 8 bits
                    binary = "0" + binary;
                }
                return binary + createCode(var + 1);    // recursao e concatenação
            }
            else{
                return "00000000" + createCode(var + 1);    // nao tem caracteres, preenche com 0
            }
        }
        return "";      // encerra
    }
}
