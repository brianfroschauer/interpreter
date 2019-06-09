package interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
class SymbolTable {

    private final Map<String, Integer> symbolTable;

    SymbolTable() {
        this.symbolTable = new HashMap<>();
    }

    void define(String varName, Integer value) {
        symbolTable.put(varName, value);
    }

    Integer lookup(String varName) {
        return symbolTable.get(varName);
    }

    boolean isDefined(String varName) {
        return symbolTable.containsKey(varName);
    }
}
