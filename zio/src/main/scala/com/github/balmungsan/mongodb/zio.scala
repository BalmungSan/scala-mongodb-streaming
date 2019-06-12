/*
 * Copyright 2019-2019 All scala-mongodb-streaming contributors.
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

import _root_.zio.interop.reactiveStreams.Adapters
import _root_.zio.stream.{ZStream => ZioStream}
import org.reactivestreams.Publisher

object zio {
  implicit final val zioStream: Stream[ZioStream[Any, Throwable, ?]] =
    new Stream[ZioStream[Any, Throwable, ?]] {
      override def fromPublisher[T](publisher: Publisher[T]): ZioStream[Any, Throwable, T] =
        Adapters.publisherToStream(publisher, bufferSize = 16)
    }
}
