package main.scala

import collection.mutable.Stack
import collection.immutable.Map

/**
 * Lessen 4.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * Tower of Hanoi
 */
object Lessen4 {
  def main(args: Array[String]) {
    val i = 20
    //val solver = new SimpleHanoiSolver
    val solver = new HanoiSolver
    solver.solve(i)
  }
}

class SimpleHanoiSolver {
  def solve(n: Int) {
    move(n, "A", "B", "C")
  }

  def move(n: Int, a: String, b: String, c: String) {
    if (n > 0) {
      move(n-1, a, c, b)
      println("move disk %d from %s to %s".format(n, a, c))
      move(n-1, b, a, c)
    }
  }
}

class HanoiSolver {
  def solve(n: Int) {
    val state = new State(n)

    state.printState()
    move(state, n, "A", "B", "C")
  }

  private
  def move(state: State, n: Int, a: String, b: String, c: String) {
    if (n > 0) {
      move(state, n-1, a, c, b)
      state.move(a, c)
      move(state, n-1, b, a, c)
    }
  }
}

class State(n: Int, pegs: Map[String, Stack[Int]]) {
  var step = 0

  def this(n: Int) = {
    this(n, Map("A" -> Stack[Int](), "B" -> Stack[Int](), "C" -> Stack[Int]()))
    clear(n)
  }

  /**
   * Clear all disks from pegs and fill initial disks to peg "A"
   * @param n
   */
  def clear(n: Int) {
    pegs("A").clear()
    pegs("B").clear()
    pegs("C").clear()

    // fill initial disks to peg "A"
    for (i <- (1 to n).reverse) pegs("A").push(i)
  }

  def move(a: String, b: String) {
    val disk = moveDisk(pegs(a), pegs(b))

    if (disk != 0) {
      printResult(disk, a, b)
    }
  }

  private
  def moveDisk(from: Stack[Int], to: Stack[Int]): Int = {
    if (to.length == 0 || from.head < to.head) to.push(from.pop()).head
    // exception is better
    // but simply return 0
    else 0
  }

  private
  def printResult(disk: Int, a: String, b: String) {
    println("move disk %d from %s to %s".format(disk, a, b))
    step += 1
    printState()
  }

  def printState() {
    println("[step %d]".format(step))
    println("A: " + pegs("A"))
    println("B: " + pegs("B"))
    println("C: " + pegs("C"))
    println("")
  }
}
