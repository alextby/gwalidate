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

<h3>Key Features</h3>
 - Per-widget style of configuration
 - Dynamic/programmatic configuration and control
 - Cross-field validation rules
 - Faily easy to integrate in a typical GWT view; little extra code required
 - A number of basic validation rules bundled
 - GIN-powered

<h4>TODO</h4>
 - Add support for rule priorities
 - Redesign the internal widget validation states with a finite-state automata

<h3>Build Instructions</h3>
 - `gwalidate-playground` has `-P dev` for faster developer builds (less permutations and tests skipped)
 - GWT 2.7 is currently the target version. GWT 2.8 is not (yet) supported.

<h3>Standalone Demo</h3>
Is for looking into the usage details of the framework. Steps to launch at localhost:
```sh
  mvn clean install
  cd gwalidate-playground/
  mvn jetty:deploy-war
```
check out <a href="http://localhost:10008">http://localhost:10008/</a>
