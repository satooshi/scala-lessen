package main.scala

import org.scalatest.FunSuite

class PrimeNumberTest extends FunSuite {
  test("Prime numbers contain 2") {
    // assert true
    assert(PrimeNumber.contains(2))
  }

  test("Prime numbers contain 3") {
    // expect true
    expectResult(true)(PrimeNumber.contains(3))
  }

  test("Prime numbers do not contain 4") {
    // expect false
    expectResult(false)(PrimeNumber.contains(4))
  }

  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    // expect 2, 3, 5, 7
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(PrimeNumber.toList(20))
  }

  test("1 is not prime number") {
    expectResult(List())(PrimeNumber.toList(1))
  }
}

class MyFirstPrimeNumberTest extends FunSuite {
  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(MyFirstPrimeNumber.toList(20))
  }
}

class MyFirstPrimeNumber2Test extends FunSuite {
  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(MyFirstPrimeNumber2.toList(20))
  }
}

class MyFirstPrimeNumber3Test extends FunSuite {
  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(MyFirstPrimeNumber3.toList(20))
  }
}

class MyFirstPrimeNumber4Test extends FunSuite {
  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(MyFirstPrimeNumber4.toList(20))
  }

  test("1 is not prime number") {
    expectResult(List())(MyFirstPrimeNumber4.toList(1))
  }
}

class MyFirstPrimeNumber5Test extends FunSuite {
  test("Prime numbers ranging from 2 to 20 are 2, 3, 5, 7, 11, 13, 17, 19") {
    expectResult(List(2, 3, 5, 7, 11, 13, 17, 19))(MyFirstPrimeNumber5.toList(20))
  }

  test("2 is prime number") {
    expectResult(List(2))(MyFirstPrimeNumber5.toList(2))
  }

  test("1 is not prime number") {
    expectResult(List())(MyFirstPrimeNumber5.toList(1))
  }

  test("0 is not prime number") {
    expectResult(List())(MyFirstPrimeNumber5.toList(0))
  }

  test("-1 is not prime number") {
    expectResult(List())(MyFirstPrimeNumber5.toList(-1))
  }
}
