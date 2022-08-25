# Mutantes
Proyecto Mutants para Mercado Libre

El proyecto valida si encontramos mutantes basado en su codigo de ADN, el cual 
detener las siguientes caracteristicas

* La secuencia de ADN debe ser de 4 letras (A,C,G,T) exclusivamente
* Numero de letras es igual al numero de secuencias medinate una matriz NxN
* Un mutante se encuentra si tiene dos o más secuencias con cuatro o más letras iguales cada secuencia de forma consecutiva en forma horizontal, vertical y/o oblicua

ej. Mutante  
   ***A***   A   C   G   T   ***G***    
      A   ***A***   **T**   G   C   ***G***    
      C   C   ***A***   T   A   ***G***    
      C   C   T   ***A***   G   ***G***    
      ***T***   ***T***   ***T***   ***T***   ***A***   C    
      A   G   T   C   C   G   

ej. Humano  
      A   A   C   G   T   G  
      A   T   T   G   C   C    
      C   C   A   T   A   G    
      C   C   T   A   G   G  
      C   T   A   T   A   C    
      A   G   T   C   C   G  
      
### Uso API

Para usar se tiene dos metodos uno para validar que usa el metodo POST y otro para obtener los estadisticos de pruebas realizas a humanos y mutantes mediante el metodo GET

#### Metodo POST Validar Mutante
*http://[host:port]/mutant*

Recibe un objeto tipo JSON con estrcutura clave-valor, donde clave es "adn" y valor es un arreglo de cadenas

{
  "dna":["AACGTG", "AATGCG", "CCGTAG", "CCATGG", "TTAGAC", "AGTCCG"]
}

###### Retorno
* En caso de que sea un mutante nos regresa un codigo *HTTP 200 OK*
* En otro caso, es decir es humano retorna un codigo *HTTP 403 Forbidden*


#### Metodo GET  Estadisticas
*http://[host:port]/stats*

###### Retorno
Las estadisticas se regresan en un objeto JSON

*{"count_mutant_dna":2,"count_human_dna":4,"ratio":0.5}*

### Demo en vivo  
* [Validar Mutante](http://springmutants-env.eba-r2chpgzg.us-east-2.elasticbeanstalk.com/mutant)
* [Estadisticas](http://springmutants-env.eba-r2chpgzg.us-east-2.elasticbeanstalk.com/stats)
