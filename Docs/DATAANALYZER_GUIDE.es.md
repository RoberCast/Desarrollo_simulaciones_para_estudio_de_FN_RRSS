# Guía del analizador de datos, DataAnalyzer
El analizador de datos usa la metodología de validación adoptada previamente y se encarga de usar dicha metodología para validar el resultado de la simulación del modelo de difusión de noticias SBFC. Esta metodología consiste en ejecutar la simulación $n$ veces, calcular la media y hallar la raíz del error cuadrático medio normalizado (NRMSE). Si NRMSE < 0.2 entonces el ajuste del modelo se considera válido.  

Después de colocar los archivos y el árbol de carpetas como se indica en el archivo [README.es.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/README.es.md), y después de ejecutar el simulador en modo *batch*, ver el [SIMULATOR_GUIDE.es.md]([https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_GUIDE.es.md), el analizador de datos se ejecuta por línea de comandos, desde el cmd, y la forma de ejecutarlo es mediante el comando `java -jar DataAnalyzer.jar [parametros]`. La forma de ejecutar DataAnalyzer es como sigue:

1. **Ejecución con un parámetro**. Este parámetro consiste en el valor `-h`. La ejecución muestra la ayuda. Si no es este valor el analizador muestra un error de parámetros. Se puede ver un ejemplo en la figura.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/1Parameter.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>

2. 
