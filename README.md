#Pools To Bracket Transition Algorithm
##

The transition from pools to bracket in a tournament must be handled 
carefully in order to prevent a rematch between people in the same pool 
until deep in the bracket.  This is obtained by following two rules 
when placing entrants into the bracket.

  * Each bracket section of X players contains a 1st seed vs (X)th seed 
   match-up, whose winner plays the winner of a 2nd seed vs (X-1)th 
   seed match-up, etc. where X is the number of players who advanced 
   from each pool.
  * Each bracket section of Y players contains exactly Y different 
   players from Y different pools, where Y is the total number of pools 
   in the round of pools immediately preceding bracket.

For more information about handling the transition from pools to 
bracket see the article written by Juggleguy on 
[SmashBoards](http://smashboards.com/threads/how-do-i-transition-from-pools-to-bracket-toing.355338)

##Usage
This program will arrange a set of Entrants in the order they should 
appear in a bracket so that pool rematches do not occur until very late 
in the bracket.  This also orders the entrants so that the pool 1 seed 
1 is the final bracket 1st seed and the pool 2 seed 1 is the 2nd seed 
and so on and so forth until the final pool's 1st seed is the last 
seeded entrant.  

The program is run as so<b>:</b>

`java SortEntrants`

So it will take a file name as a command line argument and be used like 
so<b>:</b>

`java SortEntrants [filename]`

##Input

This program runs on a text file specified by the user in the format
poolnumber seednumber\n

`1 2\n`

##TODO
###Testing
This program has been tested with 8 pool and 4 people make it out of 
pools and with 4 pools and 4 make it out.  This program will definitely 
break if more than 4 people out but should work for less than 4 out. 
Further Testing is required.
###Input
The input paser must be eexpanded to read in a name and there should also 
be a different delimiter used (opposed to space) so that names with spaces 
will be accepted.
###Python Re-Implementation
This program can also be re-implemented in python so that it can be 
used in conjunction with the python registration software for quick and 
meaningful output.   
