/**
  * © 2017 Stratio Big Data Inc., Sucursal en España.
  *
  * This software is licensed under the Apache 2.0.
  * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  * See the terms of the License for more details.
  *
  * SPDX-License-Identifier:  Apache-2.0.
  */
package com.stratio.khermes.helpers.faker.generators

import breeze.stats.distributions.Gaussian

case class GaussianDistGenerator() {
  /**
    * It uses Breeze for generating random float numbers using a Gaussian distribution
    * @param mu mean
    * @param sigma standard deviation
    * @return random value
    */
  def runNext(mu: Double, sigma: Double) : Double = {
    Gaussian.distribution(mu, sigma).get()
  }
}
