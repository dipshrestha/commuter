==============================================================
                        How to run
==============================================================
(folder-root)$ java -cp ./out/production/CommuterRailRoad/ com.dipesh.proj.Main input.txt command.txt

where,
    1   input.txt is the weighted edges in graph
    2   command.txt is the list of commands


==============================================================
                    Command Structure
==============================================================

command.txt may have commands in following syntax

1) find distance of route
dist ABC

2) find shortest distance between two nodes
sdist AC

3) find number of different path/trips between two nodes with condition
trip AC node = 3
trip AC dist > 28

NOTE: Assumptions is that distance between nodes are only positive integer


==============================================================
                        Sample
==============================================================

-- sample input.txt --
AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7

-- sample command.txt --
dist ABC
dist AD
trip AC node = 4
sdist AC
dist ADC
dist AEBCD
dist AED
trip CC node <= 3
sdist BB
trip CC dist < 28
