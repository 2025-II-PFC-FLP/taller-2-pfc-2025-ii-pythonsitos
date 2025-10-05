# Informe de proceso de funciones básicas sobre conjuntos difusos

Los conjuntos clásicos se caracterizan por dividir el universo en dos categorías: los elementos que pertenecen (miembros) y los que no pertenecen al conjunto (no miembros).

Sin embargo, en muchos casos resulta difícil determinar la pertenencia o no a un conjunto, ya que existen casos donde la pertenencia de un elemento puede ser parcial o incierta.

En este contexto surgen los conjuntos difusos, los cuales asignan a cada elemento un valor dentro del intervalo [0, 1] que representa su grado de pertenencia.
Este enfoque permite modelar fenómenos donde intervienen juicios subjetivos o información imprecisa

El presente informe describe el funcionamiento y comportamiento de algunas funciones básicas sobre conjuntos difusos.

---
## 1. `grande`

```scala
  def grande(d:Int, e:Int): ConjDifuso = {
    (n:Int) => Math.pow(n.toDouble/(n+d).toDouble,e.toDouble)
  }
```
Esta función crea un conjunto difuso de números grandes a partir de los parámetros ingresados, el cuál espera un número entero para ser evaluado y retornar el grado de pertenencia al conjunto
con la expresión:

$$
(\tfrac{n}{n+d})^e\;\; \text{donde:}
$$

-`n`: Es el número entero a comprobar si es grande.

-`d`: Es un número pequeño mayor o igual a uno el cual se suma en el denominador con el número entero que se quiere comprobar si pertenece al conjunto de números grandes.

-`e`: Es un número entero mayor a uno el cual 

### Ejemplo de funcionamiento

#### 1. Se crea el conjunto con parámetros iniciales
```Scala
val g1: cd.ConjDifuso = cd.grande(3, 3) //Conjunto difuso con parámetros d = 3 y e = 3
```

#### 2. La función retorna el Conjunto Difuso
```Scala
(n:Int) => Math.pow(n.toDouble/(n+d).toDouble,e.toDouble)
```
#### 3. Evaluación del número
```Scala
g1(1000)
```
En este caso se desea comprobar si **1000** es un número grande.

#### 4. Retorna grado de pertenencia del número 
```Scala
0.991054
```
El cual es el resultado de la operación:

$$
(\tfrac{1000}{1000+3})^3
$$

Como el valor es cercano a 1, se considera que tiene un gran grado de pertenencia al conjunto, por lo tanto, puede considerarse un número grande.

### Ejemplos de otros llamados:

| Llamado                | n    | Resultado aproximado |
|------------------------|------|----------------------|
| `cd.grande(3,3)(1)`    | 1    | 0.0156               |
| `cd.grande(3,3)(10)`   | 10   | 0.4552               |
| `cd.grande(3,3)(1000)` | 1000 | 0.9911               |
| `cd.grande(2,2)(1)`    | 1    | 0.1111               |
| `cd.grande(2,2)(10)`   | 10   | 0.6944               |
| `cd.grande(1,4)(5)`    | 5    | 0.4823               |


## 2. `igualdad` 
```scala
  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    inclusion(cd1,cd2) && inclusion(cd2,cd1)
  }
```
Esta función determina si dos conjuntos difusos son iguales, verificando que cada uno esté incluido en el otro.
Un conjunto A está incluido en B (A ⊆ B) si, para todo entero en el rango definido (0 a 1000 en este caso), se cumple que:

$$
A(x)\leq B(x)
$$

Por lo tanto, la igualdad se da cuando:

$$
A\subseteq B \wedge B\subseteq A
$$

### Ejemplo de funcionamiento

#### 1. Se crean los conjuntos 
```Scala
val c1: cd.ConjDifuso = cd.muchoMayorQue(2,6)
val c2: cd.ConjDifuso = cd.muchoMayorQue(2,6)
val c3: cd.ConjDifuso = cd.grande(2,2)
```
#### 2. Se evalúa la igualdad
```Scala
cd.igualdad(c1, c2) //true
cd.igualdad(c1, c3) //false
```
#### 3. Resultado esperado

- `c1` y `c2` se construyeron con los mismos parámetros → **son iguales**.

- `c1` y `c3` se construyeron de forma diferente → **no son iguales**.

