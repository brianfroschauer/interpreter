package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public enum Kind {

    INTEGER,
    STRING,

    PLUS,
    MINUS,
    MUL,
    DIV,

    LPAREN,
    RPAREN,

    ASSIGN,
    SEMI,

    LET,
    ID,

    EOF
}
