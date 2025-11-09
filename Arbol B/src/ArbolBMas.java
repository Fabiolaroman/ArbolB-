public class ArbolBMas {
    private Nodo raiz;
    private int orden;


    public ArbolBMas(int orden) {
        this.orden = orden;
        this.raiz = new Nodo(true); // inicia como hoja
    }


    public void insertar(int clave) {
        Nodo nodo = raiz;
        if (nodo.listaClaves.size() == orden - 1) {
            Nodo nuevaRaiz = new Nodo(false);
            nuevaRaiz.listaHijos.add(raiz);
            dividirHijo(nuevaRaiz, 0, raiz);
            raiz = nuevaRaiz;
        }
        insertarNoLleno(raiz, clave);
    }


    private void insertarNoLleno(Nodo nodo, int clave) {
        if (nodo.esHoja) {
            nodo.insertarClave(clave);
        } else {
            int i = nodo.listaClaves.size() - 1;
            while (i >= 0 && clave < nodo.listaClaves.get(i)) {
                i--;
            }
            i++;
            Nodo hijo = nodo.listaHijos.get(i);
            if (hijo.listaClaves.size() == orden - 1) {
                dividirHijo(nodo, i, hijo);
                if (clave > nodo.listaClaves.get(i)) {
                    i++;
                }
            }
            insertarNoLleno(nodo.listaHijos.get(i), clave);
        }
    }


    private void dividirHijo(Nodo padre, int indice, Nodo hijo) {
        Nodo nuevo = hijo.dividir();
        int clavePromocionada = nuevo.listaClaves.get(0);
        padre.listaClaves.add(indice, clavePromocionada);
        padre.listaHijos.add(indice + 1, nuevo);
    }


    public boolean buscar(int clave) {
        return buscarRec(raiz, clave);
    }

    private boolean buscarRec(Nodo nodo, int clave) {
        int i = 0;
        while (i < nodo.listaClaves.size() && clave > nodo.listaClaves.get(i)) {
            i++;
        }
        if (i < nodo.listaClaves.size() && clave == nodo.listaClaves.get(i)) {
            return true;
        }
        if (nodo.esHoja) {
            return false;
        } else {
            return buscarRec(nodo.listaHijos.get(i), clave);
        }
    }


    public void eliminar(int clave) {
        eliminarRec(raiz, clave);
    }

    private void eliminarRec(Nodo nodo, int clave) {
        if (nodo.esHoja) {
            nodo.listaClaves.removeIf(k -> k == clave);
        } else {
            int i = 0;
            while (i < nodo.listaClaves.size() && clave > nodo.listaClaves.get(i)) {
                i++;
            }
            eliminarRec(nodo.listaHijos.get(i), clave);
        }
    }


    public void recorrer(int n) {
        Nodo actual = raiz;
        while (!actual.esHoja) {
            actual = actual.listaHijos.get(0);
        }
        int cont = 0;
        while (actual != null && cont < n) {
            for (int clave : actual.listaClaves) {
                System.out.print(clave + " ");
                cont++;
                if (cont >= n) break;
            }
            actual = actual.siguienteHoja;
        }
        System.out.println();
    }
}

