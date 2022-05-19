# Bot Route Planner

Application that finds the quickest path between modules.

## Key information about the project
### Construction
The **grid** is constructed as a Set of Modules.
Each **Module** contains a list of **products** that it stores
and its **location** as row and column parameters.
Each **Module** is aware of its:
- **neighbours** and the **time** that it would take to drive onto them,
- the **path** that the "bot" has taken to get to it,
- the **time** that the "bot" took to get to it

### Pathfinding
**Dijkstra's algorithm** is used to find the quickest path between two **modules**.

### Implementation
After reading the files with instructions for creating the grid and 
the instructions for the bot, the grid is created with specified modules 
that store specified products. 

####

Then the modules that store the demanded product are filtered and for
every one found The Dijkstra's algorithm is fired for twice when 
calculating paths from the: 
- starting position to the productModule,
- productModule to the receivingStation

Then the results are summed up, compared to the quickest time yet, 
and interchanged if quicker.

In the end, the number of steps, total time and the 
quickest path are printed.






