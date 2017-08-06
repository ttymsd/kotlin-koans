package i_introduction._8_Smart_Casts

import util.TODO
import util.doc8

// 'sealed' modifier restricts the type hierarchy:
// all the subclasses must be declared in the same file
sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
        when (e) {
            is Num -> todoTask8(e)
            is Sum -> todoTask8(e)
        }

fun todoTask8(expr: Expr): Int = when (expr) {
    is Num -> expr.value

    is Sum -> todoTask8(expr.left) + todoTask8(expr.right)

    else -> throw IllegalArgumentException("")
}

