package main.scala

/**
 * Lessen3.
 *
 * http://vipprog.net/wiki/exercise.html
 *
 * 与えられた数が素数かどうか調べる
 * あるいは与えられた数までの素数を列挙する
 *
 * エラトステネスの篩でやってみた
 * http://ja.wikipedia.org/wiki/%E3%82%A8%E3%83%A9%E3%83%88%E3%82%B9%E3%83%86%E3%83%8D%E3%82%B9%E3%81%AE%E7%AF%A9
 *
 * i = 11
 * List(2, 3, 5, 7, 11)
 * true
 *
 * i = 100
 * List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
 * false
 */
object Lessen3 {
  def main(args: Array[String]) {
    val i = 1000
    // list of prime numbers ranging from 2 to i
    println(primeNumbers(i))

    // whether argument i is the prime number
    println(isPrimeNumber(i))
  }

  /**
   * Return whether the target number is the prime number.
   *
   * @param i Target number.
   * @return true if the target number is the prime number, false otherwise.
   */
  def isPrimeNumber(i: Int): Boolean = {
    if (i <= 1) false
    else if (i == 2) true
    else primeNumbers(i).contains(i)
  }

  /**
   * Return prime numbers.
   *
   * @param max Maximum number to search prime numbers.
   * @return Prime numbers.
   */
  def primeNumbers(max: Int): List[Int] = {

    /**
     * Return prime numbers.
     *
     * @param primes Prime numbers.
     * @param range  Search range.
     * @return Prime numbers.
     */
    def search(primes: List[Int], range: Seq[Int]): List[Int] = {
      // step 3
      // 前のステップで素数リストに加えられた数の全ての倍数を、探索リストから削除する。
      val candidates = range.filter { _ % primes.last != 0 }

      // step 4
      // 探索リストの最大値が素数リストの最大値の平方よりも小さい場合、素数リストおよび探索リストに残っている数が素数となる。
      // 探索リストの最大値が素数リストの最大値の平方よりも大きい場合、ステップ 2 に戻る。
      if (candidates.max <= math.pow(primes.max, 2)) primes ::: candidates.toList
      // step 2
      // リストの先頭の数を素数リストに記録する。
      else search(primes ::: List(candidates.head), candidates)
    }

    // step 1
    // 整数を最初の素数である 2 から昇順で探索リストに羅列する。
    // step 2
    // リストの先頭の数を素数リストに記録する。
    search(List(2), 2 to max)
  }
}
