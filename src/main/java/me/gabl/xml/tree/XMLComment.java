package me.gabl.xml.tree;

import de.natrox.common.validate.Check;

@SuppressWarnings("ClassCanBeRecord")
public final class XMLComment implements XMLItem.Text {

    private final String content;

    XMLComment(String content) {
        this.content = content;
    }

    public static XMLComment of(String content) {
        Check.argCondition(content.contains("-->"), "Comment must not contain -->.");
        return new XMLComment(content);
    }

    @Override
    public ItemType type() {
        return ItemType.COMMENT;
    }

    @Override
    public String text() {
        return this.content;
    }
}
