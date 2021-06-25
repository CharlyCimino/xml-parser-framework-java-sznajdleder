package xmlparserframework;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Test2 {

    public static void main(String[] args) {
        // leemos el archivo y lo cargamos en memoria
        XMLFactory.load("test.xml");
        // accedo al tag especificando su "ruta" y atributo
        String path = "/framework/data-access/mapping/table";
        String attName = "name";
        String attValue = "EMP";
        XTag tag = XMLFactory.getByAttribute(path, attName, attValue);
        // accedo a los valores de sus atributos
        String type = tag.getAtts().get("type");
        System.out.println(type);
        // accedo a los valores de sus subtags (field)
        XTag[] fields = tag.getSubtags("field");
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i]);
        }
    }
}
