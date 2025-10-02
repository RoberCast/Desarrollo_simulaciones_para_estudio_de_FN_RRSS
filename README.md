# PFG - Desarrollo de simulaciones para el estudio de la difusión de noticias falsas (fake news) en redes sociales

## University
Universidad Nacional de Educación a Distancia - UNED

## Course
2024-2025

## Abstract
Final degree project.

FakeNewsSpreadSim implements a SBFC (Susceptible, Believer, Fact-Checker) news spread model for simulation in Repast Simphony. 

DataAnalyzer applies a validation methodology that involves running 10 model simulations, averaging the number of agents in each state, and calculating the NRMSE to determine the validity of the model. The model is considered valid if NRMSE < 0.2. DataAnalyzer can validate the implemented model and can also fine-tune the news spread model to what is observed in a real news story from a real news spread dataset.

## Instructions
To simulate and validate the model or to fit the model to a real news story from a real dataset, you need to install Repast Simphony 2.11.0 from its official repository:

https://repast.github.io/download.html

Once the simulator is installed, create a Repast Simphony project named "FakeNewsSpreadSim" and copy the files from the FakeNewsSpreadSim folder of this repository as follows:

```tree
    FakeNewsSpreadSim
    FakeNewsSpreadSim/src
    FakeNewsSpreadSim/src/Agent.java
    FakeNewsSpreadSim/src/AgentNetworkParams.java
    FakeNewsSpreadSim/src/AgentScheduler.java
    FakeNewsSpreadSim/src/AgentState.java
    FakeNewsSpreadSim/src/CustomStyleAgentOGL2D.java
    FakeNewsSpreadSim/src/FNSpreadSimBuilder.java
    FakeNewsSpreadSim/src/JungNetworkGenerator.java
    FakeNewsSpreadSim/src/ProbabilityParams.java
    FakeNewsSpreadSim/batch
    FakeNewsSpreadSim/batch/batch_params.xml
    FakeNewsSpreadSim/src/FakeNewsSpreadSim.rs
    FakeNewsSpreadSim/src/FakeNewsSpreadSim.rs/context.xml
    FakeNewsSpreadSim/src/FakeNewsSpreadSim.rs/parameters.xml
```

```bash    
FakeNewsSpreadSim
    ├── src
    │    ├── Agent.java
    |    ├── AgentNetworkParams.java
    |    ├── AgentScheduler.java
    |    ├── AgentState.java
    |    ├── CustomStyleAgentOGL2D.java
    |    ├── FNSpreadSimBuilder.java
    |    ├── JungNetworkGenerator.java
    │    └── ProbabilityParams.java
    |
    ├── batch
    |    └── batch_params.xml
    |
    └── FakeNewsSpreadSim.rs
         ├── context.xml
         └── parameters.xml
```
         
The parameters.xml file contains the default model parameters. You can now run Repast Simphony in GUI mode (an single simulation) or in batch mode. To validate the model or compare it with real data, run the model in batch mode, choosing the "DataAnalyzer JAR/Resources 
/repast simphony output" as the output folder. Follow the instructions in the "DataAnalyzer JAR/Resources/dataset/README.txt" file to select a valid dataset and place it in the "dataset" folder in that same path. To check for valid commands in DataAnalyzer.jar, you must run the .jar file from the cmd with the "-h" command; this will display the help. Un modelo será válido si NRMSE < 0.2



