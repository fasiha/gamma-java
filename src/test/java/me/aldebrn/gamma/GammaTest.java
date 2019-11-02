package me.aldebrn.gamma;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.junit.Test;

/*
#!/usr/bin/env python

import numpy as np
from scipy.special import gammaln, gamma
import json

x = np.logspace(-10, 10, 2000)
y = gammaln(x)
z = gamma(x)

with open("test.json", "w") as out: out.write(json.dumps(list(zip(x, y, z))))
*/

/**
 * Compare Gamma to Scipy
 */
public class GammaTest {
  private double logTol = 1e-12;
  private double tol = 1e-12;

  private static double relerr(double expected, double actual) {
    return (actual == expected) ? 0 : Math.abs(actual - expected) / Math.abs(expected);
  }

  @Test
  public void loadAndCompare() {
    Gson gson = new Gson();

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test.json");
    String raw = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));

    double expected[][] = gson.fromJson(raw, double[][].class);

    for (double[] expectedPair : expected) {
      double x = expectedPair[0];
      String msg = "x=" + String.valueOf(x);
      {
        double y = expectedPair[1];
        double yActual = Gamma.gammaln(x);
        assertEquals(msg, 0, Math.abs(relerr(y, yActual)), logTol);
      }
      {
        double z = expectedPair[2];
        double zActual = Gamma.gamma(x);
        if (Double.isFinite(z)) {
          assertEquals(msg, 0, Math.abs(relerr(z, zActual)), tol);
        } else {
          assertTrue(z == zActual);
        }
      }
    }
  }
}
