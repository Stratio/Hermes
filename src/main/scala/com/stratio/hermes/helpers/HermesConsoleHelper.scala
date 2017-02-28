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

import com.stratio.hermes.actors.HermesClientActor
import jline.console.ConsoleReader

case class HermesConsoleHelper(client: HermesClientActor) {

  lazy val reader = createDefaultReader()

  //scalastyle:off
  def parseLines(hermesConfig: Option[String] = None,
                 kafkaConfig: Option[String] = None,
                 template: Option[String] = None): Unit = {
    reader.readLine.trim match {
      case "set hermes" =>
        val config = setConfiguration(hermesConfig, kafkaConfig, template)
        parseLines(config, kafkaConfig, template)

      case "set kafka" =>
        val config = setConfiguration(hermesConfig, kafkaConfig, template)
        parseLines(hermesConfig, config, template)

      case "set template" =>
        val config = setConfiguration(hermesConfig, kafkaConfig, template)
        parseLines(hermesConfig, kafkaConfig, config)

      case value if value.startsWith("start") =>
        startStop(value, "start", hermesConfig, kafkaConfig, template)
        parseLines(hermesConfig, kafkaConfig, template)

      case value if value.startsWith("stop") =>
        startStop(value, "stop", hermesConfig, kafkaConfig, template)
        parseLines(hermesConfig, kafkaConfig, template)

      case "ls" =>
        ls
        parseLines(hermesConfig, kafkaConfig, template)

      case "clear" =>
        clearScreen
        parseLines(hermesConfig, kafkaConfig, template)

      case "exit" | "quit" | "bye" =>
        System.exit(0)

      case "" =>
        parseLines(hermesConfig, kafkaConfig, template)

      case _ =>
        printNotFoundCommand
        parseLines(hermesConfig, kafkaConfig, template)
    }
  }

  def setConfiguration(hermesConfig: Option[String] = None,
                       kafkaConfig: Option[String] = None,
                       template: Option[String] = None): Option[String] = {
    println("Press Control + D to finish")
    val parsedBlock = Option(parseBlock())
    reader.setPrompt("hermes> ")
    parsedBlock
  }

  def startStop(line: String,
                firstWord: String,
                hermesConfig: Option[String] = None,
                kafkaConfig: Option[String] = None,
                template: Option[String] = None): Unit = {
    val ids = line.replace(firstWord, "").trim.split(",").map(_.trim).filter("" != _)
    ids.map(id => println(s"Sending $id start message"))
    client.start(hermesConfig, kafkaConfig, template, ids)
    reader.setPrompt("hermes> ")
  }

  def ls: Unit = {
    println("Node Id                                Status")
    println("------------------------------------   ------")
    client.ls
    Thread.sleep(HermesConsoleHelper.TimeoutWhenLsMessage)
    reader.setPrompt("hermes> ")
  }

  def clearScreen: Unit = {
    reader.clearScreen()
  }

  def printNotFoundCommand: Unit = {
    println("Command not found. Type help to list available commands.")
  }

  //scalastyle:on

  def parseBlock(result: String = ""): String = {
    reader.setPrompt("")
    Option(reader.readLine()).map(currentLine => parseBlock(s"$result\n$currentLine")).getOrElse(result)
  }

  protected[this] def createDefaultReader(): ConsoleReader = {
    val reader = new ConsoleReader()
    reader.setHandleUserInterrupt(true)
    reader.setExpandEvents(false)
    reader.setPrompt("hermes> ")
    reader
  }
}

object HermesConsoleHelper {

  val TimeoutWhenLsMessage = 200L
}
