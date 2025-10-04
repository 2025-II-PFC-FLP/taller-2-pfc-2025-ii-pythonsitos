package taller

class ConjDif{

  type ConjDifuso = Int => Double

  def muchoMayorQue(a: Int, m: Int): ConjDifuso = {
    def mma(x: Int): Double = {
      if (x <= a) 0.0
      else if (x > a && x <= m) (x- a).toDouble / (m- a).toDouble
      else 1.0
    }
    mma
  }

  def pertenece(elem: Int, s: ConjDifuso): Double = {
    s(elem)
  }

  def grande(d:Int, e:Int): ConjDifuso = {
    (n:Int) => Math.pow(n.toDouble/(n+d).toDouble,e.toDouble)
  }

  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    inclusion(cd1,cd2) && inclusion(cd2,cd1)
  }

  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    (n:Int) => Math.max(cd1(n),cd2(n))
  }

  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    (n:Int) => Math.min(cd1(n),cd2(n))
  }

}
