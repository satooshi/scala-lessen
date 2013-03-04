package main.scala

/**
 * Lessen1.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * Hello World![改行]を5回表示させてください。
 *
 * print(或いはprintf,cout等)を5回コピーすれば当然可能ですが、
 * ループ構文(for,while等)を利用して、print等は1回の使用にとどめてみてください。
 */
object Lessen1 {
  def main(args: Array[String]) {
    //fiveTimes()
    //recursiveFiveTimes(5)
    forFiveTimes()
    //whileFiveTimes()
    //doWhileFiveTimes()
  }

  /**
   * Work but bad answer.
   */
  def fiveTimes() {
    println("Hello World!")
    println("Hello World!")
    println("Hello World!")
    println("Hello World!")
    println("Hello World!")
  }

  /**
   * 5 times recursion.
   *
   * @param i
   */
  def recursiveFiveTimes(i: Int) {
    if (0 < i && i <= 5) {
      recursiveFiveTimes(i - 1)
      println("Hello World!")
    }
  }

  /**
   * 5 times for loop.
   */
  def forFiveTimes() { for (i <- 1 to 5) println("Hello World!") }

  /**
   * 5 times while loop.
   */
  def whileFiveTimes() {
    var i = 1

    while (i <= 5) {
      println("Hello World!")
      i += 1
    }
  }

  /**
   * 5 times do-while loop.
   */
  def doWhileFiveTimes() {
    var i = 1

    do {
      println("Hello World!")
      i += 1
    } while (i <= 5)
  }
}
