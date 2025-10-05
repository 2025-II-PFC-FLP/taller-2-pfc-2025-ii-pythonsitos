package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ComplementoTest extends AnyFunSuite {
  val cd = new ConjDif()

  // Conjuntos
  val cdCero: cd.ConjDifuso = _ => 0.0
  val cdUno: cd.ConjDifuso = _ => 1.0
  val cdInter: cd.ConjDifuso = n => n / 10.0
  val mmq: cd.ConjDifuso = cd.muchoMayorQue(2, 6)

  // Complementos
  val compCero = cd.complemento(cdCero)
  val compUno = cd.complemento(cdUno)
  val compInter = cd.complemento(cdInter)
  val compInter2 = cd.complemento(compInter)
  val compMmq = cd.complemento(mmq)

  test("Complemento de conjunto que siempre es 0 debe ser 1") {
    assert(compCero(0) == 1.0)
    assert(compCero(100) == 1.0)
  }

  test("Complemento de conjunto que siempre es 1 debe ser 0") {
    assert(compUno(0) == 0.0)
    assert(compUno(100) == 0.0)
  }

  test("Complemento de valores intermedios") {
    assert(compInter(0) == 1.0)
    assert(compInter(5) == 0.5)
    assert(compInter(10) == 0.0)
  }

  test("Complemento doble debe devolver el conjunto original") {
    assert(compInter2(0) == cdInter(0))
    assert(compInter2(5) == cdInter(5))
    assert(compInter2(10) == cdInter(10))
  }

  test("Complemento de muchoMayorQue") {
    assert(compMmq(0) == 1.0)
    assert(compMmq(2) == 1.0)
    assert(compMmq(4) == 1 - mmq(4))
    assert(compMmq(7) == 0.0)
  }
}
