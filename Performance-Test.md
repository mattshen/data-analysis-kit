# Performance Tests

Tests were run on a VM with Intel i5-4690K CPU, 7GB spare MEM. 

## Test 1

Generating test data in parallel
```
$time ./data-analysis-kit -t generate -n 500000000 -p
Generating 500,000,000 records into file ./data_file.txt ...

real	11m23.533s
user	11m16.780s
sys	13m51.628s
```

## Test 2

Sorting with GNU sort

```
$time LC_ALL=C sort --parallel=4  -g -t "|" -k 1 data_file.txt -o sorted_data_file.txt -T /media/shenm/bigfolder

real	20m19.800s
user	34m51.032s
sys	2m27.048s
```

## Test 3

Analyzing and normalizing.

```
$time ./data-analysis-kit -t analyze -o normalized_data_file.txt
Analyzing file ./sorted_data_file.txt ...
Normalized file saved to normalized_data_file.txt

============== Summary ==============
Minimum Temperature (celsius): -50
Maximum Temperature (celsius): 50
Sum Temperature (celsius): -141139756
Mean Temperature (celsius): 0
Count: 500000000
TraveledDistance (meters): 8508816804100218
Observations per post: {US=124996965, AU=124991623, FR=125014966, OTHER=124996446}


real	20m12.117s
user	17m40.020s
sys	2m12.016s
```

## Test 4

Analyzing only

```
$time ./data-analysis-kit -t analyze
Analyzing file ./sorted_data_file.txt ...

============== Summary ==============
Minimum Temperature (celsius): -50
Maximum Temperature (celsius): 50
Sum Temperature (celsius): -141139756
Mean Temperature (celsius): 0
Count: 500000000
TraveledDistance (meters): 8508816804100218
Observations per post: {AU=124991623, US=124996965, FR=125014966, OTHER=124996446}


real	5m7.852s
user	3m22.736s
sys	1m46.092s
```

