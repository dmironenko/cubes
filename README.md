Solution of task described in TASK.md

Technology stack:
- Java 7

Downloading project:
git clone https://github.com/dmironenko/cubes.git

Building project:
 - cd cubes
 - mvn clean install

Application is assembled to jar with manifest using maven jar plugin

Running project:
To run project you have to specify
- arg[0] - full path to file with cube. If not specified searches solution for blue cube

Example of running:
java -jar target/cubes-1.0.jar /srv/blue_cube.txt