### Ejemplo de otros llamados
| Conjunto A           | Conjunto B           | Resultado |
|----------------------|----------------------|-----------|
| `muchoMayorQue(2,6)` | `muchoMayorQue(2,6)` | true      |
| `grande(2,2)`        | `muchoMayorQue(1,5)` | false     |
| `grande(3,3)`        | `grande(3,3)`        | true      |
| `constante 0`        | `constante 1`        | false     |
| `muchoMayorQue(2,3)` | `muchoMayorQue(2,7)` | false     |

----

## 3. `Unión`

```scala
  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    (n:Int) => Math.max(cd1(n),cd2(n))
  }
```
Esta función crea un nuevo conjunto difuso que representa la unión de dos conjuntos difusos dados.
El grado de pertenencia de un elemento en el conjunto resultante se determina tomando el valor máximo entre los grados 
de pertenencia del elemento en cada uno de los conjuntos originales.
Matemáticamente, la unión de dos conjuntos difusos A y B se define como:
$$
(A \cup B)(x) = \max(A(x), B(x))
$$
### Ejemplo de funcionamiento
#### 1. Se crean los conjuntos 
```Scala
val c1: cd.ConjDifuso = cd.muchoMayorQue(2,6)
val c2: cd.ConjDifuso = cd.grande(2,2)
```
#### 2. Se crea el conjunto unión
```Scala
val cUnion: cd.ConjDifuso = cd.union(c1, c2)
```
#### 3. Evaluación del número
```Scala
cUnion(10)
```
En este caso se desea comprobar el grado de pertenencia del número **10** en el conjunto unión.             
#### 4. Retorna grado de pertenencia del número 
```Scala
0.6944
```
El cual es el resultado de la operación:    
$$
\max(muchoMayorQue(2,6)(10), grande(2,2)(10)) = \max(0.2621, 0.6944) = 0.6944
$$
### Ejemplo de otros llamados
| Conjunto A           | Conjunto B           | n   | Resultado aproximado |
|----------------------|----------------------|-----|--------------------|
| `muchoMayorQue(2,6)` | `grande(2,2)`        | 1   | 0.1111             |
| `muchoMayorQue(2,6)` | `grande(2,2)`        | 10  | 0.6944             |
| `muchoMayorQue(2,6)` | `grande(2,2)`        | 100 | 0.9138             |            
| `grande(3,3)`        | `grande(1,4)`        | 5   | 0.4823             |
| `grande(3,3)`        | `grande(1,4)`        | 50  | 0.9035             |
| `grande(3,3)`        | `grande(1,4)`        | 500 | 0.9831             |
----
## 4. `Intersección`

```scala
  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    (n:Int) => Math.min(cd1(n),cd2(n))
  }
```
Esta función crea un nuevo conjunto difuso que representa la intersección de dos conjuntos difusos dados.
El grado de pertenencia de un elemento en el conjunto resultante se determina tomando el valor mínimo entre los grados de pertenencia del elemento en cada uno de los conjuntos originales.
Matemáticamente, la intersección de dos conjuntos difusos A y B se define como:
$$
(A \cap B)(x) = \min(A(x), B(x))
$$
### Ejemplo de funcionamiento
#### 1. Se crean los conjuntos 
```Scala
val c1: cd.ConjDifuso = cd.muchoMayorQue(2,6)
val c2: cd.ConjDifuso = cd.grande(2,2)
```
#### 2. Se crea el conjunto intersección
```Scala
val cInter: cd.ConjDifuso = cd.interseccion(c1, c2)
```
#### 3. Evaluación del número
```Scala
cInter(10)
```
En este caso se desea comprobar el grado de pertenencia del número **10** en el conjunto intersección.             
#### 4. Retorna grado de pertenencia del número 
```Scala
0.2621
```
El cual es el resultado de la operación:    
$$
\min(muchoMayorQue(2,6)(10), grande(2,2)(10)) = \min(0.2621, 0.6944) = 0.2621
$$
### Ejemplo de otros llamados
| Conjunto A           | Conjunto B          | n   | Resultado aproximado |
|----------------------|---------------------|-----|----------------------|
| `muchoMayorQue(2,6)` | `grande(2,2)`       | 1   | 0.0156               |
| `muchoMayorQue(2,6)` | `grande(2,2)`       | 10  | 0.2621               |
| `muchoMayorQue(2,6)` | `grande(2,2)`       | 100 | 0.0833               |            
| `grande(3,3)`        | `grande(1,4)`       | 5   | 0.0156               |
| `grande(3,3)`        | `grande(1,4)`       | 50  | 0.4823               |
| `grande(3,3)`        | `grande(1,4)`       | 500 | 0.6944               |

----
