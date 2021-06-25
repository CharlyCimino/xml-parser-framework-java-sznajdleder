package xmlparserframework;

import xmlparserframework.classes.XMLTag;
import xmlparserframework.classes.XMLFactory;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Test {

    public static void main(String[] args) {
        // leemos el archivo y lo cargamos en memoria
        XMLFactory.load("test.xml");
        // accedo al tag especificando su "ruta"
        String path = "/framework/data-access/connection-pool";
        XMLTag tag = XMLFactory.getByPath(path);
        // accedo a los valores de los atributos
        String usr = tag.getAtts().get("usr");
        String pwd = tag.getAtts().get("pwd");
        String url = tag.getAtts().get("url");
        String driver = tag.getAtts().get("driver");
        String sMinsize = tag.getAtts().get("minsize");
        String sMaxsize = tag.getAtts().get("maxsize");
        String sSteep = tag.getAtts().get("steep");
        System.out.println(usr);
        System.out.println(pwd);
        System.out.println(url);
        System.out.println(driver);
        System.out.println(sMinsize);
        System.out.println(sMaxsize);
        System.out.println(sSteep);
    }

}
