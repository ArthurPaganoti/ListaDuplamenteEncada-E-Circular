public class ListaCircularDuplamenteEncadeada {

    Celula primeira;
    Celula ultima;
    int totalDeElementos = 0;

    public int tamanho() {
        return totalDeElementos;
    }

    public void AdicionaNoComeco(Object elemento) {
        if (totalDeElementos == 0) {
            Celula nova = new Celula(elemento);
            primeira = nova;
            ultima = nova;
        } else {
            Celula nova = new Celula(primeira, elemento);
            primeira.setAnterior(nova);
            primeira = nova;
        }
        totalDeElementos++;
    }

    public ListaEncadeadaCircular intercalarListasOrdenadas(ListaEncadeadaCircular lista1, ListaEncadeadaCircular lista2) {
        ListaEncadeadaCircular listaIntercalada = new ListaEncadeadaCircular();

        Celula celula1 = lista1.primeira;
        Celula celula2 = lista2.primeira;

        while (celula1 != null && celula2 != null) {
            int elemento1 = (int) celula1.getElemento();
            int elemento2 = (int) celula2.getElemento();

            if (elemento1 < elemento2) {
                listaIntercalada.AdicionaNoComeco(elemento1);
                celula1 = celula1.getProxima();
            } else {
                listaIntercalada.AdicionaNoComeco(elemento2);
                celula2 = celula2.getProxima();
            }
        }

        while (celula1 != null) {
            int elemento1 = (int) celula1.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento1);
            celula1 = celula1.getProxima();
        }

        while (celula2 != null) {
            int elemento2 = (int) celula2.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento2);
            celula2 = celula2.getProxima();
        }

        return listaIntercalada;
    }

    public ListaEncadeadaCircular copiarLista() {
        ListaEncadeadaCircular listaCopia = new ListaEncadeadaCircular();

        if (totalDeElementos == 0) {
            return listaCopia;
        }
        Celula atual = primeira;
        do {
            listaCopia.AdicionaNoComeco(atual.getElemento());
            atual = atual.getProxima();
        } while (atual != primeira);

        return listaCopia;
    }
}
