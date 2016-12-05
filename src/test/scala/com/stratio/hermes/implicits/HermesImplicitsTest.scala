/*
 * Copyright (C) 2016 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.hermes.implicits

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HermesImplicitsTest extends FlatSpec with Matchers {

  "A HermesImplicit" should "get a valid IP of a host" in {
    import com.stratio.hermes.implicits.HermesImplicits._
    getHostIP() should fullyMatch regex """.*(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3}).*"""
  }
}
