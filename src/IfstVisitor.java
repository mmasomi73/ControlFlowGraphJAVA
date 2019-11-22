// Generated from D:/University/Uni/term-1/Compiler/Project/Phase-01/grammer\Ifst.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IfstParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IfstVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IfstParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(IfstParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#ifSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSt(IfstParser.IfStContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#elseSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseSt(IfstParser.ElseStContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBexp(IfstParser.BexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#bterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBterm(IfstParser.BtermContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#bfactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBfactor(IfstParser.BfactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(IfstParser.RelopContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#st}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSt(IfstParser.StContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(IfstParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(IfstParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link IfstParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(IfstParser.FactorContext ctx);
}