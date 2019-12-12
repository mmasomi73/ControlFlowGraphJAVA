public class Stack {

    private static final int MAX = 1000;
    private int top;
    private String[] a = new String[MAX]; // Maximum size of Stack

    boolean isEmpty()
    {
        return (top < 0);
    }
    public Stack()
    {
        top = -1;
    }

    boolean push(String x)
    {
        if (top >= (MAX - 1)) {
            return false;
        }
        else {
            a[++top] = x;
            return true;
        }
    }

    String pop()
    {
        if (top < 0) {
            return "0";
        }
        else {
            return a[top--];
        }
    }

    String peek()
    {
        if (top < 0) {
            return "0";
        }
        else {
            return a[top];
        }
    }
}
