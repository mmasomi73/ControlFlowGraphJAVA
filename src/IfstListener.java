// Generated from D:/University/Uni/term-1/Compiler/Project/Phase-01/grammer\Ifst.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IfstParser}.
 */
public interface IfstListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IfstParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IfstParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IfstParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#ifSt}.
	 * @param ctx the parse tree
	 */
	void enterIfSt(IfstParser.IfStContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#ifSt}.
	 * @param ctx the parse tree
	 */
	void exitIfSt(IfstParser.IfStContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#elseSt}.
	 * @param ctx the parse tree
	 */
	void enterElseSt(IfstParser.ElseStContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#elseSt}.
	 * @param ctx the parse tree
	 */
	void exitElseSt(IfstParser.ElseStContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#bexp}.
	 * @param ctx the parse tree
	 */
	void enterBexp(IfstParser.BexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#bexp}.
	 * @param ctx the parse tree
	 */
	void exitBexp(IfstParser.BexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#bterm}.
	 * @param ctx the parse tree
	 */
	void enterBterm(IfstParser.BtermContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#bterm}.
	 * @param ctx the parse tree
	 */
	void exitBterm(IfstParser.BtermContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#bfactor}.
	 * @param ctx the parse tree
	 */
	void enterBfactor(IfstParser.BfactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#bfactor}.
	 * @param ctx the parse tree
	 */
	void exitBfactor(IfstParser.BfactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterRelop(IfstParser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitRelop(IfstParser.RelopContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#st}.
	 * @param ctx the parse tree
	 */
	void enterSt(IfstParser.StContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#st}.
	 * @param ctx the parse tree
	 */
	void exitSt(IfstParser.StContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IfstParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IfstParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(IfstParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(IfstParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link IfstParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(IfstParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IfstParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(IfstParser.FactorContext ctx);
}