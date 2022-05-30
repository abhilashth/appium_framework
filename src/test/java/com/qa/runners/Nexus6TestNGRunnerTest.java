package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

/**
 * An example of using TestNG when the test class does not inherit from
 * AbstractTestNGCucumberTests but still executes each scenario as a separate
 * TestNG test.
 */
@CucumberOptions(
        plugin = {"pretty"
                , "html:target/Nexus6/cucumber"
                , "summary"
                , "me.jvt.cucumber.report.PrettyReports:target/Nexus6/cucumber-html-reports"}
        , features = {"src/test/resources"}
        , glue = {"com.qa.stepdef"}
        , dryRun = false
        , monochrome = true
        , tags = "@test"
)
public class Nexus6TestNGRunnerTest extends RunnerBase {
}