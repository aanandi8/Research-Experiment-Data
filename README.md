# My Personal Project

## Research (Experiment) Data

The aim of the  project is to be able to capture all the data a **research assistant** runs. This includes:
 - experiments the research assistant might be part of
 - Capturing all the data of different participants that are participating in any of the experiments
 
 
 As I am  interested in *research* and understand how much time and effort goes into the saving data, I wish to 
 develop something that would help other researchers too. 
 
## User Stories
1) As a user, I want to be able to view all participants' names in a specific experiment 
2) As a user, I want to be able  to add a participant to a specific experiment 
3) As a user, I want to be able to view a participants relevant data 
4) As a user, I wish to be able to remove a participant from an experiment
5) As a user, I wish to be able to add a data entry to a particular participants' data
6) As a user, I want to be able to save all participants in an experiment to file
7) As a user, I want to be able to optionally load my participants from file when the program starts

## Instructions for Grader
1) You can generate the first event by writing the participant's name and id on the required textbox and then clicking 
the add participant button. (Note: name and id of participants have to be different from those already added to expt,
which includes both saved participants as well as participants just being added). You can also remove a participant by 
typing the name and id of participant already added and then pressing remove participant.
2) You can generate the second required event by writing a specific dataEntry on the third textbox and clicking input 
dataEntry for participant of that id. (Note: unless a participant is added to an experiment you cannot update their 
data entry)
3) You can trigger my audio component when pressing any of the buttons.
4) You can save the state of my application by pressing the save participant button. The name, id will get saved (and 
mean if you've inputted a dataEntry, otherwise mean will get saved as 0)
5) You can reload the state of my application by pressing view names, it will show the all the names of participants
saved before and the ones just added. If you re-run the program and run the application again, on pressing the view names
button all saved participants will get displayed in a pop-up menu. You can also see the mean of a particular participant saved
by typing id of participant you want to check and then pressing view mean. If participant with that id is not present,
mean will be displayed as 0 in the popup menu. 



Overall note: You can't press Input dataEntry without filling in the input data and input id textbox. Also, you can't 
press the view mean button without having the input ID textbox filled. 