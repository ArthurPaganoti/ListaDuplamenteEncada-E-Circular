public class ListaEncadeadaCircularNoCabeca {

    Celula cabeca;
    int totalDeElementos;

    public ListaEncadeadaCircularNoCabeca() {
        cabeca = new Celula(null);
        cabeca.setProxima(cabeca);
        cabeca.setAnterior(cabeca);
        totalDeElementos = 0;
    }

    // Exercicio03
    // a-)
    public int tamanho() {
        return totalDeElementos;
    }

    // Exercicio03
    // b-)
    public void AdicionaNoComeco(Object elemento) {
        Celula nova = new Celula(elemento);
        nova.setProxima(cabeca.getProxima());
        nova.setAnterior(cabeca);
        cabeca.getProxima().setAnterior(nova);
        cabeca.setProxima(nova);
        totalDeElementos++;
    }

    // Exercicio03
    // c-)
    public void concatenaLista(ListaEncadeadaCircularNoCabeca lista) {
        if (lista.totalDeElementos == 0) {
            return;
        }

        Celula ultimaAtual = cabeca.getAnterior();
        Celula primeiraLista = lista.cabeca.getProxima();

        ultimaAtual.setProxima(primeiraLista);
        primeiraLista.setAnterior(ultimaAtual);

        Celula primeiraAtual = cabeca.getProxima();
        Celula ultimaLista = lista.cabeca.getAnterior();

        ultimaLista.setProxima(primeiraAtual);
        primeiraAtual.setAnterior(ultimaLista);

        cabeca.setAnterior(lista.cabeca.getAnterior());
        lista.cabeca.getAnterior().setProxima(cabeca);

        totalDeElementos += lista.totalDeElementos;

        lista.cabeca.setProxima(lista.cabeca);
        lista.cabeca.setAnterior(lista.cabeca);
        lista.totalDeElementos = 0;
    }

    // Exercicio03
    // d-)
    public ListaEncadeadaCircularNoCabeca intercalarListasOrdenadas(ListaEncadeadaCircularNoCabeca lista1, ListaEncadeadaCircularNoCabeca lista2) {
        ListaEncadeadaCircularNoCabeca listaIntercalada = new ListaEncadeadaCircularNoCabeca();

        Celula celula1 = lista1.cabeca.getProxima();
        Celula celula2 = lista2.cabeca.getProxima();

        while (celula1 != lista1.cabeca && celula2 != lista2.cabeca) {
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

        while (celula1 != lista1.cabeca) {
            int elemento1 = (int) celula1.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento1);
            celula1 = celula1.getProxima();
        }
        while (celula2 != lista2.cabeca) {
            int elemento2 = (int) celula2.getElemento();
            listaIntercalada.AdicionaNoComeco(elemento2);
            celula2 = celula2.getProxima();
        }
        return listaIntercalada;
    }

    // Exercicio03
    // e-)
    public ListaEncadeadaCircularNoCabeca copiarLista() {
        ListaEncadeadaCircularNoCabeca listaCopia = new ListaEncadeadaCircularNoCabeca();

        if (totalDeElementos == 0) {
            return listaCopia;
        }

        Celula atual = cabeca.getProxima();
        while (atual != cabeca) {
            listaCopia.AdicionaNoComeco(atual.getElemento());
            atual = atual.getProxima();
        }

        return listaCopia;
    }
}