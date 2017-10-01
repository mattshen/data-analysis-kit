# Data Analysis Kit

## Prerequisites
- Java 8
- Gradle 4
- GNU sort

## Running Instructions

### Generating test data
```
gradlew run -q -Dexec.args="-t generate"
```

### Sorting
- Linux
```
LC_ALL=C sort --parallel=4  -g -t "|" -k 1 data_file.txt -o sorted_data_file.txt
```
- Mac OS (Install `gsort` via Homebrew first)
```
LC_ALL=C gsort --parallel=4  -g -t "|" -k 1 data_file.txt -o sorted_data_file.txt
```

### Analyzing
```
gradlew run -q -Dexec.args="-t analyze"
```
