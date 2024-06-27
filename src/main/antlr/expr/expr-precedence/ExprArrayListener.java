// Generated from .\ExprArray.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprArrayParser}.
 */
public interface ExprArrayListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprArrayParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(ExprArrayParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArrayParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(ExprArrayParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArrayParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprArrayParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArrayParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprArrayParser.ExprContext ctx);
}