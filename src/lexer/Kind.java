package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public enum Kind {

    NUMBER,
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

    PRINT,

    EOF
}
