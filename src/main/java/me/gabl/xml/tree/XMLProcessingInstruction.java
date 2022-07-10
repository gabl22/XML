package me.gabl.xml.tree;

import de.natrox.common.validate.Check;

@SuppressWarnings("ClassCanBeRecord")
public final class XMLProcessingInstruction implements XMLItem.ProcessingInstruction {

    private final String target;
    private final String content;

    XMLProcessingInstruction(String target, String content) {
        this.target = target;
        this.content = content;
    }

    public static XMLProcessingInstruction of(String target, String content) {
        Check.argCondition(target.contains("?>"), "Target must not contain ?>.");
        Check.argCondition(target.contains(" "), "Target must not contain ' '.");
        Check.argCondition(content.contains("?>"), "Target must not contain ?>.");
        return new XMLProcessingInstruction(target, content);
    }

    @Override
    public String target() {
        return this.target;
    }

    @Override
    public String content() {
        return this.content;
    }
}
