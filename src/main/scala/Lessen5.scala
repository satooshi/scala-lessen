package main.scala

import collection.mutable

/**
 * Lessen5.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * Tower of Hanoi
 */
object Lessen5 {
  def main(args: Array[String]) {
    val i = 3
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
  private
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
    println(state.num)
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
 * @param num  Number of disks.
 * @param pegs Pegs map.
 */
class State(val num: Int, val pegs: Map[String, mutable.Stack[Int]]) extends PegsContainer {
  var step = 0

  /**
   * Constructor.
   *
   * @param num Number of disks.
   */
  def this(num: Int) {
    this(num, Map("A" -> mutable.Stack[Int](), "B" -> mutable.Stack[Int](), "C" -> mutable.Stack[Int]()))
    clear(num)
  }

  /**
   * Clear all disks from pegs and fill initial disks to peg "A".
   *
   * @param num Number of disks
   */
  def clear(num: Int) {
    step = 0

    foreachPegName(pegs(_).clear())

    // fill initial disks to peg "A"
    (1 to num).reverse.foreach(pegs("A").push(_))
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
    foreachPegName(name => println("%s: %s".format(name, pegs(name))))
    println("")
  }
}

trait PegsContainer {
  def foreachPegName(func: String => Unit) { List("A", "B", "C").foreach(func) }
}

class ConsolePrinter(state: State) extends PegsContainer {
  def print() {

  }

  def printResult(disk: Int, step:Int, a: String, b: String) {
    println("move disk %d from %s to %s".format(disk, a, b))
    printState(step)
  }

  def printState(step: Int) {
    println("[step %d]".format(step))
    foreachPegName(name => println("%s: %s".format(name, state.pegs(name))))
    println("")
  }
}
