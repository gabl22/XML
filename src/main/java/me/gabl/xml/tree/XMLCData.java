package me.gabl.xml.tree;

import de.natrox.common.validate.Check;

@SuppressWarnings("ClassCanBeRecord")
public final class XMLCData implements XMLItem.Text {

    private final String content;

    XMLCData(String content) {
        this.content = content;
    }

    public static XMLCData of(String content) {
        Check.argCondition(content.contains("]]>"), "CData must not contain ]]>.");
        return new XMLCData(content);
    }

    @Override
    public ItemType type() {
        return ItemType.CDATA;
    }

    @Override
    public String text() {
        return this.content;
    }
}
