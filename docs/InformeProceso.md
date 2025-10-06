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

---
## **Explicación matemática del conjunto difuso de números grandes**

El conjunto difuso **de números grandes** busca modelar la idea intuitiva de qué tan "grande" es un número entero.  
En los conjuntos clásicos, un número es grande o no lo es.  
Sin embargo, en la teoría de conjuntos difusos, la **grandeza** puede medirse de forma gradual, asignando un **grado de pertenencia** entre 0 y 1 según qué tan grande sea el numero.

---

### **Definición matemática**

Para construir este conjunto difuso, se propone la siguiente función de pertenencia:

$$
\mu_{\text{grande}}(n) = \left( \frac{n}{n + d} \right)^e
$$

donde:

- $n \in \mathbb{N}$ es el número cuyo grado de “grandeza” se quiere medir,
- $d \in \mathbb{N}$ es un entero positivo pequeño $d \ge 1$,
- $e \in \mathbb{N}$ es un entero mayor que 1 que ajusta la **curvatura** de la función.

Así, la función que define el conjunto difuso de números grandes es:

$$
f_{\text{grande}} : \mathbb{N} \to [0, 1]
$$

$$
f_{\text{grande}}(n) = \left( \frac{n}{n + d} \right)^e
$$

---

### **Análisis de la función de pertenencia**

1. **Dominio válido:**

   Como $n, d > 0$, el denominador $n + d > n$.  
   Por tanto, el cociente $\frac{n}{n + d}$ está siempre entre 0 y 1:

   $$
   0 < \frac{n}{n + d} < 1
   $$

2. **Rango:**

   Al elevar a una potencia positiva $e > 1$, la expresión sigue estando dentro de $[0,1)$.  
   En consecuencia:

   $$
   0 < \left(\frac{n}{n + d}\right)^e < 1
   $$

   Esto garantiza que $\mu_{\text{grande}}(n)$ cumple la propiedad fundamental de una función de pertenencia difusa:

   $$
   \forall n \in \mathbb{N}, \; 0 \le \mu_{\text{grande}}(n) \le 1
   $$

3. **Comportamiento asintótico:**

    - Cuando $n$ es **pequeño**, $n/(n+d)$ es pequeño, por lo tanto $\mu_{\text{grande}}(n)$ se aproxima a 0.
    - Cuando $n \to \infty$:

      $$
      \lim_{n \to \infty} \frac{n}{n + d} = 1
      $$

      por tanto:

      $$
      \lim_{n \to \infty} \mu_{\text{grande}}(n) = 1
      $$

   Esto significa que los números grandes tienen un grado de pertenencia **cercano a 1**,  
   mientras que los pequeños se mantienen cerca de 0.

---

### **Demostración por inducción matemática**

Queremos demostrar que:

$$
\forall n \in \mathbb{N}, \; 0 \le \mu_{\text{grande}}(n) \le 1
$$


### **Caso base $(n = 1)$**

Sustituyendo $n = 1$ en la definición:

$$
\mu_{\text{grande}}(1) = \left(\frac{1}{1 + d}\right)^e
$$

Como $d \ge 1$, se cumple que:

$$
0 < \frac{1}{1 + d} < 1
$$

Por tanto:

$$
0 < \left(\frac{1}{1 + d}\right)^e < 1
$$

La propiedad se cumple para $n = 1$.

### **Hipótesis inductiva**

Supongamos que para un cierto $k \in \mathbb{N}$:

$$
0 < \left(\frac{k}{k + d}\right)^e < 1
$$

es decir, la función de pertenencia cumple el rango válido.


### **Paso inductivo $(n = k + 1)$**

Evaluamos para $n = k + 1$:

$$
\mu_{\text{grande}}(k + 1) = \left(\frac{k + 1}{k + 1 + d}\right)^e
$$

Observamos que:

$$
\frac{k + 1}{k + 1 + d} > \frac{k}{k + d}
$$

ya que al aumentar $n$, la fracción se acerca más a 1.  
Por tanto, si la hipótesis es cierta para $k$, también lo es para $k+1$:

$$
0 < \left(\frac{k + 1}{k + 1 + d}\right)^e < 1
$$

