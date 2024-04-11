package symtable;

import cymbol.CymbolBaseListener;
import cymbol.CymbolParser;

public class SymbolTableListener extends CymbolBaseListener {
  private GlobalScope globalScope = null;
  private Scope currentScope = null;

  private final SymbolTableTreeGraph graph = new SymbolTableTreeGraph();

  @Override
  public void enterProg(CymbolParser.ProgContext ctx) {
    globalScope = new GlobalScope(null);
    currentScope = globalScope;
  }

  @Override
  public void enterBlock(CymbolParser.BlockContext ctx) {
    LocalScope localScope = new LocalScope(currentScope);

    graph.addEdge(localScope.getName(), currentScope.getName());

    currentScope = localScope;
  }

  @Override
  public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
    String retType = ctx.type().getText();
    globalScope.resolve(retType);

    String funcName = ctx.ID().getText();

    FunctionSymbol func = new FunctionSymbol(funcName, currentScope);
    graph.addEdge(funcName, currentScope.getName());

    currentScope.define(func);
    currentScope = func;
  }

  /**
   * define symbols
   */
  @Override
  public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
    String typeName = ctx.type().getText();
    Type type = (Type) globalScope.resolve(typeName);

    String varName = ctx.ID().getText();
    VariableSymbol varSymbol = new VariableSymbol(varName, type);
    currentScope.define(varSymbol);
  }

  @Override
  public void exitFormalParameter(CymbolParser.FormalParameterContext ctx) {
    String typeName = ctx.type().getText();
    Type type = (Type) globalScope.resolve(typeName);

    String varName = ctx.ID().getText();

    VariableSymbol varSymbol = new VariableSymbol(varName, type);
    currentScope.define(varSymbol);
  }

  /**
   * use symbols
   */
  @Override
  public void exitId(CymbolParser.IdContext ctx) {
    String varName = ctx.ID().getText();
    currentScope.resolve(varName);
  }

  /**
   * Exit the current scope and return to its enclosing scope
   */
  @Override
  public void exitProg(CymbolParser.ProgContext ctx) {
    exitCurrentScope();
  }

  @Override
  public void exitBlock(CymbolParser.BlockContext ctx) {
    exitCurrentScope();
  }

  @Override
  public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
    exitCurrentScope();
  }

  private void exitCurrentScope() {
    graph.addNode(SymbolTableTreeGraph.toDot(currentScope));
    currentScope = currentScope.getEnclosingScope();
  }

  public SymbolTableTreeGraph getGraph() {
    return graph;
  }
}