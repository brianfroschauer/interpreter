package interpreter;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public interface NodeVisitable {

    void accept(NodeVisitor visitor);
}
