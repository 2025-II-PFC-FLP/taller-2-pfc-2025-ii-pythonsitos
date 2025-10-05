package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InterseccionTest extends AnyFunSuite {
  val cd = new ConjDif()

  val cd1: cd.ConjDifuso = cd.grande(3, 4)
  val cd2: cd.ConjDifuso = cd.muchoMayorQue(1, 5)

  test("Intersección toma el valor mínimo entre los grados de pertenencia de los conjuntos") {
    val interseccion12 = cd.interseccion(cd1, cd2)
    val x = 4
    val esperado = Math.min(cd1(x), cd2(x))
    assert(interseccion12(x) == esperado)
  }

  test("Intersección de un conjunto consigo mismo debe ser igual al conjunto original") {
    val interseccion11 = cd.interseccion(cd1, cd1)
    assert(cd.igualdad(cd1, interseccion11))
  }

  test("Intersección debe ser conmutativa") {
    val interseccion12 = cd.interseccion(cd1, cd2)
    val interseccion21 = cd.interseccion(cd2, cd1)
    assert(cd.igualdad(interseccion12, interseccion21))
  }

  test("Intersección con el conjunto vacío debe dar el conjunto vacío") {
    val cdVacio: cd.ConjDifuso = _ => 0.0
    val interseccion = cd.interseccion(cd1, cdVacio)
    assert(cd.igualdad(cdVacio, interseccion))
  }

  test("la intersección con el conjunto universal debe dar el conjunto original") {
    val cdUniversal: cd.ConjDifuso = _ => 1.0
    val interseccion = cd.interseccion(cd1, cdUniversal)
    assert(cd.igualdad(cd1, interseccion))
  }


}
