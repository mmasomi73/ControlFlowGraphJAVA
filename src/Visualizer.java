import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;


public class Visualizer {

    /**
     * The payload will either be the name of the parser rule, or the token
     * of a leaf in the tree.
     */
    private final Object payload;

    /**
     * All child nodes of this Visualizer.
     */
    private final List<Visualizer> children;

    public Visualizer(ParseTree tree) {
        this(null, tree);
    }

    private Visualizer(Visualizer ast, ParseTree tree) {
        this(ast, tree, new ArrayList<Visualizer>());
    }

    private Visualizer(Visualizer parent, ParseTree tree, List<Visualizer> children) {

        this.payload = getPayload(tree);
        this.children = children;

        if (parent == null) {
            // We're at the root of the Visualizer, traverse down the parse tree to fill
            // this Visualizer with nodes.
            walk(tree, this);
        }
        else {
            parent.children.add(this);
        }
    }

    public Object getPayload() {
        return payload;
    }

    public List<Visualizer> getChildren() {
        return new ArrayList<>(children);
    }

    // Determines the payload of this Visualizer: a string in case it's an inner node (which
    // is the name of the parser rule), or a Token in case it is a leaf node.
    private Object getPayload(ParseTree tree) {
        if (tree.getChildCount() == 0) {
            // A leaf node: return the tree's payload, which is a Token.
            return tree.getPayload();
        }
        else {
            // The name for parser rule `foo` will be `FooContext`. Strip `Context` and
            // lower case the first character.
            String ruleName = tree.getClass().getSimpleName().replace("Context", "");
            return Character.toLowerCase(ruleName.charAt(0)) + ruleName.substring(1);
        }
    }

    // Fills this Visualizer based on the parse tree.
    private static void walk(ParseTree tree, Visualizer ast) {

        if (tree.getChildCount() == 0) {
            // We've reached a leaf. We must create a new instance of an Visualizer because
            // the constructor will make sure this new instance is added to its parent's
            // child nodes.
            new Visualizer(ast, tree);
        }
        else if (tree.getChildCount() == 1) {
            // We've reached an inner node with a single child: we don't include this in
            // our Visualizer.
            walk(tree.getChild(0), ast);
        }
        else if (tree.getChildCount() > 1) {

            for (int i = 0; i < tree.getChildCount(); i++) {

                Visualizer temp = new Visualizer(ast, tree.getChild(i));

                if (!(temp.payload instanceof Token)) {
                    // Only traverse down if the payload is not a Token.
                    walk(tree.getChild(i), temp);
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        Visualizer ast = this;
        List<Visualizer> firstStack = new ArrayList<>();
        firstStack.add(ast);

        List<List<Visualizer>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<Visualizer> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            }
            else {
                ast = childStack.remove(0);
                String caption;

                if (ast.payload instanceof Token) {
                    Token token = (Token) ast.payload;
                    caption = String.format("TOKEN[type: %s, text: %s]",
                            token.getType(), token.getText().replace("\n", "\\n"));
                }
                else {
                    caption = String.valueOf(ast.payload);
                }

                String indent = "";

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                }

                builder.append(indent)
                        .append(childStack.isEmpty() ? "'- " : "|- ")
                        .append(caption)
                        .append("\n");

                if (ast.children.size() > 0) {
                    List<Visualizer> children = new ArrayList<>();
                    for (int i = 0; i < ast.children.size(); i++) {
                        children.add(ast.children.get(i));
                    }
                    childListStack.add(children);
                }
            }
        }

        return builder.toString();
    }

}