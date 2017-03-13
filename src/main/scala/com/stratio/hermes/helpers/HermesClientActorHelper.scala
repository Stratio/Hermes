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

object HermesClientActorHelper {
  def messageFeedback(hermesConfigOption: Option[String],
                      kafkaConfigOption: Option[String],
                      templateOption: Option[String]): String = {
    var m = List[String]()
    if(hermesConfigOption.isEmpty) m = "hermes" :: m
    if(kafkaConfigOption.isEmpty) m = "kafka" :: m
    if(templateOption.isEmpty) m = "template" :: m
    if(m.isEmpty) "Your configuration is OK" else s"Error: To start nodes is necessary to set ${m.mkString(" and ")} configuration."
  }

}
