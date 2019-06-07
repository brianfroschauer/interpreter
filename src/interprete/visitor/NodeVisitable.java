package interprete.visitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public interface NodeVisitable {

    Integer accept(NodeVisitor visitor);
}
