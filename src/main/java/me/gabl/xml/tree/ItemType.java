package me.gabl.xml.tree;

public enum ItemType {

    ELEMENT(XMLElement.class),
    RAW_TEXT(XMLRawText.class),
    COMMENT(XMLComment.class),
    PROCESSING_INSTRUCTION(XMLProcessingInstruction.class),
    CDATA(XMLCData.class);

    private final Class<? extends XMLItem> itemClass;

    ItemType(Class<? extends XMLItem> itemClass) {
        this.itemClass = itemClass;
    }

    public Class<? extends XMLItem> itemClass() {
        return this.itemClass;
    }
}
