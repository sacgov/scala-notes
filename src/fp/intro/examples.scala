package fp.intro

import scala.annotation.tailrec

object examples extends App {
  /**
   * Writing loops in fp involves writing tail recursive implementations
   */

  def factorial(n: Int): Int = {

    // checks if the implementation is not tail recursive
    @tailrec
    def factorialAccumulator(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else factorialAccumulator(n - 1, n * acc)
    }

    factorialAccumulator(n, 1)
  }

  /**
   * Write a recursive function to get the nth Fibonacci number (http://mng.bz/C29s). The first two Fibonacci numbers
   * are 0 and 1. The nth number is always the sum of the previous two—the sequence begins 0, 1, 1, 2, 3, 5.
   * Your definition should use a local tail-recursive function.
   */

  def fib(n: Int): Int = {
    @tailrec
    def fibAccumulator(n: Int, cur: Int, prev: Int): Int = {
      if (n == 0) prev
      else fibAccumulator(n - 1, cur + prev, cur)
    }

    fibAccumulator(n, 1, 0)
  }

  println((1 to 10).map(fib))

  /**
   * Variable-naming conventions
   * It’s a common convention to use names like f, g, and h for parameters to a higher- order function.
   * In functional programming, we tend to use very short variable names, even one-letter names.
   * This is usually because HOFs are so general that they have no opinion on what the argument should actually do.
   * All they know about the argument is its type. Many functional programmers feel that short names make code
   * easier to read, since it makes the structure of the code easier to see at a glance.
   */
  /**
   * We use higher order type functions with generics
   * We call them Polymorphic Function
   * Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
   * def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean
   */

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @tailrec
    def loop(n: Int): Boolean = {
      n < 2 || (ordered(as(n - 2), as(n - 1)) && loop(n - 1))
    }

    loop(as.length)
  }

  val isSortedArr = Array(isSorted[Int](
    Array(2833, 13856, 18235, 15386, 5844), _ < _),
    isSorted[Int](Array(1688, 13925, 15865, 13621, 17470), _ < _),
    isSorted[Int](Array(18624, 4294, 2467, 16540, 12828), _ < _),
    isSorted[Int](Array(9, 12299, 15021, 16974, 17731), _ < _),
    isSorted[Int](Array(3429, 4046, 5148, 12075, 13981), _ < _),
    isSorted[Int](Array(139, 1304, 6303, 10618, 11534), _ < _),
    isSorted[Int](Array(), _ < _),
    isSorted[Int](Array(139), _ < _)
  )
  println(isSortedArr.mkString("Array(", ", ", ")"))

}
