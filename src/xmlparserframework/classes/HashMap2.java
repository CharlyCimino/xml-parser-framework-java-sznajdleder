package xmlparserframework.classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class HashMap2<T> {

    private HashMap<String, LinkedList<T>> tabla;
    private Collection<String> claves;

    public HashMap2() {
        tabla = new HashMap<>();
        claves = new LinkedList<>();
    }

    public void put(String key, T elm) {
        LinkedList<T> lst = tabla.get(key);
        if (lst == null) {
            lst = new LinkedList<>();
            tabla.put(key, lst);
            claves.add(key);
        }
        lst.addLast(elm);
    }

    public LinkedList<T> get(String key) {
        return tabla.get(key);
    }

    public Collection<String> keys() {
        return claves;
    }
}
