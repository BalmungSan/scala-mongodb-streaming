/*
 * Copyright 2019-2019 All scala-mongodb-streaming contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.balmungsan.mongodb.streaming

import com.mongodb.{ConnectionString, MongoClientSettings, MongoDriverInformation}
import com.mongodb.reactivestreams.client.{MongoClient => JMongoClient, MongoClients => JMongoClients}

import scala.language.higherKinds

/**
 * A client-side representation of a MongoDB cluster.
 */
final class MongoClient[S[_]] private (val underlying: JMongoClient) extends AnyVal {

}

/**
 * A factory for MongoClient instances.
 */
object MongoClient {
  def apply[S[_]](): MongoClient[S] =
    createFromConnectionString(new ConnectionString("mongodb://localhost"))

  def apply[S[_]](connectionString: String): MongoClient[S] =
    createFromConnectionString(new ConnectionString(connectionString))

  def apply[S[_]](connectionString: ConnectionString): MongoClient[S] =
    createFromConnectionString(connectionString)

  def apply[S[_]](connectionString: ConnectionString, driverInformation: MongoDriverInformation): MongoClient[S] =
    createFromConnectionString(connectionString, Some(driverInformation))

  def apply[S[_]](settings: MongoClientSettings): MongoClient[S] =
    createFromSettings(settings)

  def apply[S[_]](settings: MongoClientSettings, driverInformation: MongoDriverInformation): MongoClient[S] =
    createFromSettings(settings, Some(driverInformation))

  private def createFromConnectionString[S[_]](
    connectionString: ConnectionString,
    extraDriverInformation: Option[MongoDriverInformation] = None
  ): MongoClient[S] =
    createFromSettings(
      MongoClientSettings
        .builder()
        .applyConnectionString(connectionString)
        .build(),
      extraDriverInformation
    )

  private def createFromSettings[S[_]](
    settings: MongoClientSettings,
    extraDriverInformation: Option[MongoDriverInformation] = None
  ): MongoClient[S] = {
    val driverInformationBuilder =
      extraDriverInformation.fold(
        MongoDriverInformation.builder()
      ) { extraInfo => MongoDriverInformation.builder(extraInfo) }

    val driverInformation =
      driverInformationBuilder
        .driverName(DriverInfo.Name)
        .driverVersion(DriverInfo.Version)
        .driverPlatform(DriverInfo.Platform)
        .build()

    new MongoClient(JMongoClients.create(settings, driverInformation))
  }
}
