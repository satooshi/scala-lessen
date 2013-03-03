package main.scala

/**
 * 可能ならコマンドラインから入力を受け取って、n回表示するように改造してください。
 * 何回目のHello World!かも表示してみてください。
 */
object Lessen1_1 {
  def main(args: Array[String]) {
    if (args.length == 0) {
      println("arg1: Int > 0")
      return
    }

    try {
      val arg1 = args(0).toInt

      if (arg1 > 0) forManyTimes(arg1)
      else println("arg1: Int > 0")
    } catch {
      case e: NumberFormatException => println("arg1: Int > 0")
      case e: Throwable => println("failed something")
    }
  }

  def forManyTimes(i: Int) = for (i <- 1 to i) println("Hello World! %d".format(i))
}
