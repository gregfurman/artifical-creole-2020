# Evolving an Artificial Creole

## Abstract
There has been a significant amount of research on computational modeling
of language evolution to understand the origins and evolution of communication.
However, there has relatively been relatively little computational modeling
of environmental factors that enable the evolution of creole languages,
specifically, modeling lexical term transmission between intersecting
language groups, within the context of artificial creole language
evolution.  This study used an iterative agent-based simulation to
investigate the impact of population size and lexical similarity
of interacting language groups on the evolution of an artificial creole lexicon.
We applied the synthetic methodology, using agent-based artificial
language evolution as an experimental platform to investigate two
objectives.  First, to investigate the impact of population size of
interacting groups (with differing lexicons) on the evolution of a
common (creole) lexicon.  Second, to evaluate the concurrent impact of
lexical similarity between interacting agent groups on the evolution
of a creole lexicon.


## Running the simulator

In order to run the language evolution simulator enter the following lines of code into bash: 

'''
bash run
cd data
java -jar Simulator.jar [experiment indexes to run/leave empty for all experiments]
'''

The simulator takes in experiment numbers 1-48. One can either enter a list of experiment numbers i.e 1 2 3 ... or leave the experiment number blank for all 48 experiments to be run.

Look at *index.txt* for a list of experiment parameters.
