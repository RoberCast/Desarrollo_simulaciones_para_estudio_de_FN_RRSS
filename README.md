# PFG - Desarrollo de simulaciones para el estudio de la difusión de noticias falsas (fake news) en redes sociales

## University
Universidad Nacional de Educación a Distancia - UNED

Escuela Técnica Superior de Ingeniería Informática

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

```bash    
FakeNewsSpreadSim
    ├── src
    │    └── fakeNewsSpreadSim
    │          ├── Agent.java
    |          ├── AgentNetworkParams.java
    |          ├── AgentScheduler.java
    |          ├── AgentState.java
    |          ├── CustomStyleAgentOGL2D.java
    |          ├── FNSpreadSimBuilder.java
    |          ├── JungNetworkGenerator.java
    │          └── ProbabilityParams.java
    |
    ├── batch
    |    └── batch_params.xml
    |
    └── FakeNewsSpreadSim.rs
         ├── context.xml
         └── parameters.xml
```
         
The parameters.xml file contains the default model parameters. You can now run Repast Simphony in GUI mode (an single simulation) or in batch mode. To validate the model or fit it with real data, run the model in batch mode, choosing the "DataAnalyzer JAR/Resources 
/repast simphony output" as the output folder. Follow the instructions in the "DataAnalyzer JAR/Resources/dataset/README.txt" file to select a valid dataset and place it in the "dataset" folder in that same path. To check which commands are valid in DataAnalyzer, you must run the DataAnalyzer.jar file from the cmd with the "-h" option, this will display the help. Finally, DataAnalyzer will display the analysis result.

## Demo
Below are images of the execution of the SBFC news diffusion model in Repast Simphony and the execution of the DataAnalyzer validation tool.

* Execution of the SBFC news diffusion model in Repast Simphony.
  
![modelo](Images/FakeNewsSpreadSimRun.png)
  
* Agent status after a simulation.

![estado_agentes](Images/StateOfAgents.png)
  
* Result of validating the model fit to a real news story using a real dataset.

![DataAnalyzer](Images/DataAnalyzerResult.png)  

## License
This project is licensed under the **GNU General Public License v3 (GPLv3)**.

© 2025 Roberto Castillejo Embid.  
This repository contains original code developed for academic purposes.  
You may freely use, modify, and redistribute it under the same license terms (GPLv3).

## Credits
### FakeNews dissemination model
E. Sulis and M. Tambuscio. (2020, Oct.) Simulation of misinformation spreading processes
in social networks: an application with netlogo. Access: 2025-08-14. [Online]. Available:
https://ieeexplore.ieee.org/document/9260064/ 

### Validation methodology
A. Gausen, W. Luk, and C. Guo. (2021, Jun.) Can we stop fake news? using agent-based
modelling to evaluate countermeasures for misinformation on social media. Access:
2025-08-14. [Online]. Available: https://workshop-proceedings.icwsm.org/pdf/2021_63.pdf

## How to cite this work
If you use this project, please cite it using the DOI:

[https://doi.org/10.5281/zenodo.17360338](https://doi.org/10.5281/zenodo.17360338)

You can also use the following BibTeX entry:

```bibtex
@misc{RoberCast2025,
  author = {Roberto Castillejo Embid},
  title = {Desarrollo de simulaciones para el estudio de la difusión de noticias falsas (fake news) en redes sociales},
  year = {2025},
  publisher = {Zenodo},
  doi = {10.5281/zenodo.17360338},
  url = {https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS}
}



