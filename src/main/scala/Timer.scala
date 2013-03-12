package main.scala

/**
 * Created with IntelliJ IDEA.
 * User: satoshi
 * Date: 2013/03/12
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
object Timer {
  def timer(func: => Unit) {
    System.gc
    val start = System.nanoTime
    func
    println("Elapsed time: %d".format(System.nanoTime - start))
  }
}
