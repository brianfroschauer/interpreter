package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public enum Kind {

    NUMBER_LITERAL,
    STRING_LITERAL,

    NUMBER_DATATYPE,
    STRING_DATATYPE,

    PLUS,
    MINUS,
    MUL,
    DIV,

    LPAREN,
    RPAREN,

    ASSIGN,
    SEMICOLON,
    COLON,

    LET,
    ID,

    PRINT,

    EOF
}
