package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UnionTest extends AnyFunSuite {
  val cd = new ConjDif()

  val cd1: cd.ConjDifuso = cd.grande(3, 4)
  val cd2: cd.ConjDifuso = cd.muchoMayorQue(1, 5)

  test("Union toma el valor máximo entre los grados de pertenencia de los conjuntos") {
    val union12 = cd.union(cd1, cd2)
    val x = 4
    val esperado = Math.max(cd1(x), cd2(x))
    assert(union12(x) == esperado)
  }

  test("Union de un conjunto consigo mismo debe ser igual al conjunto original") {
    val union11 = cd.union(cd1, cd1)
    assert(cd.igualdad(cd1, union11))
  }

  test("Union debe ser conmutativa") {
    val union12 = cd.union(cd1, cd2)
    val union21 = cd.union(cd2, cd1)
    assert(cd.igualdad(union12, union21))
  }

  test("Union con el conjunto vacío (todo 0) debe dar el conjunto original") {
    val cdVacio: cd.ConjDifuso = _ => 0.0
    val union = cd.union(cd1, cdVacio)
    assert(cd.igualdad(cd1, union))
  }

  test("la unión con el conjunto universal debe dar el conjunto universal") {
    val cdUniversal: cd.ConjDifuso = _ => 1.0
    val union = cd.union(cd1, cdUniversal)
    val resultadoEsperado: cd.ConjDifuso = _ => 1.0
    assert(cd.igualdad(union, resultadoEsperado))
  }

}

