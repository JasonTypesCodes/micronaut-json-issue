package some.simulation

import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class SomeSimulation extends Simulation {
  val httpConf = http
    .baseUrl(baseUrl)
    .header("Accept", "application/json")

  def userCount: Int = getProperty("loadtests.users", "1000").toInt
  def baseUrl: String = getProperty("loadtests.baseurl", "http://localhost:8080")
  def rampDuration: Int = getProperty("loadtests.rampduration", "10").toInt
  def testDuration: Int = getProperty("loadtests.duration", "240").toInt

  /** * Before ***/
  before {
    println(s"baseUrl: ${baseUrl}")
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Maximum test duration: ${testDuration} seconds")
  }

  /** * Helper Methods ***/
  protected def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def getHealth() = {
    exec(http("health")
      .get("/health")
      .check(jsonPath("$.status").is("UP"))
      .check(status.is(200)))
  }

  def createSomething() = {
      exec(http("createSomething")
          .post("/something")
          .header("Content-Type", "application/json; charset=utf-8")
          .body(StringBody("{\"name\": \"Something Great!\", \"value\": 123456}"))
          .check(status.is(201)))
  }

  /** * Scenario Design ***/
  val scn = scenario("Some Service Load Test")
    .repeat(1) {
      exec(getHealth())
        .exec(createSomething())
    }

  /** * Setup Load Simulation ***/
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount).during(rampDuration seconds))
  ).protocols(httpConf).maxDuration(testDuration seconds)

  /** * After ***/
  after {
    println("Stress test completed")
  }
}
