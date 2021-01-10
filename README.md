### Benchmark Result:
```
Benchmark                                         Mode  Cnt     Score    Error  Units
JsonObjectBenchmark.readFromBuffer_JsonObject    thrpt   25  2401.014 ± 45.937  ops/s
JsonObjectBenchmark.readFromBuffer_MyJsonObject  thrpt   25  2437.243 ± 28.637  ops/s
JsonObjectBenchmark.writeToBuffer_JsonObject     thrpt   25  2510.820 ± 16.262  ops/s
JsonObjectBenchmark.writeToBuffer_MyJsonObject   thrpt   25  2669.995 ±  9.591  ops/s
```
### Environment
* Os: Windows 10
* Jvm: openjdk version "11.0.9" 2020-10-20
* Cpu: Processor	Intel(R) Core(TM) i5-8265U CPU @ 1.60GHz, 1801 Mhz, 4 Core(s), 8 Logical Processor(s)
* Ram: 8G
