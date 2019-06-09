package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public interface Token {

    Kind getKind();
    String getValue();
}
