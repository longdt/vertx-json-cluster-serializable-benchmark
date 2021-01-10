### Benchmark Result:
BatchJsonBenchmark: 100 small json objects
```
Benchmark                                         Mode  Cnt     Score    Error  Units
JsonObjectBenchmark.readFromBuffer_JsonObject    thrpt   25  2401.014 ± 45.937  ops/s
JsonObjectBenchmark.readFromBuffer_MyJsonObject  thrpt   25  2437.243 ± 28.637  ops/s
JsonObjectBenchmark.writeToBuffer_JsonObject     thrpt   25  2510.820 ± 16.262  ops/s
JsonObjectBenchmark.writeToBuffer_MyJsonObject   thrpt   25  2669.995 ±  9.591  ops/s
```
RealJsonBenchmark: a json object from https://www.json-generator.com/
```
Benchmark                                         Mode  Cnt       Score      Error  Units
JsonObjectBenchmark.readFromBuffer_JsonObject    thrpt   20  256088.840 ± 2082.391  ops/s
JsonObjectBenchmark.readFromBuffer_MyJsonObject  thrpt   20  268095.985 ± 1448.772  ops/s
JsonObjectBenchmark.writeToBuffer_JsonObject     thrpt   20  204292.082 ±  619.726  ops/s
JsonObjectBenchmark.writeToBuffer_MyJsonObject   thrpt   20  220900.651 ± 4215.101  ops/s
```
### Environment
* Os: Windows 10
* Jvm: openjdk version "11.0.9" 2020-10-20
* Cpu: Processor	Intel(R) Core(TM) i5-8265U CPU @ 1.60GHz, 1801 Mhz, 4 Core(s), 8 Logical Processor(s)
* Ram: 8G
