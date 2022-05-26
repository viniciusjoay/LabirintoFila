import java.util.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class LabirintoFila {

    Queue<Coordenada> queue = new LinkedList<>();
    Coordenada inicio = new Coordenada (1,6);
    Coordenada saida = new Coordenada (1,11);
    Coordenada atual = inicio;
    int tamanhoQueue;

    private final String [][] backupLabirinto = { //Matriz do labirinto | . = Parede |  = Caminho | A = Ponto Inicial | B = Saída
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", "A", ".", ".", ".", ".", "B", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", ".", " ", " ", " ", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", " ", " ", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", " ", " ", " ", ".", ".", ".", "."},
            {".", ".", ".", ".", " ", ".", ".", ".", ".", " ", ".", ".", "."},
            {".", ".", " ", " ", " ", ".", ".", ".", " ", " ", ".", ".", "."},
            {".", ".", " ", ".", " ", " ", " ", " ", ".", " ", " ", " ", "."},
            {".", " ", " ", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", ".", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", " ", ".", " ", " ", " ", " ", " ", ".", ".", " ", "."},
            {".", " ", ".", ".", " ", ".", " ", ".", " ", " ", " ", " ", "."},
            {".", ".", ".", " ", " ", ".", " ", ".", ".", ".", " ", ".", "."},
            {".", " ", " ", " ", ".", ".", " ", " ", ".", ".", " ", " ", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
    };
    private String [][] labirinto = { //Matriz do labirinto | . = Parede |  = Caminho | A = Ponto Inicial | B = Saída
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", "A", ".", ".", ".", ".", "B", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", ".", " ", " ", " ", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", " ", " ", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", " ", " ", " ", ".", ".", ".", "."},
            {".", ".", ".", ".", " ", ".", ".", ".", ".", " ", ".", ".", "."},
            {".", ".", " ", " ", " ", ".", ".", ".", " ", " ", ".", ".", "."},
            {".", ".", " ", ".", " ", " ", " ", " ", ".", " ", " ", " ", "."},
            {".", " ", " ", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", ".", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", " ", ".", " ", " ", " ", " ", " ", ".", ".", " ", "."},
            {".", " ", ".", ".", " ", ".", " ", ".", " ", " ", " ", " ", "."},
            {".", ".", ".", " ", " ", ".", " ", ".", ".", ".", " ", ".", "."},
            {".", " ", " ", " ", ".", ".", " ", " ", ".", ".", " ", " ", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
    };
    public void imprimeLabirinto() { //Imprime a matriz do labirinto
        for (String[] strings : labirinto) {
            String matrizLabirinto = Arrays.toString(strings);
            matrizLabirinto = matrizLabirinto.replace(",", " "); // Substitui , por espaços para melhor vizualização
            System.out.println(matrizLabirinto);
        }
    }

    public void resolveLabirinto() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        while (true){
            imprimeLabirinto();
            System.out.println("Programe seus passos para chegar no 'S'!");
            System.out.println("Comandos enfileirados: " + this.queue.size());
            System.out.println("-=-=-=-=-=-=| COMANDOS |=-=-=-=-=-=-");
            System.out.println("[ W ] - CIMA");
            System.out.println("[ S ] - BAIXO");
            System.out.println("[ A ] - ESQUERDA");
            System.out.println("[ D ] - DIREITA");
            System.out.println("[ V ] - VERIFICAR CAMINHO");
            System.out.println("[ R ] - RECOMECAR");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            String comando = scan.nextLine();
            comando = comando.toUpperCase().trim();
            if(comando.equals("W") || comando.equals("A") || comando.equals("S") || comando.equals("D")){
                Coordenada coord = validaMovimento(comando);
                    if(coord != null){
                        this.queue.add(coord);
                    }else{
                        System.out.println("Voce nao pode ir por esse caminho!");
                    }
            }else if(comando.equals("V")){
                boolean ganhou = start();
                if(ganhou){
                    System.out.println("Ganhou!");
                }else{
                    System.out.println("Perdeu!");
                }
            }else if(comando.equals("R")){
                queue.clear();
                labirinto = backupLabirinto;
                atual = inicio;
            }
        }
    }

    public Coordenada validaMovimento(String coordenada){
        Coordenada cima = new Coordenada(this.atual.x - 1 , this.atual.y);
        Coordenada baixo = new Coordenada(this.atual.x + 1 , this.atual.y);
        Coordenada esquerda = new Coordenada(this.atual.x, this.atual.y - 1);
        Coordenada direita = new Coordenada(this.atual.x , this.atual.y + 1);

        switch(coordenada){
            case "W":
                if (Objects.equals(this.labirinto[cima.x][cima.y], " ") || this.labirinto[cima.x][cima.y].equals("B")){
                    this.labirinto[cima.x][cima.y] = "*";
                    this.atual = cima;
                    return cima;
                }else{
                    return null;
                }
            case "S":
                if (Objects.equals(this.labirinto[baixo.x][baixo.y], " ") || this.labirinto[baixo.x][baixo.y].equals("B")){
                    this.labirinto[baixo.x][baixo.y] = "*";
                    this.atual = baixo;
                    return baixo;
                }else{
                    return null;
                }
            case "A":
                if (Objects.equals(this.labirinto[esquerda.x][esquerda.y], " ") || this.labirinto[esquerda.x][esquerda.y].equals("B")){
                    this.labirinto[esquerda.x][esquerda.y] = "*";
                    this.atual = esquerda;
                    return esquerda;
                }else{
                    return null;
                }
            case "D":
                if (Objects.equals(this.labirinto[direita.x][direita.y], " ") || this.labirinto[direita.x][direita.y].equals("B")){
                    this.labirinto[direita.x][direita.y] = "*";
                    this.atual = direita;
                    return direita;
                }else{
                    return null;
                }
        }
        return cima;
    }

    public boolean start() throws InterruptedException {
        this.tamanhoQueue = queue.size();
        for(int i = 0; i <= tamanhoQueue; i++){
            if(queue.size() == 1){
                Coordenada coord = queue.peek();
                if(coord.x == saida.x && coord.y == saida.y){
                    return true;
                }else{
                    return false;
                }
            }
            Coordenada coord = queue.peek();
            this.labirinto[coord.x][coord.y] = "M";
            queue.remove();
            imprimeLabirinto();
            System.out.println();
            MILLISECONDS.sleep(1000);
        }
        return false;
    }

}
