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
To simulate and validate the model, or to adjust the model to a real news story using a real dataset, you must first install Repast Symphony 2.11.0 from its official repository:

https://repast.github.io/download.html

Once the simulator is installed, create a Repast Symphony project called "FakeNewsSpreadSim" and copy the files from the FakeNewsSpreadSim folder from this repository as follows:

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

The .java files comprise the SBFC news dissemination  model. The *parameters.xml* file contains the model's default parameters, the *batch_params.xml* file contains the parameters for batch execution of the simulator, and the *context.xml* file contains the necessary instructions for the simulator. The next step is to configure the simulator. To do this, you can follow the instructions found [here](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_SETUP.md).

The next step is to place the *DataAnalyzer* in your desired location. You can find it [here](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/tree/main/DataAnalyzer%20JAR). The directory structure for DataAnalyzer, which must be followed, is as follows:

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

After completing the previous steps, you can perform the following actions:

* Run a simulation in GUI mode.
* Validate the implemented SBFC news dissemination model.
* Adjust the SBFC news dissemination model using data extracted from a real news story and validate the adjustment.

*Considerations*: The model probabilities must be manually adjusted until a valid fit is obtained, as this is the procedure used in the adopted validation methodology. Resources are available for learning how to use the simulator and the data analyzer.

* [Simulator Guide](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_GUIDE.md).
* [Data Analyzer Guide, DataAnalyzer](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/DATAANALYZER_GUIDE.md).

## Demo
The following two experiments are described: the validation of the implemented SBFC news dissemination model and the fitting of this model to real news data obtained from a real dataset. Both experiments were run on a Windows 11 Pro (x64) system, version 25H2.

* **Validation of the implemented SBFC news dissemination model**. The following video demonstrates that the implemented model is consistent with the reference model, as it ultimately achieves an NRMSE < 0.2 and the behavior is identical with the same parameters. Specifically, the number of agents in the Believer state drops rapidly due to fact-checking, while the number of agents in the FactChecker state increases rapidly. Conversely, the number of agents in the Susceptible state remains constant because, over time, the news may be forgotten. The execution parameter values ​​are shown in the video.

https://github.com/user-attachments/assets/4b6e9c38-2dd0-4205-852f-579b85c589e1

**Fitting the SBFC news dissemination model to data extracted from a real news story and validating the fit.** The following video demonstrates that the news dissemination model can be fitted to real data extracted from a real dataset. The validation obtained is an NRMSE < 0.2, therefore, the fit is valid. The graph shown at the end of the video is a demonstrative example, as it was generated with a single run of the simulator, but it shows that the number of Believer agents approximates the number of users who believed the analyzed real news story. The values ​​of the execution parameters can be seen in the video.

https://github.com/user-attachments/assets/c9b59f17-de4d-4ccc-b10f-bf2f08ca510c

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



