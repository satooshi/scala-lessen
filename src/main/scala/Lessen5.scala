package main.scala

import collection.mutable

/**
 * Lessen 4.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * Tower of Hanoi
 */
object Lessen5 {
  def main(args: Array[String]) {
    val i = 4
    //val solver = new SimpleHanoiSolver
    val solver = new HanoiSolver
    solver.solve(i)
  }
}

/**
 * Simple solver.
 */
class SimpleHanoiSolver {
  /**
   * Solve tower of Hanoi.
   *
   * @param n Number of disks.
   */
  def solve(n: Int) {
    move(n, "A", "B", "C")
  }

  /**
   * Move disk from peg a to peg c through peg b.
   *
   * @param n Number of disks.
   * @param a Peg name from.
   * @param b Peg name through.
   * @param c Peg name to.
   */
  def move(n: Int, a: String, b: String, c: String) {
    if (n > 0) {
      move(n-1, a, c, b)
      println("move disk %d from %s to %s".format(n, a, c))
      move(n-1, b, a, c)
    }
  }
}

/**
 * Tower of Hanoi solver with pegs' state.
 */
class HanoiSolver {
  /**
   * Solve tower of Hanoi.
   *
   * @param n
   */
  def solve(n: Int) {
    val state = new State(n)

    state.printState()
    move(state, n, "A", "B", "C")
  }

  /**
   * Move disk from peg a to peg c through peg b.
   *
   * @param state Pegs' state.
   * @param n     Number of disks.
   * @param a     Peg name from.
   * @param b     Peg name through.
   * @param c     Peg name to.
   */
  private
  def move(state: State, n: Int, a: String, b: String, c: String) {
    if (n > 0) {
      move(state, n-1, a, c, b)
      val disk = state.move(a, c)
      state.printResult(disk, a, c)
      move(state, n-1, b, a, c)
    }
  }
}

/**
 * Hanoi state.
 *
 * @param n Number of disks.
 * @param pegs Pegs map.
 */
class State(n: Int, pegs: Map[String, mutable.Stack[Int]]) {
  var step = 0

  /**
   * Constructor.
   *
   * @param n Number of disks.
   */
  def this(n: Int) = {
    this(n, Map("A" -> mutable.Stack[Int](), "B" -> mutable.Stack[Int](), "C" -> mutable.Stack[Int]()))
    clear(n)
  }

  /**
   * Clear all disks from pegs and fill initial disks to peg "A"
   * @param n
   */
  def clear(n: Int) {
    step = 0
    pegs("A").clear()
    pegs("B").clear()
    pegs("C").clear()

    // fill initial disks to peg "A"
    (1 to n).reverse.foreach(pegs("A").push(_))
  }

  /**
   * Move disk from peg name a to peg name b.
   * @param a Peg name from
   * @param b Peg name to
   * @return
   */
  def move(a: String, b: String) = moveDisk(pegs(a), pegs(b))

  /**
   * Move disk.
   * @param from Peg from
   * @param to   Peg to
   * @return
   */
  private
  def moveDisk(from: mutable.Stack[Int], to: mutable.Stack[Int]) =
    if (to.isEmpty || from.head < to.head) {
      step += 1
      to.push(from.pop()).head
    }
    else throw new IllegalArgumentException

  /**
   * Print move result
   * @param disk
   * @param a
   * @param b
   */
  def printResult(disk: Int, a: String, b: String) {
    println("move disk %d from %s to %s".format(disk, a, b))
    printState()
  }

  /**
   * Print pegs' state
   */
  def printState() {
    println("[step %d]".format(step))
    println("A: " + pegs("A"))
    println("B: " + pegs("B"))
    println("C: " + pegs("C"))
    println("")
  }
}