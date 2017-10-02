# **Data Analysis Kit**

## Summary


A simple CLI tool to analyze logs of a weather balloon for important information.

## Data Format


`<timestamp>|<location>|<temperature>|<observatory>`

e.g `2014-12-31T13:44|10,5|243|AU`

### Assumptions
`location` is a co-ordinate x,y *on a plane* . And x, and y are natural numbers in observatory specific units. 

## Prerequisites

- Git
- Java 8
- Gradle 3+
- Linux or MacOS
- GNU sort


## Installation

```
$git clone https://mattshen@bitbucket.org/mattshen/data-analysis-kit.git
$./gradlew build test
```

Binary distribution would be available in `${projectDir}/build/distributions` after building

## Running Instructions (with source code)


### Help
```
gradlew run -q -Dexec.args="-h"
```

### Generating test data

```
gradlew run -q -Dexec.args="-t generate"
```

### Sorting by GNU sort

- Linux
```
LC_ALL=C sort --parallel=4  -g -t "|" -k 1 data_file.txt -o sorted_data_file.txt
```
- Mac OS (Install `gsort` via Homebrew first)
```
LC_ALL=C gsort --parallel=4  -g -t "|" -k 1 data_file.txt -o sorted_data_file.txt
```

### Analyzing

#### example 1
```
gradlew run -q -Dexec.args="-t analyze"
```

#### example 2
```
gradlew run -q -Dexec.args="-t analyze -o normalized_data.txt"
```

#### example 3
```
gradlew run -q -Dexec.args="-t analyze -o normalized_data.txt --unit-temperature kelvin --unit-distance m"
```


## Improvements

- Separate normalizing code from `Analyzer`

## Some Performance Test Results

[Performance Test](Performance-Test.md)
