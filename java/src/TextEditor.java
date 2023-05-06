package src;

// Implement a simple text editor with undo functionality using Stacks.
import java.util.Stack;

class TextEditor {
    private Stack<String> textStack;
    private StringBuilder currentText;

    public TextEditor() {
        textStack = new Stack<>();
        currentText = new StringBuilder();
    }

    public void type(String text) {
        textStack.push(currentText.toString());
        currentText.append(text);
    }

    public void undo() {
        if (!textStack.isEmpty()) {
            currentText = new StringBuilder(textStack.pop());
        }
    }

    public String getText() {
        return currentText.toString();
    }
}
