import java.util.*;

public class Ordenamiento {

    public static void insercion_vidaActual_desendiente(ArrayList<Soldado> lista) {
        for (int i = 0; i < lista.size(); i++) {
            int posicion = i;
            Soldado tem = lista.get(i);
            while (posicion > 0 && lista.get(posicion - 1).getVidaActual() < lista.get(posicion).getVidaActual()) {

                lista.set(posicion, lista.get(posicion - 1));
                lista.set(posicion - 1, tem);
                posicion--;
            }
        }
    }

    public static void burbuja_vidaActual_desendiente(ArrayList<Soldado> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1; j++) {

                if (lista.get(j).getVidaActual() < lista.get(j + 1).getVidaActual()) {
                    Soldado tmp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, tmp);
                }
            }
        }

    }

}
