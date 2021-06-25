package xmlparserframework;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class XTag {

    private String name;
    private HashMap<String, String> atts;
    private HashMap2<XTag> subtags;

    public XTag(String qname, HashMap<String, String> atts) {
        this.name = qname;
        this.atts = atts;
        this.subtags = new HashMap2<>();
    }

    public void addSubtag(XTag t) {
        subtags.put(t.getName(), t);
    }

    public XTag getSubtag(String name) {
        String auxName;
        // si el pathname es absoluto lo convertimos en relativo
        if (name.startsWith("/" + this.name + "/")) {
            auxName = name.substring(this.name.length() + 2);
        } else {
            auxName = name;
        }
        String[] tokens = auxName.split("/");
        XTag dum = this;
        for (int i = 0; i < tokens.length; i++) {
            dum = dum.subtags.get(tokens[i]).getFirst();
        }
        return dum;
    }

    public XTag[] getSubtags(String name) {
        String auxName;
        String auxName2;
        int idx = name.lastIndexOf('/');
        LinkedList<XTag> hijos;
        if (idx > 0) {
            auxName = name.substring(0, idx);
            auxName2 = name.substring(idx + 1);
            XTag tag = getSubtag(auxName);
            hijos = tag.subtags.get(auxName2);
        } else {
            hijos = subtags.get(name);
        }
        XTag[] t = new XTag[hijos.size()];
        for (int i = 0; i < hijos.size(); i++) {
            t[i] = hijos.get(i);
        }
        return t;
    }

    public XTag getSubTagByAttribute(String path, String attName, String value) {
        XTag[] tags = getSubtags(path);
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].atts.get(attName).equals(value)) {
                return tags[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String x = name + " (";
        int i = 0;
        Collection<String> coll = atts.keySet();
        for (String a : coll) {
            x += a + "=" + atts.get(a) + (i++ < atts.size() - 1 ? "," : ") ");
        }
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((XTag) obj).name);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getAtts() {
        return atts;
    }  

}
