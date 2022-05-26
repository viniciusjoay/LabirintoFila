public class Array {
    private int[] dados;

    Array(int tamanho) {
        this.dados = new int[tamanho];
    }

    public int tamanho() {
        return this.dados.length;
    }

    public int indexOf(int valor) {
        for (int i = 0; i < tamanho(); i++) {
            if(dados[i] == valor) return i;
        }
        return -1;
    }

    public String toString() {
        String borda = "[";
        if(tamanho() > 0) {
            borda += this.dados[0];
        }
        for (int i = 1; i < tamanho(); i++) {
            borda += ", " + this.dados[i];
        }
        borda += "]";
        return borda;
    }
}