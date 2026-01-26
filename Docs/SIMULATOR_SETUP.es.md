# Configuración del simulador
Después de crear el proyecto de Repast Simphony y tras colocar los archivos indicados en el [README.es.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/README.es.md#instrucciones) en su lugar correspondiente, se procede a completar la configuración del simulador. Para ello, se realizan los pasos siguientes:

1. **Ejecución del simulador en modo GUI (ejecución única)**. En el IDE Eclipse que se ha instalado con Repast Simphony, se ejecuta *FakeNewsSpreadSim Model*. Se abre el simulador en modo GUI y se procede a su configuración.
  
<p align="center">
  <img src="../Images/Simulator_setup/EclipseRun.png" alt="FakeNewsSpreadSim Model." width="50%"/>
</p>

2. **Pestaña Scenario Tree**. Con el simulador abierto, se hace click en la pestaña *Scenario Tree*, que contiene la configuración de la simulación. Los apartados que se pueden configurar son: *Charts*, *Data Loaders*, *Data Sets*, *Displays*, *Text Sinks* y *User Panel*. Se configuran todos ellos excepto *User Panel*.

<p align="center">
  <img src="../Images/Simulator_setup/ScenarioTree.png" alt="FakeNewsSpreadSim Model." width="35%"/>
</p>

3. **Data Loaders**. Se hace click con el botón derecho en *Data Loaders* y después se hace click en *Set Data Loader*. En la siguiente ventana se elige la opción *Custom ContextBuilder Implementation* y se hace click en *Next*. 
   
<p align="center">
  <img src="../Images/Simulator_setup/DataLoader1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

La siguiente ventana no se modifica y se hace click en *Next* y después en *Finish*.

<p align="center">
  <img src="../Images/Simulator_setup/DataLoader2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

4. **Data Sets**. Se hace click con el botón derecho en *Data Sets* y después se hace click en *Add Data Set*. En la siguiente ventana se le da un nombre al dataset, por ejemplo, *Social Network Data Set*, y se deja *Aggregate* como tipo de dataset. Se hace click en *Next*.

<p align="center">
  <img src="../Images/Simulator_setup/DataSets1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

En la siguiente ventana, se pueden observar las pestañas *Standard Sources*, *Method Data Sources* y *Custom Data Sources*. En la pestaña *Standard Sources* se deja marcada la opción *Tick Count*. 

<p align="center">
  <img src="../Images/Simulator_setup/DataSets2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

En la pestaña *Method Data Sources* se añade lo siguiente haciendo click en *Add* y haciendo click en cada campo para realizar la selección:

  * *Source Name* = Susceptible count, *Agent Type* = agent, *Method* = isSusceptible y *Aggregate Operation* = sum.
  * *Source Name* = Believer count, *Agent Type* = agent, *Method* = isBeliever y *Aggregate Operation* = sum.
  * *Source Name* = FactChecker count, *Agent Type* = agent, *Method* = isFactChecker y *Aggregate Operation* = sum.

<p align="center">
  <img src="../Images/Simulator_setup/DataSets3.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

La pestaña *Custom Data Sources* se deja como está y se hace click en *Next*.

<p align="center">
  <img src="../Images/Simulator_setup/DataSets4.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

La siguiente ventana se deja como está y se hace click en *Finish*.

<p align="center">
  <img src="../Images/Simulator_setup/DataSets5.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

5. **Displays**. Se hace click con el botón derecho en *Displays* y después se hace click en *Add Display*.
