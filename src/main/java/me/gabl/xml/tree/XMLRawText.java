package me.gabl.xml.tree;

import de.natrox.common.validate.Check;

@SuppressWarnings("ClassCanBeRecord")
public final class XMLRawText implements XMLItem.Text {

    private final String content;

    XMLRawText(String content) {
        this.content = content;
    }

    public static XMLRawText of(String content) {
        Check.argCondition(content.contains("<"), "Text must not contain <.");
        return new XMLRawText(content);
    }

    @Override
    public ItemType type() {
        return ItemType.RAW_TEXT;
    }

    @Override
    public String text() {
        return this.content;
    }
}
