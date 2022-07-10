package me.gabl.xml.tree;

import de.natrox.common.validate.Check;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public final class XMLDocument {

    private final @Nullable XMLProcessingInstruction prolog;
    private final List<XMLItem> content;

    XMLDocument(@Nullable XMLProcessingInstruction prolog, List<XMLItem> content) {
        if (prolog != null)
            Check.stateCondition(!"xml".equals(prolog.target()), "Prolog must target xml.");
        this.prolog = prolog;
        this.content = content;
    }

    public XMLProcessingInstruction prolog() {
        return this.prolog;
    }

    public List<XMLItem> items() {
        return Collections.unmodifiableList(content);
    }
}
