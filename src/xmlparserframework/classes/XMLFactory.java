package xmlparserframework.classes;

import java.util.HashMap;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class XMLFactory extends DefaultHandler {

    private static XMLFactory instancia = null;
    private Stack<XMLTag> pila;
    private XMLTag raiz;

    private XMLFactory() {
        pila = new Stack<XMLTag>();
    }

    public static XMLTag getByPath(String path) {
        return instancia.raiz.getSubtag(path);
    }
    
    public static XMLTag getByAttribute(String path, String attname, String value) {
        return instancia.raiz.getSubTagByAttribute(path,attname,value);
    }

    public static void load(String xmlfilename) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            instancia = new XMLFactory();
            sp.parse(xmlfilename, instancia);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        HashMap<String, String> atts = _cloneAttributes(attributes);
        XMLTag t = new XMLTag(name, atts);
        if (pila.isEmpty()) {
            raiz = t;
        }
        pila.push(t);
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        if (pila.size() > 1) {
            XMLTag hijo = pila.pop();
            XMLTag padre = pila.pop();
            padre.addSubtag(hijo);
            pila.push(padre);
        }
    }

    private HashMap<String, String> _cloneAttributes(Attributes attributes) {
        HashMap<String, String> atts = new HashMap<>();
        for (int i = 0; i < attributes.getLength(); i++) {
            atts.put(attributes.getQName(i), attributes.getValue(i));
        }
        return atts;
    }

}
