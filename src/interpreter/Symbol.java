package interpreter;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public class Symbol<T> {

    private final String type;
    private final T value;

    Symbol(String type, T value) {
        this.type = type;
        this.value = value;
    }

    String getType() {
        return type;
    }

    public T getValue() {
        return value;
    }
}
