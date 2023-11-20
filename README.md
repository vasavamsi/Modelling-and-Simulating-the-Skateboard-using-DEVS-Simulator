# Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator
This project is completed as a part of assignment for CSE-561 Modelling, Simulation and Theory of applications. This work leverages the state-of-the-art DEVS simulator to develop the model for Skateboard Generator, Skateboard Transducer and Skateboard models. We also couple these models to create the couple model.

## Installations:

This project utilizes the DEVS Simulator package and needs the same to be installed in your device for running the project. The Source code along with documentation for DEVS Simulator version 6.1.0 can be found [here](https://sourceforge.net/projects/devs-suitesim/files/DEVS_Suite_6.1.0/)  Some of the prior neccessities to be installed are either of JRE versions from 1.8, 11.02, 11.10 and 15.02 and Maven. It is also recommended to install 'Eclipse IDE for JAVA developers', you can find the installation details for the same [here](https://www.eclipse.org/downloads/packages/installer).

## Running the simulation:

After downloading the Simulator packaged in your local device, navigate to the folder "DEVS-Suite-Mixerd_Win64_6.1.0\Models\Component\BasicProcessor" and place the .java files from this repository here. Follow the steps as below:

Step 1 - Expand the Package Explorer in the left of the IDE and double click on controller pack (as shown in image below). Now, hower over 'Run As' option and select '1 Java Application'.

![Step 1](https://github.com/vasavamsi/Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator/assets/58003228/651662f2-61a1-4615-af02-3b022762d188)

Step 2 - Select 'Component Models' in the UI. 

![image](https://github.com/vasavamsi/Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator/assets/58003228/92d630c4-db86-41b7-9d58-d50733ed6104)

Step 3 - Now select the Package as 'Component.BasicProcessor' and Model as any of the desired models provided in this pack. Check the boxes for SimView for Visualization of the model and Tracking to track the simulation.

![image](https://github.com/vasavamsi/Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator/assets/58003228/68cf88f8-682f-4f3c-91cd-8e579ff691f9)

Step 4 - Now provide the desired inputs and hit the 'Step' button to run the simulation for one step and so on. You can set n desired steps using 'Step(n)' button.

![image](https://github.com/vasavamsi/Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator/assets/58003228/674297f6-b80b-49c7-ac34-22a7614b7e74)

**Note** - The inputs for each model and coupled model can be modified or increased in the code using the function _addTestInput_.

Implementation of coupled model:

![image](https://github.com/vasavamsi/Modelling-and-Simulating-the-Skateboard-using-DEVS-Simulator/assets/58003228/bc9568ab-e215-493c-bb9f-551746feb7e9)

