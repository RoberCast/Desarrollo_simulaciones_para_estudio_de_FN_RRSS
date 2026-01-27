# Simulator setup
After creating the Repast Symphony project and placing the files indicated in the [README.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/README.md#instructions) file in their corresponding locations, proceed to complete the simulator configuration. This process can be viewed in a [video](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_SETUP.md#simulator-setup-video) at the end of this document. The steps for the simulator configuration process are as follows:

1. **Running the simulator in GUI mode (single run)**. In the Eclipse IDE installed with Repast Symphony, run *FakeNewsSpreadSim Model*. Open the simulator in GUI mode and proceed with its configuration.

<p align="center">
  <img src="../Images/Simulator setup/EclipseRun.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

2. **Scenario Tree tab**. With the simulator open, click on the *Scenario Tree* tab, which contains the simulation settings. The configurable sections are: *Charts*, *Data Loaders*, *Data Sets*, *Displays*, *Text Sinks*, and *User Panel*. Configure all of them except *User Panel*.

<p align="center">
  <img src="../Images/Simulator setup/ScenarioTree.png" alt="FakeNewsSpreadSim Model." width="35%"/>
</p>

3. **Data Loaders**. Right-click on *Data Loaders* and then click on *Set Data Loader*. In the next window, select the *Custom ContextBuilder Implementation* option and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/DataLoader1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

The next window is not modified, and you click on *Next* and then on *Finish*.

<p align="center">
  <img src="../Images/Simulator setup/DataLoader2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

4. **Data Sets**. Right-click on *Data Sets* and then click on *Add Data Set*. In the next window, give the dataset a name, for example, *Social Network Data Set*, and leave *Aggregate* as the dataset type. Click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/DataSets1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, you can see the tabs *Standard Sources*, *Method Data Sources*, and *Custom Data Sources*. In the *Standard Sources* tab, leave the *Tick Count* option selected.

<p align="center">
  <img src="../Images/Simulator setup/DataSets2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the *Method Data Sources* tab, add the following by clicking on *Add* and double-clicking on each field to fill it in:

  * *Source Name* = Susceptible count, *Agent Type* = Agent, *Method* = isSusceptible y *Aggregate Operation* = Sum.
  * *Source Name* = Believer count, *Agent Type* = Agent, *Method* = isBeliever y *Aggregate Operation* = Sum.
  * *Source Name* = FactChecker count, *Agent Type* = Agent, *Method* = isFactChecker y *Aggregate Operation* = Sum.

<p align="center">
  <img src="../Images/Simulator setup/DataSets3.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

The *Custom Data Sources* tab is left as is and *Next* is clicked.

<p align="center">
  <img src="../Images/Simulator setup/DataSets4.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

The following window is left as is and you click on *Finish*.

<p align="center">
  <img src="../Images/Simulator setup/DataSets5.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

5. **Displays**. Right-click on *Displays* and then click on *Add Display*. In the next window, give the display a name, for example, *Social Network Display*. In the projections, add *Social network* and *space*, in that order, and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Displays1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, add *Agent* and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Displays2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, under *Style Class*, select the *fakeNewsSpreadSim.CustomStyleAgentOGL2D* option and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Displays3.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, do not change anything and click on *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Displays4.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, leave the *repast.simphony.visualizationOGL2D.DefaultEdgeStyleOGL2D* option selected and click the button to the right of it, which is used to edit the style, in this case, of the network edges. In the *Edge Thickness* option, set *Value* to 0.5 and change the color to gray (the default is green). Click *OK* and then *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Displays5.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window there are no changes and you click on *Finish*.

<p align="center">
  <img src="../Images/Simulator setup/Displays6.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

6. **Text Sinks**. Right-click on *Text Sinks* and then click on *Add File Sink*. In the next window, give the *File Sink* a name, for example, *File Sink - Count*. Then select *tick*, *Susceptible count*, *Believer count*, and *FactChecker count*, in that order, and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/TextSinks1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, you choose a name for the output file or leave the default name. In this configuration manual, we'll change the name to *Model_Output_Data.txt*. These output files are saved in the project's *repast-licences* folder. Then click *Finish*.

<p align="center">
  <img src="../Images/Simulator setup/TextSinks2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

7. **Charts**. Right-click on *Charts* and then click on *Add Time Series Chart*. In the next window, give the chart a name, for example, *Spread of fake news - Time Series Chart*, and click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Charts1.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, select *Believer count*, *FactChecker count*, and *Susceptible count*. To adhere to the SBFC model, choose the following colors:

  * Believer = Blue.
  * FactChecker = Red.
  * Susceptible = Grey.

These colors can be changed by double-clicking on the colored box. Once the colors are selected, click *Next*.

<p align="center">
  <img src="../Images/Simulator setup/Charts2.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

In the next window, give the chart a title, for example, *State of Agents*. Choose a label for the Y-axis, for example, *Agents*, and then click *Finish*.

<p align="center">
  <img src="../Images/Simulator setup/Charts3.png" alt="FakeNewsSpreadSim Model." width="60%"/>
</p>

To finalize the setup, it's important to click on the simulator's floppy disk icon, 💾. This will save the settings you've entered.

## Simulator setup video
Here is a video of the simulator setup process seen earlier.

https://github.com/user-attachments/assets/e15e03fc-b29b-4ee4-ab1c-15c2c11c9eba
