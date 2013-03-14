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
    case _ => eratosthenesSieve(2, List(2), 2 to max)
  }

  /**
   * Run Sieve of Eratosthenes algorithm.
   *
   * @param last   Last element of primes.
   * @param primes Prime numbers.
   * @param range  Candidate range.
   * @return Prime numbers.
   */
  private
  def eratosthenesSieve(last: Int, primes: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step 3
    // 前のステップで素数リストに加えられた数の全ての倍数を、探索リストから削除する。
    _ % last != 0
  } match {
    // step 4
    // 探索リストの最大値が素数リストの最大値の平方よりも小さい場合、素数リストおよび探索リストに残っている数が素数となる。
    // 探索リストの最大値が素数リストの最大値の平方よりも大きい場合、ステップ 2 に戻る。
    case xs if xs.max <= math.pow(primes.max, 2) => primes ++ xs
    // step 2
    // リストの先頭の数を素数リストに記録する。
    case xs => eratosthenesSieve(xs.head, primes ::: List(xs.head), xs)
  }
}

object MyFirstPrimeNumber {
  def toList(max: Int): List[Int] = {
    // step1
    val range = 2 to max

    // step2
    val list = List(range.head)

    eratosthenesSieve(list, range)
  }

  private
  def eratosthenesSieve(list: List[Int], range: Seq[Int]): List[Int] = {
    // step3
    val last = list.last
    val range1 = range.filter(i => i % last != 0)

    // step4
    if (range1.max < math.pow(list.max, 2)) {
      list ::: range1.toList
    } else {
      // back to step2
      val list1 = list ::: List(range1.head)
      // step3
      eratosthenesSieve(list1, range1)
    }
  }
}

object MyFirstPrimeNumber2 {
  def toList(max: Int): List[Int] = {
    // step1
    // step2
    eratosthenesSieve(List(2), 2 to max)
  }

  private
  def eratosthenesSieve(list: List[Int], range: Seq[Int]): List[Int] = {
    // step3
    val range1 = range.filter(i => i % list.last != 0)

    // step4
    if (range1.max < math.pow(list.max, 2)) {
      list ::: range1.toList
    } else {
      // back to step2
      // step3
      eratosthenesSieve(list ::: List(range1.head), range1)
    }
  }
}

object MyFirstPrimeNumber3 {
  def toList(max: Int): List[Int] = {
    // step1
    // step2
    eratosthenesSieve(List(2), 2 to max)
  }

  private
  def eratosthenesSieve(list: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step3
    _ % list.last != 0
  } match {
    // step4
    case range1 if range1.max < math.pow(list.max, 2) => list ::: range1.toList
    // back to step2
    // step3
    case range1 => eratosthenesSieve(list ::: List(range1.head), range1)
  }
}

object MyFirstPrimeNumber4 {
  def toList(max: Int): List[Int] = {
    // step1
    // step2
    eratosthenesSieve(2, List(2), 2 to max)
  }

  private
  def eratosthenesSieve(last: Int, list: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step3
    _ % last != 0
  } match {
    // step4
    case range1 if range1.max < math.pow(list.max, 2) => list ++ range1
    // back to step2
    // step3
    case range1 => eratosthenesSieve(range1.head, list ::: List(range1.head), range1)
  }
}

object MyFirstPrimeNumber5 {
  def toList(max: Int): List[Int] = max match {
    case max if max <= 1 => List()
    case max if max == 2 => List(2)
    // step1
    // step2
    case _ => eratosthenesSieve(2, List(2), 2 to max)
  }

  private
  def eratosthenesSieve(last: Int, list: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step3
    _ % last != 0
  } match {
    // step4
    case range1 if range1.max < math.pow(list.max, 2) => list ++ range1
    // back to step2
    // step3
    case range1 => eratosthenesSieve(range1.head, list ::: List(range1.head), range1)
  }
}

object MyFirstPrimeNumber6 {
  def contains(i: Int) = toList(i).contains(i)

  def toList(max: Int): List[Int] = max match {
    case max if max <= 1 => List()
    case max if max == 2 => List(2)
    // step1
    // step2
    case _ => eratosthenesSieve(2, List(2), 2 to max)
  }

  private
  def eratosthenesSieve(last: Int, list: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step3
    _ % last != 0
  } match {
    // step4
    case range1 if range1.max < math.pow(list.max, 2) => list ++ range1
    // back to step2
    // step3
    case range1 => eratosthenesSieve(range1.head, list ::: List(range1.head), range1)
  }
}

object MyFirstPrimeNumber7 {
  def contains(i: Int) = i match {
    case i if i <= 1 => false
    case i if i == 2 => true
    case _ => toList(i).contains(i)
  }

  def toList(max: Int): List[Int] = max match {
    case max if max <= 1 => List()
    case max if max == 2 => List(2)
    // step1
    // step2
    case _ => eratosthenesSieve(2, List(2), 2 to max)
  }

  private
  def eratosthenesSieve(last: Int, list: List[Int], range: Seq[Int]): List[Int] = range.filter {
    // step3
    _ % last != 0
  } match {
    // step4
    case range1 if range1.max < math.pow(list.max, 2) => list ++ range1
    // back to step2
    // step3
    case range1 => eratosthenesSieve(range1.head, list ::: List(range1.head), range1)
  }
}