Esto demuestra que la propiedad se conserva para el siguiente caso.

### **Conclusión**

Por el **principio de inducción matemática**,  
se cumple que:

$$
\forall n \in \mathbb{N}, \quad 0 \le \left(\frac{n}{n + d}\right)^e \le 1
$$

Por lo tanto, la función \(\mu_{\text{grande}}(n)\) define correctamente un **conjunto difuso de números grandes**,  
ya que su rango se mantiene en \([0,1]\) para todos los enteros positivos \(n\).

---

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
## Explicación matemática de la Intersección en conjuntos difusos

La **intersección difusa** representa el grado en que dos conjuntos difusos comparten pertenencia sobre los mismos elementos del universo.  
En los conjuntos clásicos, un elemento pertenece a la intersección solo si pertenece a **ambos conjuntos**.  
En el contexto difuso, esta idea se generaliza considerando que un elemento puede pertenecer **en cierto grado** a cada conjunto.  
Por tanto, el grado de pertenencia a la intersección se obtiene tomando el **mínimo** de ambos valores.

---

### **Definición matemática**

Sean  $A$  y $B$ dos conjuntos difusos definidos sobre el mismo universo $U$, con funciones de pertenencia:

$$
\mu_A : U \rightarrow [0,1], \qquad \mu_B : U \rightarrow [0,1]
$$

La función de pertenencia de la intersección difusa  $A \cap B$ se define punto a punto como:

$$
\mu_{A \cap B}(x) = \min(\mu_A(x), \mu_B(x)), \qquad \forall x \in U
$$

Esto significa que, para cada elemento $x$, el grado de pertenencia a la intersección es el **menor** de sus grados de pertenencia en $A$ y en $B$.

---

### **Demostración de interseccion por inducción matemática**

Queremos demostrar que para todo conjunto difuso $A, B \subseteq U$:

$$
\forall x \in U,\; 0 \le \mu_{A \cap B}(x) \le 1
$$

y que esta definición genera una función de pertenencia válida.


### **Caso base $(|U| = 1)$**

Si el universo tiene un solo elemento $x_1$, sabemos por definición de conjunto difuso que:

$$
0 \le \mu_A(x_1) \le 1 \qquad \text{y} \qquad 0 \le \mu_B(x_1) \le 1
$$

Entonces, aplicando la definición de intersección:

$$
\mu_{A \cap B}(x_1) = \min(\mu_A(x_1), \mu_B(x_1))
$$

El mínimo de dos números en el intervalo $[0,1]$ también pertenece a $[0,1]$.  
Por tanto:

$$
0 \le \mu_{A \cap B}(x_1) \le 1
$$

De esta forma, la propiedad se cumple para un universo de tamaño 1.

### **Hipótesis inductiva**

Supongamos que la propiedad es cierta para un universo de tamaño $k$, es decir, para todo $x_i \in U_k$:

$$
0 \le \mu_{A \cap B}(x_i) \le 1
$$

### **Paso inductivo $(|U| = k + 1)$**

Sea ahora un universo con un elemento adicional:

$$
U_{k+1} = \{x_1, x_2, \dots, x_k, x_{k+1}\}
$$

Por hipótesis inductiva, los primeros $k$ elementos cumplen la propiedad.  
Analicemos el nuevo elemento $x_{k+1}$:

$$
\mu_{A \cap B}(x_{k+1}) = \min(\mu_A(x_{k+1}), \mu_B(x_{k+1}))
$$

Como por definición $0 \le \mu_A(x_{k+1}) \le 1$ y $0 \le \mu_B(x_{k+1}) \le 1$,  
su mínimo también estará dentro del intervalo $[0,1]$.

Por tanto:

$$
0 \le \mu_{A \cap B}(x_{k+1}) \le 1
$$

Así, la propiedad se cumple para el nuevo elemento y, junto con la hipótesis,  
para todo el universo $U_{k+1}$.


### **Conclusión**

Por el **principio de inducción matemática**,la definición de la intersección difusa produce una función de pertenencia válida para cualquier universo finito \(U\).

En consecuencia:

$$
\forall x \in U, \quad 0 \le \mu_{A \cap B}(x) \le 1
$$
---
