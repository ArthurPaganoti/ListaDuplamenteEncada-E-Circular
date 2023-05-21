public class ListaDupla {

    Celula primeira;
    Celula ultima;
    int totalDeElementos = 0;

    //Exercicio01
    //c-)
    public static ListaDupla intercalaListas(ListaDupla lista1, ListaDupla lista2) {
        ListaDupla listaIntercalada = new ListaDupla();
        Celula atual1 = lista1.getPrimeira();
        Celula atual2 = lista2.getPrimeira();

        while (atual1 != null && atual2 != null) {
            if ((int) atual1.getElemento() < (int) atual2.getElemento()) {
                listaIntercalada.Adiciona(atual1.getElemento());
                atual1 = atual1.getProxima();
            } else {
                listaIntercalada.Adiciona(atual2.getElemento());
                atual2 = atual2.getProxima();
            }
        }

        while (atual1 != null) {
            listaIntercalada.Adiciona(atual1.getElemento());
            atual1 = atual1.getProxima();
        }

        while (atual2 != null) {
            listaIntercalada.Adiciona(atual2.getElemento());
            atual2 = atual2.getProxima();
        }

        return listaIntercalada;
    }

    // Método para verificar se a posição está ocupada
    boolean PosicaoOcupada(int pos) {
        return ((pos >= 0) && (pos < this.totalDeElementos));
    }

    // Recuperar a célula numa determinada posição
    Celula PegaCelula(int pos) {
        if (!this.PosicaoOcupada(pos)) {
            throw new IllegalArgumentException("Posição não existe");
        } else {
            Celula atual = this.primeira;
            for (int i = 0; i < pos; i++) {
                atual = atual.getProxima();
            }
            return (atual);
        }
    }

    // Recuperar um objeto (informação) numa determinada posição
    Object Pega(int pos) {
        return (this.PegaCelula(pos).getElemento());
    }

    // Adicionar objetos (informações) no começo da lista
    void AdicionaNoComeco(Object elemento) {
        if (this.totalDeElementos == 0) {
            Celula nova = new Celula(elemento);
            this.primeira = nova;
            this.ultima = nova;
        } else {
            Celula nova = new Celula(this.primeira, elemento);
            this.primeira.setAnterior(nova);
            this.primeira = nova;
        }
        this.totalDeElementos++;
    }

    // Adicionar objetos (informações) na lista
    void Adiciona(Object elemento) {
        if (this.totalDeElementos == 0) {
            this.AdicionaNoComeco(elemento);
        } else {
            Celula nova = new Celula(elemento);
            this.ultima.setProxima(nova);
            nova.setAnterior(this.ultima);
            this.ultima = nova;
            this.totalDeElementos++;
        }
    }

    // Adiciona objetos (informações) na lista numa determinada posição
    void Adiciona(int pos, Object elemento) {
        if (pos == 0) {
            this.AdicionaNoComeco(elemento);
        } else if (pos == this.totalDeElementos) {
            this.Adiciona(elemento);
        } else {
            Celula anterior = this.PegaCelula(pos - 1);
            Celula proxima = anterior.getProxima();
            Celula nova = new Celula(anterior.getProxima(), elemento);
            nova.setAnterior(anterior);
            anterior.setProxima(nova);
            proxima.setAnterior(nova);
            this.totalDeElementos++;
        }
    }

    // Remove células no começo da lista
    void RemovedoComeco() {
        if (!this.PosicaoOcupada(0)) {
            throw new IllegalArgumentException("Posicao nao Existe");
        } else {
            this.primeira = this.primeira.getProxima();
            this.totalDeElementos--;
        }
        if (this.totalDeElementos == 0) {
            this.ultima = null;
        }
    }

    // Remove células no final da lista
    void RemovedoFim() {
        if (!this.PosicaoOcupada(totalDeElementos - 1)) {
            throw new IllegalArgumentException("Posicao nao existe");
        } else {
            if (this.totalDeElementos == 1) {
                this.RemovedoComeco();
            } else {
                Celula penultima = this.ultima.getAnterior();
                penultima.setProxima(null);
                this.ultima = penultima;
                this.totalDeElementos--;
            }
        }
    }

    // Remove células de uma determinada posição da lista
    void Remove(int pos) {
        if (!this.PosicaoOcupada(pos)) {
            throw new IllegalArgumentException("Posicao nao Existe");
        } else {
            if (pos == 0) {
                this.RemovedoComeco();
            } else if (pos == this.totalDeElementos - 1) {
                this.RemovedoFim();
            } else {
                Celula anterior = this.PegaCelula(pos - 1);
                Celula atual = anterior.getProxima();
                Celula proxima = atual.getProxima();
                anterior.setProxima(proxima);
                proxima.setAnterior(anterior);
                this.totalDeElementos--;
            }
        }
    }

    // Verifica se um determinado objeto está na lista
    boolean Contem(Object elemento) {
        Celula atual = this.primeira;
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return (true);
            }
            atual = atual.getProxima();
        }
        return (false);
    }

    // Verifica o tamanho da lista (quantidade de elementos)
    int tamanho() {
        return (this.totalDeElementos);
    }

    // Esvazia toda a lista
    void EsvaziaLista() {
        this.primeira = null;
        this.ultima = null;
        this.totalDeElementos = 0;
    }

    // Imprime a lista (Se os objetos forem do tipo String)
    public void Imprimir(ListaDupla lista) {
        if (this.totalDeElementos == 0) {
            System.out.println("[]");
        } else {
            StringBuilder builder = new StringBuilder("[");
            Celula atual = this.primeira;
            for (int i = 0; i < this.totalDeElementos - 1; i++) {
                builder.append(atual.getElemento());
                builder.append(", ");
                atual = atual.getProxima();
            }
            builder.append(atual.getElemento());
            builder.append("]");
            System.out.println(builder);
        }
    }

    // Exercicio 01
    // a-)
    public ListaDupla concatenaLista(ListaDupla lista) {
        ListaDupla concatena = new ListaDupla();
        if (lista.totalDeElementos == 0) {
            this.primeira = lista.primeira;
        } else if (lista.totalDeElementos != 0) {
            this.ultima.setProxima(lista.primeira);
            this.primeira.setAnterior(this.ultima);
        }
        this.ultima = lista.ultima;
        lista.totalDeElementos += this.totalDeElementos;
        lista.primeira = null;
        lista.ultima = null;
        lista.totalDeElementos = 0;
        return concatena;
    }

    // Exercicio01
    // b-)
    public ListaDupla separaListaAnterior(int pos) {
        ListaDupla novaLista = new ListaDupla();

        if (!PosicaoOcupada(pos)) {
            throw new IllegalArgumentException("Posição não existe");
        } else if (pos == 0) {
            novaLista.primeira = this.primeira;
            novaLista.ultima = this.ultima;
            novaLista.totalDeElementos = this.totalDeElementos;
            this.primeira = null;
            this.ultima = null;
            this.totalDeElementos = 0;
        } else {
            Celula anterior = PegaCelula(pos - 1);
            novaLista.primeira = anterior.getProxima();
            novaLista.ultima = this.ultima;
            novaLista.totalDeElementos = this.totalDeElementos - pos;
            this.ultima = anterior;
            this.totalDeElementos = pos;
            anterior.setProxima(null);
        }

        return novaLista;
    }

    private Celula getPrimeira() {
        return null;
    }

}