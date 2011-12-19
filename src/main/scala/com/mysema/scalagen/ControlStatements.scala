package com.mysema.scalagen

import japa.parser.ast.CompilationUnit
import japa.parser.ast.body._
import japa.parser.ast.stmt._
import japa.parser.ast.expr._
import japa.parser.ast.visitor._
import java.util.{ ArrayList, Collections }

/**
 * @author tiwe
 *
 */
object ControlStatements extends ModifierVisitorAdapter[Context] with UnitTransformer {
  
  def transform(cu: CompilationUnit): CompilationUnit = {
    cu.accept(this, null).asInstanceOf[CompilationUnit] 
  }  
    
  override def visit(n: ForStmt, arg: Context) = {
    // transform
    //   for (int i = 0; i < x; i++) block 
    // into
    //   for (i <- 0 until x) block
    if (n.getInit != null && n.getInit.size == 1 && n.getInit.get(0).isInstanceOf[VariableDeclarationExpr]
     && n.getCompare.isInstanceOf[BinaryExpr] 
     && n.getCompare.asInstanceOf[BinaryExpr].getOperator.toString == "less"
     && n.getUpdate != null && n.getUpdate.size == 1 
     && n.getUpdate.get(0).isInstanceOf[UnaryExpr]
     && n.getUpdate.get(0).asInstanceOf[UnaryExpr].getOperator.toString == "posIncrement") {
      val init = n.getInit.get(0).asInstanceOf[VariableDeclarationExpr]
      val cmp = n.getCompare.asInstanceOf[BinaryExpr]
      var until = new MethodCallExpr(init.getVars.get(0).getInit, "until", Collections.singletonList(cmp.getRight))
      init.getVars.get(0).setInit(null)
      new ForeachStmt(init, until, n.getBody())
    } else {
      n  
    }    
  }
  
  override def visit(n: SwitchEntryStmt, arg: Context) = {    
    // remove break
    val size = if (n.getStmts == null) 0 else n.getStmts.size
    if (size > 1 && n.getStmts.get(size-1).isInstanceOf[BreakStmt]) {
      n.getStmts.remove(size-1)
    }
    n
  }
  
}