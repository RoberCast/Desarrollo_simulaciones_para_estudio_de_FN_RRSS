# PFG - Desarrollo de simulaciones para el estudio de la difusión de noticias falsas (fake news) en redes sociales

## Universidad
Universidad Nacional de Educación a Distancia - UNED

Escuela Técnica Superior de Ingeniería Informática

## Curso
2024-2025

## Resumen
Proyecto de fin de grado.

FakeNewsSpreadSim implementa un modelo de difusión de noticias SBFC (Susceptible, Believer, Fact-Checker) para su simulación en Repast Simphony.

DataAnalyzer aplica una metodología de validación que implica la ejecución del modelo 10 veces, hacer el promedio del número de agentes en cada estado posible y calcular el NRMSE para determinar la validez del modelo. El modelo se considera válido si el NRMSE < 0.2. DataAnalyzer puede validar el modelo implementado y ajustarlo a la información observada en una noticia real a partir de un conjunto de datos de difusión de noticias reales.

## Instrucciones
Para simular y validar el modelo o ajustar el modelo a una noticia real a partir de un conjunto de datos reales, primero debe instalar Repast Simphony 2.11.0 desde su repositorio oficial:

https://repast.github.io/download.html

Una vez instalado el simulador, cree un proyecto Repast Simphony llamado "FakeNewsSpreadSim" y copie los archivos de la carpeta FakeNewsSpreadSim de este repositorio de la siguiente manera:

```bash    
FakeNewsSpreadSim
    ├── src
    │    └── fakeNewsSpreadSim
    │          ├── Agent.java
    │          ├── AgentNetworkParams.java
    │          ├── AgentScheduler.java
    │          ├── AgentState.java
    │          ├── CustomStyleAgentOGL2D.java
    │          ├── FNSpreadSimBuilder.java
    │          ├── JungNetworkGenerator.java
    │          └── ProbabilityParams.java
    │
    ├── batch
    │    └── batch_params.xml
    │
    └── FakeNewsSpreadSim.rs
         ├── context.xml
         └── parameters.xml
```

Los archivos .java conforman el modelo de difusión de noticias SBFC, el archivo *parameters.xml* contiene los parámetros predeterminados del modelo, el archivo *batch_params.xml* contiene los parámetros para la ejecución *batch* del simulador y el archivo *context.xml* contiene instrucciones necesarias para el simulador. El siguiente paso es configurar el simulador, para ello, puede seguir las instrucciones que aparecen [aquí](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_SETUP.es.md).

El siguiente paso es situar el analizador de datos *DataAnalyzer* en la ubicación que desee, puede encontrarlo [aquí](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/tree/main/DataAnalyzer%20JAR). El árbol de directorios para *DataAnalyzer*, que habrá que respetar, es el siguiente:

```bash    
Root Folder/
    ├── DataAnalyzer.jar
    └── Resources/
         ├── Dataset/
         │    └── dataset.csv
         └── Repast Simphony Output/
              ├── batch_param.txt
              └── batch_run.txt
```

Tras haber completado los pasos anteriores se pueden realizar las siguientes acciones:

* Ejecutar una simulación en modo GUI.
* Realizar una validación del modelo de difusión de noticias SBFC implementado.
* Realizar un ajuste del modelo de difusión de noticias SBFC respecto a los datos extraídos de una noticia real y validar dicho ajuste.

*Consideraciones*: Las probabilidades del modelo deben ajustarse de manera manual hasta dar con un ajuste válido, ya que es la forma en que se realiza en la metodología de validación adoptada. Existen recursos disponibles para aprender el manejo del simulador y del analizador de datos.

* [Guía del simulador](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_GUIDE.es.md).
* [Guía del analizador de datos, DataAnalyzer](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/DATAANALYZER_GUIDE.es.md).


## Demostración
A continuación, se pueden visualizar dos experimentos: la validación del modelo de difusión de noticias SBFC implementado y el ajuste de dicho de modelo a los datos de una noticia real obtenida de un conjunto de datos reales. Ambos experimentos se han ejecutado en un sistema Windows 11 Pro (x64), versión 25H2.

* **Validación del modelo de difusión de noticias SBFC implementado**. En el siguiente video se demuestra que el modelo implementado es consistente con el modelo de referencia, ya que finalmente se obtiene un NRMSE < 0.2 y el comportamiento es igual con los mismos parámetros. Es decir, el número de agentes en estado Believer cae rápidamente porque se realiza una verificación de hechos, se puede ver que aumenta rápidamente el número de agentes en estado FactChecker. Por otro lado, el número de agentes en estado Susceptible se mantiene por el hecho de que, con el paso del tiempo, la noticia puede olvidarse. Los valores de los parámetros de ejecución pueden visualizarse en el video.


https://github.com/user-attachments/assets/4b6e9c38-2dd0-4205-852f-579b85c589e1


* **Ajuste del modelo de difusión de noticias SBFC respecto a los datos extraídos de una noticia real y validación de dicho ajuste**. En el siguiente video se demuestra que el modelo de difusión de noticias puede ser ajustado a los datos reales extraídos de un conjunto de datos reales. La validación obtenida es de un NRMSE < 0.2, por lo tanto, el ajuste es válido. El gráfico que se observa al final del video es un ejemplo representativo, ya que se ha generado con una sola ejecución del simulador, pero se comprueba que el número de agentes Believer se aproxima al número de usuarios que creyeron la noticia real analizada. Los valores de los parámetros de ejecución pueden visualizarse en el video.

  
https://github.com/user-attachments/assets/c9b59f17-de4d-4ccc-b10f-bf2f08ca510c


## Licencia
Este proyecto está licenciado bajo la **Licencia Pública General GNU v3 (GPLv3)**.

© 2025 Roberto Castillejo Embid.
Este repositorio contiene código original desarrollado con fines académicos.
Puede usarlo, modificarlo y redistribuirlo libremente bajo los mismos términos de la licencia (GPLv3).

## Créditos
### Modelo de difusión de noticias
E. Sulis and M. Tambuscio. (2020, Oct.) Simulation of misinformation spreading processes
in social networks: an application with netlogo. Access: 2025-08-14. [Online]. Available:
https://ieeexplore.ieee.org/document/9260064/ 

### Metodología de validación
A. Gausen, W. Luk, and C. Guo. (2021, Jun.) Can we stop fake news? using agent-based
modelling to evaluate countermeasures for misinformation on social media. Access:
2025-08-14. [Online]. Available: https://workshop-proceedings.icwsm.org/pdf/2021_63.pdf

## Cómo citar este trabajo
Si utiliza este proyecto, cítelo utilizando el DOI:

[https://doi.org/10.5281/zenodo.17360338](https://doi.org/10.5281/zenodo.17360338)

También puede utilizar la siguiente entrada BibTeX:

```bibtex
@misc{RoberCast2025,
  author = {Roberto Castillejo Embid},
  title = {Desarrollo de simulaciones para el estudio de la difusión de noticias falsas (fake news) en redes sociales},
  year = {2025},
  publisher = {Zenodo},
  doi = {10.5281/zenodo.17360338},
  url = {https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS}
}
