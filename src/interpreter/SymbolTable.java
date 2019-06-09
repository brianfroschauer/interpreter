package interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
class SymbolTable {

    private final Map<String, Symbol> symbolTable;

    SymbolTable() {
        this.symbolTable = new HashMap<>();
    }

    void define(String varName, Symbol symbol) {
        symbolTable.put(varName, symbol);
    }

    Symbol lookup(String varName) {
        return symbolTable.get(varName);
    }

    boolean isDefined(String varName) {
        return symbolTable.containsKey(varName);
    }
}
