package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IgualdadTest extends AnyFunSuite {
  val cd = new ConjDif()

  val cd1: cd.ConjDifuso = cd.grande(3,4)
  val cd2: cd.ConjDifuso = cd.muchoMayorQue(1,5)
  val cd3: cd.ConjDifuso = cd.muchoMayorQue(2,6)

  test("Igualdad entre conjuntos iguales") {
    assert(cd.igualdad(cd2, cd2))
    assert(cd.igualdad(cd3, cd3))
    assert(cd.igualdad(cd1, cd1))
  }

  test("No igualdad entre conjuntos desiguales") {
    assert(!cd.igualdad(cd1, cd2))
    assert(!cd.igualdad(cd2, cd3))
    assert(!cd.igualdad(cd3, cd1))
  }

  test("No importa el orden en la igualdad") {
    assert(cd.igualdad(cd1, cd2) == cd.igualdad(cd2, cd1))
    assert(cd.igualdad(cd3, cd2) == cd.igualdad(cd2, cd3))
    assert(cd.igualdad(cd1, cd3) == cd.igualdad(cd3, cd1))
  }

  test("Dos conjuntos creados con los mismos parámetros deben ser iguales"){
    val cd4 = cd.muchoMayorQue(2,6)
    assert(cd.igualdad(cd3, cd4))
  }

  test("Igualdad con conjuntos constantes") { // Un conjunto constante es igual a sí mismo
    val cdCero: cd.ConjDifuso = _ => 0.0
    val cdUno: cd.ConjDifuso = _ => 1.0
    assert(cd.igualdad(cdCero, cdCero))
    assert(cd.igualdad(cdUno, cdUno))
  }

}
