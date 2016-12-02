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

package com.stratio.hermes.helpers

import scala.util.Random

/**
<<<<<<< HEAD
 * Helper to work with random data.
 */
object RandomHelper {

  /**
    * Returns a random element from a list.
    *
    * @param l initial list
    * @tparam T with the type of the list
    * @return a random element of the list or None if the list is empty.
    */
  def randomElementFromAList[T](l: List[T]): Option[T] = Random.shuffle(l).headOption

  /**
    * Returns length of a Integer element.
    *
    * @param n number to calculate length.
    * @return size of the integer.
    */
  def numberOfDigitsFromANumber(n: Int): Int = if (n == 0) 1 else math.log10(math.abs(n)).toInt + 1

  /**
    * Returns length of a Double element.
    *
    * @param n number to calculate length.
    * @return size of the double.
    */
  def numberOfDigitsFromANumber(n: Double): Int = math.abs(n).toString.length - 1
}
