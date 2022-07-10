package me.gabl.xml.tree;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

sealed interface XMLItem {

    ItemType type();

    default Class<? extends XMLItem> itemClass() {
        return this.type().itemClass();
    }

    sealed interface Text extends XMLItem permits XMLRawText, XMLComment, XMLCData {

        String text();
    }

    sealed interface Element extends XMLItem permits XMLElement {

        @Override
        default ItemType type() {
            return ItemType.ELEMENT;
        }

        String name();

        @Nullable String attribute(String key);

        Map<String, String> attributes();

        List<XMLItem> items();

        sealed interface Builder permits XMLElement.Builder {

            Builder addAttributes(Map<String, String> attributes);

            Builder addAttribute(String key, String value);

            boolean containsAttributes();

            boolean containsAttribute(String key);

            Builder addItems(List<XMLItem> items);

            Builder addItem(XMLItem item);

            boolean containsItems();

            Element build();
        }
    }

    sealed interface ProcessingInstruction extends XMLItem permits XMLProcessingInstruction {

        @Override
        default ItemType type() {
            return ItemType.PROCESSING_INSTRUCTION;
        }

        String target();

        String content();
    }
}
