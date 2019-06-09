package parser;

import interpreter.NodeVisitable;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public interface ASTNode extends NodeVisitable {

    String getValue();
}
