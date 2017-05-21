# ammonite-failure-case
Minimal project to reproduce https://github.com/lihaoyi/Ammonite/issues/476 .

Just run `sbt clean test` to see.

You can see the similarities/differences with sbt's repl as follows:
* run `sbt console`
* paste in the 6 lines starting [here](https://github.com/Alex-At-Home/ammonite-failure-case/blob/master/src/test/scala/example/AmmoniteFailureSpec.scala#L12) and observe it fail
* change the scalac settings by changing `failInSbtConsole` to `false` [here](https://github.com/Alex-At-Home/ammonite-failure-case/blob/master/build.sbt#L10)
* repeat the first 2 steps, this time it will succeed
