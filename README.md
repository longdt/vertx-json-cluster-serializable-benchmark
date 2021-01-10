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
* jvm: openjdk version "11.0.9" 2020-10-20