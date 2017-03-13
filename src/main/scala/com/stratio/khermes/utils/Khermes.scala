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

package com.stratio.khermes.utils

import com.stratio.khermes.constants.KhermesConstants
import com.stratio.khermes.implicits.KhermesSerializer
import com.stratio.khermes.utils.generators._

import scala.language.postfixOps

/**
 * Khermes util used for to generate random values.
 */
case class Khermes(locale: String = KhermesConstants.DefaultLocale) extends KhermesSerializer {

  object Name extends NameGenerator(locale)

  object Number extends NumberGenerator

  object Geo extends GeoGenerator(locale)

  object Datetime extends DatetimeGenerator

  object Music extends MusicGenerator(locale)

  object Email extends EmailGenerator(locale)

}
