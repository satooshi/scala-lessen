package main.scala

/**
 * 可能ならコマンドラインから入力を受け取って、n回表示するように改造してください。
 * 何回目のHello World!かも表示してみてください。
 */
object Lessen3 {
  def main(args: Array[String]) {
    try {
      val i = args(0).toInt

      if (i > 0) for (n <- 1 to i) println("Hello World! %d".format(n))
      else println("arg1: Int > 0")
    } catch {
      case e: ArrayIndexOutOfBoundsException => println("arg1 is missing")
      case e: NumberFormatException          => println("arg1: Int")
      case e: Throwable                      => println("failed something")
    }
  }
}
