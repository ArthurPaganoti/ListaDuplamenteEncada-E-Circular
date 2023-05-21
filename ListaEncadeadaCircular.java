public class ListaEncadeadaCircular {

    Celula primeira;
    Celula ultima;
    int totalDeElementos = 0;

    // Exercicio03
    // a-)
    int tamanho() {
        return totalDeElementos;
    }

    // Exercicio03
    // b-)
    void AdicionaNoComeco(Object elemento) {
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

    // Exercicio03
    // c-)
    public void concatenaLista(ListaEncadeadaCircular lista) {
        if (lista.totalDeElementos == 0) {
            return; // Lista vazia, não há nada para concatenar
        }

        if (totalDeElementos == 0) {
            primeira = lista.primeira;
            ultima = lista.ultima;
        } else {
            Celula ultimaAtual = ultima;
            Celula primeiraLista = lista.primeira;

            ultimaAtual.setProxima(primeiraLista);
            primeiraLista.setAnterior(ultimaAtual);

            Celula primeiraAtual = primeira;
            Celula ultimaLista = lista.ultima;

            ultimaLista.setProxima(primeiraAtual);
            primeiraAtual.setAnterior(ultimaLista);

            ultima = lista.ultima;
        }

        totalDeElementos += lista.totalDeElementos;

        lista.primeira.setAnterior(ultima);
        lista.ultima.setProxima(primeira);

        lista.primeira = null;
        lista.ultima = null;
        lista.totalDeElementos = 0;
    }

    // Exercicio03
    // d-)
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

        // Se ainda houver elementos na lista1, adiciona todos à lista intercalada
        while (celula1 != null) {
            int elemento1 = (int) celula1.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento1);
            celula1 = celula1.getProxima();
        }

        // Se ainda houver elementos na lista2, adiciona todos à lista intercalada
        while (celula2 != null) {
            int elemento2 = (int) celula2.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento2);
            celula2 = celula2.getProxima();
        }

        return listaIntercalada;
    }

    // Exercicio03
    // e-)
    public ListaEncadeadaCircular copiarLista() {
        ListaEncadeadaCircular listaCopia = new ListaEncadeadaCircular();

        if (totalDeElementos == 0) {
            return listaCopia; // Lista vazia, retorna a lista copiada vazia
        }

        Celula atual = primeira;
        do {
            listaCopia.AdicionaNoComeco(atual.getElemento());
            atual = atual.getProxima();
        } while (atual != primeira);

        return listaCopia;
    }
}