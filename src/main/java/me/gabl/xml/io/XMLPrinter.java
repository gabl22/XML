package me.gabl.xml.io;

import me.gabl.xml.tree.*;
import me.gabl.xml.util.StringUtil;
import java.util.Map;

public class XMLPrinter {

    /*

     * THIS IS JUST AN IDEA/CONCEPT

     */

    public String parseUnknown(XMLItem item) {
        return switch (item.type()) {
            case CDATA -> this.parseCData(item);
            case COMMENT -> this.parseComment(item);
            case ELEMENT -> this.parseElement(item);
            case PROCESSING_INSTRUCTION -> this.parseProcessingInstruction(item);
            case RAW_TEXT -> this.parseRawText(item);
        };
    }

    private String parseCData(XMLItem item) {
        return "<![CDATA["+((XMLCData) item).text()+"]]>";
    }

    private String parseComment(XMLItem item) {
        return "<!-- " + ((XMLComment) item).text() + " -->";
    }

    private String parseElement(XMLItem item) {
        XMLElement element = (XMLElement) item;
        StringBuilder value = new StringBuilder()
                .append('<')
                .append(element.name());
        if (element.hasAttributes())
                value.append(' ')
                        .append(this.parseAttributes(element));
        if (!element.hasItems())
            return value.append(" />").toString();
        value.append('>');
        for (XMLItem xmlItem : element.items())
            value.append(this.parseUnknown(xmlItem));
        value.append("</")
                .append(element.name())
                .append('>');
        return value.toString();
    }

    private String parseAttributes(XMLElement element) {
        if (!element.hasAttributes())
            return "";
        StringBuilder value = new StringBuilder();
        for (Map.Entry<String, String>  attribute : element.attributes().entrySet())
            value.append(attribute.getKey())
                    .append(" = ")
                    .append(StringUtil.boxBestQuotes(attribute.getValue(), "&quot;"))
                    .append(' ');
        value.deleteCharAt(value.length() - 1);
        return value.toString();
    }

    private String parseProcessingInstruction(XMLItem item) {
        return "<?"+((XMLProcessingInstruction) item).target()+' '+((XMLProcessingInstruction) item).content()+"?>";
    }

    private String parseRawText(XMLItem item) {
        return ((XMLRawText) item).text();
    }
}
