# Simulator guide
With the simulator properly configured, see the [SIMULATOR_SETUP.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_SETUP.md#simulator-setup) document, we will now explain how the simulator works. There are two simulation modes: GUI mode, for a single simulation, and batch mode, which runs $n$ times. Each of these modes is explained below.

## GUI mode simulation
This execution mode consists of running the simulation of the SBFC news dissemination model implemented through a GUI only once. This method allows you to observe how the agent network changes as the simulation progresses and also to view the graph generated with the agent state values; that is, at time $t$, you would see the number of agents in each possible state. This execution serves to demonstrate that the simulated model behaves the same way as the reference model. To execute this simulation mode, follow these steps:

1. **Execution in Eclipse IDE**. In the Eclipse IDE that was installed with Repast Symphony, run *FakeNewsSpreadSim Model*. This opens the simulator window.

<p align="center">
    <img src="../Images/Simulator setup/EclipseRun.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

2. **Input parameters**. The *Parameters* tab contains the input parameters for the SBFC news dissemination model used. Each of these parameters is explained below.

   * *Default Random Seed*. This parameter is included by the simulator; it is the seed for the default random flow. Leave the default value.
   * *Ends at (ticks)*. This is the number of ticks or simulation steps that must occur for the simulation to end. Its value must be between 1 and 2000.
   * *Agent - Susceptible Count*. This is the number of *Susceptible* type agents that initially exist in the agent network. It is displayed in gray.
   * *Agent - Believer Count*. This is the number of *Believer* agents initially present in the agent network. It is displayed in blue.
   * *Agent - FactChecker Count*. This is the number of *FactChecker* agents initially present in the agent network. It is displayed in red.
   * *Hoaxes - Alpha - Hoax Credibility*. This corresponds to the probability of the hoax's credibility. It must be a number in the range $\alpha \in [0,1)$.
   * *Hoaxes - Beta - Spreading Rate*. This corresponds to the probability of the spread rate. It must be a number in the range $\beta \in [0,1]$.
   * *Hoaxes - pVerify*. This corresponds to the verification probability, $p_v$, and is a number in the range $p_v \in [0,1]$.
   * *Hoaxes - pForget*. It corresponds to the probability of forgetting, $p_f$, and is a number in the interval $p_f \in [0,1]$.

Another aspect to consider is that the total number of agents must be at least 100, and there must be at least two agents in different states.

On the other hand, the default parameter values ​​correspond to the values ​​observed in the reference model. These values ​​can be modified, keeping in mind that if you click the save icon (💾) after modifying them, the initial values ​​corresponding to the reference model will be lost. They will not be lost if you do not click the save icon.

<p align="center">
      <img src="../Images/Simulator guide/SimulatorGUI.png" alt="FakeNewsSpreadSim Model." width="100%"/>
</p>

3. **Load the simulation**. Click the *Initialize Run* button, indicated by a red arrow in the image. This loads the simulation and displays the *Reset* button in the previous toolbar. You will see two tabs: *Display* and *State of Agents*. The *Display* tab shows the agent network using the *Barabási-Albert* algorithm, and the *State of Agents* tab displays the graph, which is initially empty. Both the network and the graph change throughout the simulation.

  <p align="center">
    <img src="../Images/Simulator guide/InitializeRun.png" alt="FakeNewsSpreadSim Model." width="35%"/>
  </p>
  
  <p align="center">
    <img src="../Images/Simulator guide/LoadSimulator.png" alt="FakeNewsSpreadSim Model." width="100%"/>
  </p>

4. **Simulation start**. To begin the simulation, click the *Start Run* button, indicated by a red arrow in the image. You can pause the simulation by clicking the *Pause* button, which replaces the previous button while the simulation is running. You can restart the simulation by clicking the *Reset* button, indicated by a blue arrow in the image.
  
  <p align="center">
    <img src="../Images/Simulator guide/StartRun.png" alt="FakeNewsSpreadSim Model." width="40%"/>
  </p>

5. **End of simulation**. When the number of simulation steps or *ticks* defined in the corresponding parameter is reached, the simulation ends. The network has changed, and the graph can be viewed as shown in the images. Additionally, a .txt file is generated in the format `Model_Output_Data.[year].[month].[day].[hour].txt`, where the data is in the form `["tick","Susceptible Count","Believer Count","FactChecker Count"]`.

  <p align="center">
    <img src="../Images/Simulator guide/EndSimulation1.png" alt="FakeNewsSpreadSim Model." width="100%"/>
  </p>

  <p align="center">
    <img src="../Images/Simulator guide/EndSimulation2.png" alt="FakeNewsSpreadSim Model." width="100%"/>
  </p>. 

6. **Repeat the simulation**. To repeat the simulation, the steps to follow are: click on the *Reset* button, change the parameters if desired, click on the *Initialize Run* button and click on the *Start Run* button.


## Batch mode simulation
This execution mode is used to validate the implemented SBFC news dissemination model and to conduct experiments with real data. To run the simulation in batch mode, follow these steps:

1. **Execution in Eclipse IDE**. In the Eclipse IDE installed with Repast Symphony, run *Batch FakeNewsSpreadSim Model*. The simulator's batch execution window will then appear. This window contains the tabs *Model*, *Batch Parameters*, *Hosts*, and *Console*. This guide only covers the first two tabs.
   
  <p align="center">
    <img src="../Images/Simulator guide/BatchRun.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>


2. **Model Tab**. In this tab, you choose the simulation output, which consists of a model parameter file and a batch execution output. If you leave the default path for *Output Directory*, both files are saved in the `/FakeNewsSpreadSim/output` folder. For this guide, click *Browse* and choose the path where the data analyzer, *DataAnalyzer*, saves these files.

  <p align="center">
    <img src="../Images/Simulator guide/ModelTab.png" alt="FakeNewsSpreadSim Model." width="70%"/>
  </p>

3. **Batch Parameters Tab**. This tab allows you to change the model parameter values, but there's a problem entering decimal values. Therefore, **parameter values ​​must be modified directly in the *batch_params.xml* file**. This file allows you to modify the number of batch runs, the number of simulation steps, the initial number of agents for each state, and the model probabilities.
 
  <p align="center">
    <img src="../Images/Simulator guide/BatchParametersTab.png" alt="FakeNewsSpreadSim Model." width="70%"/>
  </p>


4. **Start the Batch Simulation**. Once the values ​​of the batch execution parameters have been modified and saved, and the path where the output files are saved has been selected, click on the *Execute Batch Runs* button indicated by a red arrow in the image.

  <p align="center">
    <img src="../Images/Simulator guide/ExecuteBatch.png" alt="FakeNewsSpreadSim Model." width="35%"/>
  </p>


When the batch execution is complete, the output files will be saved with the names `Model_Output_Data.[year].[month].[day].[hour].batch_param_map.txt` and `Model_Output_Data.[year].[month].[day].[hour].txt`, whose formats are `["run","randomSeed","believer_count","spreadint_rate","ticksfactChecker_countverifying_probsusceptible_countcred_hoaxforgetting_rate"]` and `["run","tickSusceptible CountBeliever CountFactChecker Count"]`, respectively.

It is important that **the names of these files are respected**.
