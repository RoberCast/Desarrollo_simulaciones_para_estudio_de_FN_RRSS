# Data Analyzer Guide, DataAnalyzer
The data analyzer uses the previously adopted validation methodology to validate the results of the SBFC news diffusion model simulation. This methodology involves running the simulation $n$ times, calculating the mean number of agents in each possible state, and finding the root mean square error (NRMSE). If NRMSE < 0.2, then the model fit is considered valid.

After placing the files and folder tree as indicated in the file [README.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/README.md), and after running the simulator in batch mode (see the file [SIMULATOR_GUIDE.md](https://github.com/RoberCast/Desarrollo_simulaciones_para_estudio_de_FN_RRSS/blob/main/Docs/SIMULATOR_GUIDE.md), the data analyzer is executed from the command line (cmd) using the syntax `java -jar DataAnalyzer.jar [parameters]`. The DataAnalyzer execution commands are explained below.

## Execution with one parameter
This parameter consists of the value `-h`. The execution displays the help. If this value is not used, the analyzer displays a parameter error. An example can be seen in the figure.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/1Parameter.png" alt="FakeNewsSpreadSim Model." width="90%"/>
  </p>

## Execution with two parameters
This run is used to validate the SBFC news diffusion model. It receives data from the output of the simulator's batch run and calculates the NRMSE. If the calculated value is less than 0.2, the model is considered valid, meaning it conforms to the reference model. The parameters to be entered are, in this order, the run parameter file and the simulation result file from the simulator's batch run.

La ejecución muestra un error si no son los archivos correctos, no están en el orden indicado o se les ha cambiado el nombre. Tras la ejecución se muestra el resultado del análisis, donde se pueden ver los parámetros de la simulación, número de *ticks*, número de ejecuciones, el promedio de agentes en cada estado después de $n$ ejecuciones y el resultado del análisis. Se puede ver un ejemplo en la siguiente figura.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/2Parameter.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>

## Execution with three parameters
This execution is used for analysis with real data. In addition to what was explained in the two-parameter execution, the file containing the real data is added to the parameter set. In this file of real news, each line is a real news item with a structure like `["id", "news_url", "tittle", "tweet_ids"]`, where `id` is a news ID, `news_url` is the URL of the news item, `tittle` is the title of the news item, and `tweet_ids` is a list of numeric codes where each code represents the ID of the user who shared the news item.

If the real data file does not exist, an error is reported. If it exists, a valid news ID will be requested. At this point, you can type `quit` to exit the program or enter a valid news ID. If the ID does not exist within the dataset, an error will be reported. If it exists but the number of believers in the news is greater than the number of agents in the simulation, another valid news ID will be requested. If the news ID is correct, the NRMSE is calculated, and the analysis result is printed as in the previous case. The information displayed is the same, but the number of agents who believe in the news with a given news ID is added. An example can be seen in the following figure.

  <p align="center">
    <img src="../Images/DataAnalyzer guide/3Parameter.png" alt="FakeNewsSpreadSim Model." width="60%"/>
  </p>
