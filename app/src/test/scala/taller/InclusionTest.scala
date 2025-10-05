package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InclusionTest extends AnyFunSuite {
  val cd = new ConjDif()

  // Conjuntos base
  val cdCero: cd.ConjDifuso = _ => 0.0
  val cdUno: cd.ConjDifuso = _ => 1.0
  val cdInter: cd.ConjDifuso = n => n / 10.0
  val cdInter2: cd.ConjDifuso = n => n / 20.0
  val mmq: cd.ConjDifuso = cd.muchoMayorQue(2, 6)
  val grande: cd.ConjDifuso = cd.grande(3, 3)

  test("Un conjunto está incluido en sí mismo") {
    assert(cd.inclusion(cdInter, cdInter))
    assert(cd.inclusion(mmq, mmq))
    assert(cd.inclusion(cdCero, cdCero))
    assert(cd.inclusion(cdUno, cdUno))
  }

  test("El conjunto cero está incluido en cualquier conjunto") {
    assert(cd.inclusion(cdCero, cdInter))
    assert(cd.inclusion(cdCero, cdUno))
    assert(cd.inclusion(cdCero, mmq))
    assert(cd.inclusion(cdCero, grande))
  }

  test("El conjunto uno solo está incluido en sí mismo") {
    assert(cd.inclusion(cdUno, cdUno))
    assert(!cd.inclusion(cdUno, cdInter))
    assert(!cd.inclusion(cdUno, mmq))
    assert(!cd.inclusion(cdUno, grande))
  }

  test("Un conjunto está incluido en otro si sus valores son menores o iguales") {
    assert(cd.inclusion(cdInter2, cdInter)) // cdInter2(n) <= cdInter(n) para todo n
    assert(cd.inclusion(cdInter2, cdUno))   // cdInter2(n) <= 1 para todo n
    assert(!cd.inclusion(cdInter, cdInter2)) // cdInter(n) > cdInter2(n) para algunos n
  }

  test("Casos donde la inclusión no se cumple") {
    assert(!cd.inclusion(grande, cdCero))
    assert(!cd.inclusion(mmq, cdCero))
    assert(!cd.inclusion(cdInter, cdCero))
  }
}