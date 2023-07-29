package de.tsvlengfeld.reports.beitragproabteilung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;

@QuarkusMainTest
public class BeitragProAbteilungMainTest {

    @Test
    @Launch("World")
    public void testLaunchCommand(LaunchResult result) {
        Assertions.assertTrue(result.getOutput().contains("Hello World"));
    }

}
