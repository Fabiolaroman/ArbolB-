import java.util.ArrayList;
import java.util.List;


public class Nodo {
    List<Integer> listaClaves;
    List<Nodo> listaHijos;
    boolean esHoja;
    Nodo siguienteHoja;


    public Nodo(boolean esHoja) {
        this.esHoja = esHoja;
        this.listaClaves = new ArrayList<>();
        this.listaHijos = new ArrayList<>();
        this.siguienteHoja = null;
    }


    public void insertarClave(int clave) {
        int i = 0;
        while (i < listaClaves.size() && listaClaves.get(i) < clave) {
            i++;
        }
        listaClaves.add(i, clave);
    }


    public Nodo dividir() {
        int medio = listaClaves.size() / 2;
        Nodo nuevoNodo = new Nodo(esHoja);


        for (int i = medio; i < listaClaves.size(); i++) {
            nuevoNodo.listaClaves.add(listaClaves.get(i));
        }


        if (!esHoja) {
            for (int i = medio + 1; i < listaHijos.size(); i++) {
                nuevoNodo.listaHijos.add(listaHijos.get(i));
            }
        }


        if (esHoja) {
            nuevoNodo.siguienteHoja = this.siguienteHoja;
            this.siguienteHoja = nuevoNodo;
        }


        while (listaClaves.size() > medio) {
            listaClaves.remove(listaClaves.size() - 1);
        }


        while (!esHoja && listaHijos.size() > medio + 1) {
            listaHijos.remove(listaHijos.size() - 1);
        }

        return nuevoNodo;
    }
}
