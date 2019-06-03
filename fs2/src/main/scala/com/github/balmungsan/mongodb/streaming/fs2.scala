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

import _root_.fs2.{Stream => Fs2Stream}
import cats.effect.ConcurrentEffect
import org.reactivestreams.Publisher

import scala.language.higherKinds

object fs2 {
  implicit def fs2Stream[F[_]: ConcurrentEffect]: Stream[Fs2Stream[F, ?]] =
    new Stream[Fs2Stream[F, ?]] {
      override def fromPublisher[T](publisher: Publisher[T]): Fs2Stream[F, T] =
        _root_.fs2.interop.reactivestreams.fromPublisher(publisher)
    }
}
