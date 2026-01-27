# Guía del simulador

Con el simulador debidamente configurado, ver el documento [SIMULATOR_SETUP.es.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_SETUP.es.md), se va a proceder a explicar el funcionamiento del simulador. Existen dos modos de simulación: La simulación en modo GUI, de simulación única, y la simulación en modo *batch*, que ejecuta un número $n$ de simulaciones. A continuación, se explica cada uno de estos modos.

1 **Simulación en modo GUI**. Este modo de ejecución consiste en ejecutar la simulación del modelo de difusión de noticias SBFC implementado mediante una GUI una sola vez. Esta forma de ejecución sirve para ver cómo cambia la red de agentes según transcurre la simulación y también, para ver el gráfico generado con los valores de los estados de los agentes, es decir, en el tiempo $t$ se vería el número de agentes que se encuentra en cada estado posible. La ejecución sirve para visualizar que el modelo simulado se comporta de la misma forma que el modelo de referencia. Para ejecutar este modo de simulación se siguen los siguientes pasos:

  * **Ejecución en IDE Eclipse**. En el IDE Eclipse que se ha instalado con Repast Simphony, se ejecuta *FakeNewsSpreadSim Model*. Esto abre la ventana del simulador.

  <p align="center">
    <img src="../Images/Simulator_setup/EclipseRun.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>

  * **Introducción de los parámetros de entrada**. En la pestaña *Parameters* se encuentran los parámetros de entrada del modelo de difusión de noticias SBFC utilizado. A continuación, se explica cada uno de ellos.
     
    * *Default Random Seed*. Este parámetro lo incluye el simulador, es la semilla para el flujo aleatorio predeterminado. Se deja el valor por defecto.
    * *Ends at (ticks)*. Es el número de ticks o pasos de la simulación que tienen que pasar para la simulación termine. Su valor deberá estar entre 1 y 2000.
    * *Agent - Susceptible Count*. Es el número de agentes de tipo *Susceptible* que existen en un inicio en la red de agentes. En la visualización tiene color gris.
    * *Agent - Believer Count*. Es el número de agentes de tipo *Believer* que existen en un inicio en la red de agentes. En la visualización tiene color azul.
    * *Agent - FactChecker Count*. Es el número de agentes de tipo *FactChecker* que existen en un inicio en la red de agentes. En la visualización tiene color rojo.
    * *Hoaxes - Alpha - Hoax Credibility*. Corresponde a la probabilidad de credibilidad del bulo. Debe ser un número en el intervalo $\alpha \in [0,1)$.
    * *Hoaxes - Beta - Spreading Rate*. Corresponde a la probabilidad de la tasa de difusión. Debe ser un número en el intervalo $\beta \in [0,1]$.
    * *Hoaxes - pVerify*. Corresponde a la probabilidad de verificación, $p_v$, y es un número en el intervalo $p_v \in [0,1]$.
    * *Hoaxes - pForget*. Corresponde a la probabilidad de olvido, $p_f$, y es un número en el intervalo $p_f \in [0,1]$.

  Otro aspecto a tener en cuenta es que el número total de agentes debe ser al menos 100, y como mínimo debe haber agentes en dos estados distintos.

  Por otro lado, los valores de los parámetros que vienen por defecto corresponden a los valores observados en el modelo de referencia. Estos valores se pueden modificar, teniendo en cuenta que, si se hace click en el icono de guardar (💾) tras modificarlos, se perderán los valores iniciales correspondientes al modelo de referencia. No se perderán si no se hace click en dicho icono.

  <p align="center">
      <img src="../Images/Simulator guide/SimulatorGUI.png" alt="FakeNewsSpreadSim Model." width="80%"/>
  </p>

  * **Cargar la simulación**. Se hace click en el botón *Initialize Run* indicado con una flecha roja en la imagen. Esto hace que se cargue la simulación y que aparezca el botón *Reset* en la botonera anterior. Se pueden observar dos pestañas: *Display* y *State of Agents*. La pestaña *Display* muestra la red de agentes con el algoritmo *Barabási-Albert*, y la pestaña *State of Agents* muestra el gráfico, al inicio vacío. Tanto la red como el gráfico van cambiando a lo largo de la simulación.

  <p align="center">
    <img src="../Images/Simulator guide/InitializeRun.png" alt="FakeNewsSpreadSim Model." width="40%"/>
  </p>
  
  <p align="center">
    <img src="../Images/Simulator guide/LoadSimulator.png" alt="FakeNewsSpreadSim Model." width="80%"/>
  </p>


  * **Inicio de la simulación**. Para comenzar la simulación, se hace click en el botón *Start Run* indicado con una flecha roja en la imagen. Es posible pausar la simulación haciendo click en el botón *Pause* que sustituye al botón anterior mientras la ejecución de la simulación está en marcha. Se podrá reiniciar la simulación haciendo click en el botón *Reset* indicado con una flecha azul en la imagen.
  
  <p align="center">
    <img src="../Images/Simulator guide/StartRun.png" alt="FakeNewsSpreadSim Model." width="30%"/>
  </p>


  *  **Fin de la simulación**. Cuando se llega al número de pasos de simulación o *ticks* definidos en el parámetro correspondiente, la simulación acaba. La red ha cambiado y se puede visualizar el gráfico como se muestra en las imágenes. Además, se genera un archivo .txt con el formato `Model_Output_Data.[año].[mes].[dia].[hora].txt` en el que los datos tienen la forma `["tick","Susceptible Count","Believer Count","FactChecker Count"]`.

  <p align="center">
    <img src="../Images/Simulator guide/EndSimulation1.png" alt="FakeNewsSpreadSim Model." width="80%"/>
  </p>

  <p align="center">
    <img src="../Images/Simulator guide/EndSimulation2.png" alt="FakeNewsSpreadSim Model." width="80%"/>
  </p>


  *  **Repetir la simulación**. Para repetir la simulación, los pasos que hay que seguir son: hacer click en el bótón *Reset*, cambiar los parámetros si se desea, hacer click en el botón *Initialize Run* y hacer click en el botón *Start Run*.
