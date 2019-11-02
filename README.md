# Γ-☕️
Read “gamma-java”. A port of [substack’s Gamma.js](https://github.com/substack/gamma.js) providing the Gamma Γ function and its natural log for real positive double-precision arguments. This implementation is within 1e-12 relative error of the output of Scipy for a range of inputs between 1e-10 to 1e10. That is, for `1e-10 < x < 1e10`, `Math.abs(scipyGamma(x) - gamma(x)) / Math.abs(scipyGamma(x)) < 1e-12`, and similarly for `gammaln` (log-Gamma).

## Install
Instructions for gradle, sbt, and lein on [JitPack](https://jitpack.io/#me.aldebrn/gamma-java) but for Maven, add the JitPack repository first:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
and then the dependency:
```xml
	<dependency>
	    <groupId>me.aldebrn</groupId>
	    <artifactId>gamma-java</artifactId>
	    <version>___TAG___</version>
	</dependency>
```
where you must replace `___TAG___` above with (presumably) the latest version on [JitPack](https://jitpack.io/#me.aldebrn/gamma-java).

## API
- Gamma: `double gamma(double z)`
- (natural) log Gamma: `double gammaln(double z)`

If you have Java 9 and like jshell, clone this repo and follow along—in a shell, run the following:
```
$ git clone https://github.com/fasiha/gamma-java.git
$ cd gamma-java
$ mvn com.github.johnpoth:jshell-maven-plugin:1.1:run -q
|  Welcome to JShell -- Version 11.0.1
|  For an introduction type: /help intro

jshell> import me.aldebrn.gamma.Gamma;
```
```java
jshell> import me.aldebrn.gamma.Gamma;

jshell> Gamma.gamma(5)
$2 ==> 23.999999999999996

jshell> Gamma.gamma(20)
$3 ==> 1.21645100408832224E17

jshell> Gamma.gamma(25)
$4 ==> 6.20448401733239E23

jshell> Gamma.gamma(120)
$5 ==> 5.574585761207872E196

jshell> Gamma.gammaln(120)
$6 ==> 453.0248962384962

jshell> Math.exp(Gamma.gammaln(120))
$7 ==> 5.574585761207872E196

jshell> Gamma.gammaln(250)
$8 ==> 1128.5237708729908
```

## Math

Relative error of 1e-12 from Scipy is ok for a lot of work. Substack’s Gamma.js and this repo use two simple iterative algorithms to calculate the output, one for small inputs (<100) and another for bigger.

If you want more precision, [open an issue](https://github.com/fasiha/gamma-java/issues) or [get in touch](https://fasiha.github.io/#contact): we should port Cephes to Java, which takes us to 1.2e-15 relative error to Scipy, which might be as close as you can get since I believe Scipy uses Cephes to implement these functions.

## License

Since this is a derivative of [substack’s Gamma.js](https://github.com/substack/gamma.js), released under MIT License.