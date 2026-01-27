# Guía del analizador de datos, DataAnalyzer
El analizador de datos usa la metodología de validación adoptada previamente y se encarga de usar dicha metodología para validar el resultado de la simulación del modelo de difusión de noticias SBFC. Esta metodología consiste en ejecutar la simulación $n$ veces, calcular la media de agentes en cada estado posible y hallar la raíz del error cuadrático medio normalizado (NRMSE). Si NRMSE < 0.2 entonces el ajuste del modelo se considera válido.  

Después de colocar los archivos y el árbol de carpetas como se indica en el archivo [README.es.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/README.es.md), y después de ejecutar el simulador en modo *batch*, ver el archivo [SIMULATOR_GUIDE.es.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_GUIDE.es.md), el analizador de datos se ejecuta por línea de comandos, desde el cmd, y la sintaxis de ejecución es `java -jar DataAnalyzer.jar [parametros]`. A continuación se explican los comandos de ejecución de DataAnalyzer.

## Ejecución con un parámetro 
Este parámetro consiste en el valor `-h`. La ejecución muestra la ayuda. Si no es este valor el analizador muestra un error de parámetros. Se puede ver un ejemplo en la figura.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/1Parameter.png" alt="FakeNewsSpreadSim Model." width="90%"/>
  </p>

## Ejecución con dos parámetros
Esta ejecución se usa para la validación del modelo de difusión de noticias SBFC, que recibe los datos de la salida de la ejecución *batch* del simulador y calcula el NRMSE. Si el valor calculado es menor que 0.2 quiere decir que el modelo será válido. Es decir, que se ajusta al modelo de referencia. Los parámetros que hay que introducir son, en este orden, el archivo de parámetros de ejecución y el de resultado de la simulación de la ejecución *batch* del simulador.

  La ejecución muestra un error si no son los archivos correctos, no están en el orden indicado o se les ha cambiado el nombre. Tras la ejecución se muestra el resultado del análisis, donde se pueden ver los parámetros de la simulación, número de *ticks*, número de ejecuciones, el promedio de agentes en cada estado después de $n$ ejecuciones y el resultado del análisis. Se puede ver un ejemplo en la siguiente figura.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/2Parameter.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>

## Ejecución con tres parámetros
Esta ejecución se usa para el análisis con datos reales. Además de lo explicado en la ejecución con dos parámetros, se añade al conjunto de parámetros el archivo que contiene los datos reales. En este archivo de noticias reales, cada línea es una noticia real con una estructura como `["id", "news_url", "tittle", "tweet_ids"]`, donde `id` es un ID de noticia, `news_url` es la URL de la noticia, `tittle` es el título de la noticia y `tweet_ids` es una lista de códigos numéricos separados por un espacio en blanco donde cada código representa el ID de usuario que compartió la noticia. 

Si el archivo de datos reales no existe se informa de un error. Si existe, se pedirá un ID de noticia válido. En este punto se podrá escribir `quit` para salir del programa o introducir un ID de noticia válido. Si el ID no existe dentro del dataset se informará de un error, si existe pero el número de creyentes de la noticia es mayor que el número de agentes en la simulación se volverá a pedir otro ID de noticia válido. Si el ID de noticia es correcto, se calcula el NRMSE y se imprime el resultado del análisis como en el caso anterior. La información mostrada es la misma, pero se añade el número de agentes que cree en la noticia con un ID de noticia determinado. Se puede ver un ejemplo en la siguiente figura.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/3Parameter.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>
