package main.scala

/**
 * Lessen2.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * FizzBuzz
 *
 * なんか偉い人が考えた問題
 *
 * ルールは以下の通り
 *
 * - 1から順番に数を表示する
 * - その数が3で割り切れるなら"Fizz"、5で割り切れるなら"Buzz"、両方で割り切れるなら"FizzBuzz"と表示する
 * - 要するに"1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz ･･･"と出力される
 * - プログラマかどうかがわかるんだとさ
 *
 * 実行例
 * 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz Fizz 22 23 Fizz Buzz 26 Fizz 28 29 FizzBuzz 31 32 Fizz 34
 */
object Lessen2 {
  def main(args: Array[String]) {
    for (i <- 1 to 99) println(fizzBuzz(i))
    //for (i <- 1 to 99) println(fizzBuzz2(i))
    //fizzBuzz3(99)
    //for (i <- 1 to 99) println(fizzBuzz4(i))
  }

  /**
   * basic idea 1.
   *
   * @param i
   * @return
   */
  def fizzBuzz(i: Int): String = {
    val fizz = i % 3 == 0
    val buzz = i % 5 == 0

    if (fizz && buzz) "FizzBuzz"
    else if (fizz) "Fizz"
    else if (buzz) "Buzz"
    else i.toString()
  }

  /**
   * basic idea 2.
   *
   * @param i
   * @return
   */
  def fizzBuzz2(i: Int): String = {
    var s = ""

    if (i % 3 == 0) s += "Fizz"
    if (i % 5 == 0) s += "Buzz"

    if (s.isEmpty) i.toString() else s
  }

  /**
   * functional programming style.
   *
   * これは思いつかないわー
   * http://yuroyoro.hatenablog.com/entry/20100317/1268819400
   *
   * @param i
   */
  def fizzBuzz3(i: Int) = {
    1 to i
  } map {
    case i if i % 15 == 0 => "FizzBuzz"
    case i if i % 3 == 0 => "Fizz"
    case i if i % 5 == 0 => "Buzz"
    case _ => i
  } foreach {
    s => println(s)
  }

  /**
   * pattern match
   *
   * @param i
   * @return
   */
  def fizzBuzz4(i: Int) = i match {
    case i if i % 15 == 0 => "FizzBuzz"
    case i if i % 3 == 0 => "Fizz"
    case i if i % 5 == 0 => "Buzz"
    case _ => i.toString()
  }
}
