# Scala neural network from scratch :fire:

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)

Scala project for AGH Computer science class.

## Quick start :rocket:

Make sure you fulfil **requirements**. Quick start creates local server for open-ai gym with "Cartpole" game and 
starts scala client with new neural network. It trains it and shows results.

```shell script
git clone https://github.com/twrdyyy/scala_project awesome_project
cd awesome_project
chmod u+x example.sh
./example.sh
```

## Requirements :mega:
 - Python3+ :snake:
 - Open ai gym 
 ```shell script
pip3 install gym
```
 - Scala
 - Numsca 
 \
Simply add this line to your **sbt** build
```scala
libraryDependencies += "be.botkop" %% "numsca" % "0.1.7"
``` 




