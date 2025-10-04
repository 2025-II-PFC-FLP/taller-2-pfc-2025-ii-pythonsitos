package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GrandeTest extends AnyFunSuite {
  val cd = new ConjDif()

  val g1: cd.ConjDifuso = cd.grande(3, 3)

  test("Valor con n=0") {
    assert(g1(0) == 0.0)  // siempre debe dar 0
  }

  test("Valor aproximándose a 0 para n pequeño") {
    assert(g1(1) < 1.0)
    assert(g1(2) < 1.0)
  }

  test("Valor aproximándose a 1 para n grande") {
    assert(g1(1000) > 0.9)
    assert(g1(100) > 0.9)
  }

  test("Mientras n aumenta, el valor de la función aumenta") {
    assert(g1(50) > g1(30))
  }

  test("Mientras e aumenta, el valor de la función crece más lento") {
    val g2 = cd.grande(3, 6)
    assert(g2(1) < g1(1))
    assert(g2(10) < g1(10))
  }

}
