GWalidate
===============
<p>
GWalidate is a simple and lightweight client-side GWT validation framework. <br/>
It aims to address the shortcomings of the GWT's built-in <i>javax.validation.*</i> support allowing the client code
to specify extended validation configurations in a per-widget manner.
</p>
<p>
Project Pages:<br>
<a href="http://alextby.github.io/gwalidate/">http://alextby.github.io/gwalidate/</a>
</p>

<h3>Build Instructions</h3>
 - Java7 is preferably the one to go with.
 - Building `gwalidate-core` with Java8 won't let the tests run, so make sure to `-DskipTests=true` if Java8 is required (the rest of the code compiles/runs as expected).
 - `gwalidate-playground` has `-P dev` for faster developer builds (less permutations and tests skipped)

<h3>Standalone Demo</h3>
```sh
  mvn clean install
  cd gwalidate-playground/
  mvn jetty:deploy-war
```
check out <a href="http://localhost:10008">http://localhost:10008/</a>
