package main.scala

import collection.mutable

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
object Lessen4 {
  def main(args: Array[String]) {
    val i = 100

    // list prime numbers ranging from 2 to i
    println(PrimeNumber.toList(i))

    // whether i is prime number
    println(PrimeNumber.contains(i))
  }
}

object PrimeNumber {
  /**
   * Return whether the target number is the prime number.
   *
   * @param i Target number.
   * @return true if the target number is the prime number, false otherwise.
   */
  def contains(i: Int) = i match {
    case i if i <= 1 => false
    case i if i == 2 => true
    //case _           => toList(i).contains(i)
    // この方が速かった
    case _           => Stream.range(2, i).forall(i % _ != 0)
  }

  /**
   * Return prime numbers.
   *
   * @param max Maximum number of the list.
   * @return Prime numbers.
   */
  def toList(max: Int) = max match {
    case max if max <= 1 => List()
    case max if max == 2 => List(2)
    // step 1
    // 整数を最初の素数である 2 から昇順で探索リストに羅列する。
    // step 2
    // リストの先頭の数を素数リストに記録する。
    case _ => eratosthenesSieve(List(2), 2 to max)
  }

  /**
   * Run Sieve of Eratosthenes algorithm.
   *
   * @param primes Prime numbers.
   * @param range  Candidate range.
   * @return Prime numbers.
   */
  private
  def eratosthenesSieve(primes: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step 3
    // 前のステップで素数リストに加えられた数の全ての倍数を、探索リストから削除する。
    _ % primes.last != 0
  } match {
    // step 4
    // 探索リストの最大値が素数リストの最大値の平方よりも小さい場合、素数リストおよび探索リストに残っている数が素数となる。
    // 探索リストの最大値が素数リストの最大値の平方よりも大きい場合、ステップ 2 に戻る。
    case candidates if candidates.max <= math.pow(primes.max, 2) => primes ++ candidates
    // step 2
    // リストの先頭の数を素数リストに記録する。
    case candidates => eratosthenesSieve(primes ::: List(candidates.head), candidates)
  }
}
