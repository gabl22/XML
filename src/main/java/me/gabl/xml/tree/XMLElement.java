package me.gabl.xml.tree;

import de.natrox.common.validate.Check;
import me.gabl.xml.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings("ClassCanBeRecord")
public final class XMLElement implements XMLItem.Element, Iterable<XMLItem> {

    private final String name;
    private final Map<String, String> attributes;
    private final List<XMLItem> items;

    XMLElement(String name, Map<String, String> attributes, List<XMLItem> items) {
        this.name = name;
        this.attributes = attributes;
        this.items = items;
    }

    public static Element.Builder builder(String name) {
        return new Builder(name);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public @Nullable String attribute(String key) {
        return this.attributes.getOrDefault(key, null);
    }

    @Override
    public Map<String, String> attributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public List<XMLItem> items() {
        return Collections.unmodifiableList(items);
    }

    @NotNull
    @Override
    public ListIterator<XMLItem> iterator() {
        return this.items.listIterator();
    }

    static final class Builder implements XMLItem.Element.Builder {

        private final Map<String, String> attributes;
        private final String name;
        private final List<XMLItem> items;

        Builder(String name) {
            Check.argCondition(name.contains(" "), "Name must not contain ' '.");
            Check.argCondition(name.contains(">"), "Name must not contain >.");
            Check.argCondition(StringUtil.startsWith(name, "xml", true), "Name must not start with 'xml'.");
            Check.argCondition(!(Character.isLetter(name.charAt(0)) || name.charAt(0) == '_'), "Name must start with a letter or an underscore.'.");
            this.name = name;
            this.attributes = new HashMap<>();
            this.items = new LinkedList<>();
        }

        @Override
        public Element.Builder addAttributes(Map<String, String> attributes) {
            for (Map.Entry<String, String> entry : attributes.entrySet())
                this.addAttribute(entry.getKey(), entry.getValue());
            return this;
        }

        @Override
        public Element.Builder addAttribute(String key, String value) {
            Check.argCondition(key.contains(" "), "Key must not contain ' '.");
            this.attributes.put(key, value);
            return this;
        }

        @Override
        public boolean containsAttributes() {
            return !this.attributes.isEmpty();
        }

        @Override
        public boolean containsAttribute(String key) {
            return this.attributes.containsKey(key);
        }

        @Override
        public Element.Builder addItems(List<XMLItem> items) {
            this.items.addAll(items);
            return this;
        }

        @Override
        public Element.Builder addItem(XMLItem item) {
            this.items.add(item);
            return this;
        }

        @Override
        public boolean containsItems() {
            return !this.items.isEmpty();
        }

        @Override
        public Element build() {
            return new XMLElement(this.name, this.attributes, this.items);
        }
    }
}
