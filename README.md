# scala-mongodb-streaming

**Scala lightweight, type-safe, _(not opinionated)_ streaming driver for MongoDB**.

This driver is just a simple wrapper over
the [**MongoDB Reactive Streams Java Driver**]( https://github.com/mongodb/mongo-java-driver-reactivestreams/tree/master).
Which provides a more _"Scala-friendly"_ API.

The driver is divided into the `core` module,
which provides a generic abstraction over the java driver.
And a set of implementation modules,
which each one provide a concrete instance of the `Stream[S[_]]` typeclass
for different streams of the _Scala ecosystem_.

### Usage

|Module|Description|
| ----------------------------------------- |:--------------|
|`"com.github.balmungsan" %% "scala-mongodb-streaming" % version`|Core functionality.|
|`"com.github.balmungsan" %% "scala-mongodb-streaming-fs2" % version`| `fs2.Stream[F, T]` implementation _(For any **F** which has an instance of `cats.effect.ConcurrentEffect[F]`, like **IO**)_.|

## Motivation

My very first Scala project used mongo,
at that time we used the [**MongoDB Scala Driver**](https://github.com/mongodb/mongo-scala-driver).
I really liked the friendliness of the API.
However, as time passed, and I started learning about FP,
streams became my preferred abstraction for dealing with data-flows.
That is how I ended up using the reactive driver
together with [**fs2-reactive-streams**](https://fs2.io/).

I have to say that everything has been great until now.
But nevertheless, since the reactive driver is written in Java,
sometimes the interaction with the API is not as pleasant as I would like.

This is an attempt to provide the same functionality with a more friendlier API, and make it independent to the underlying streaming implementation.

## License

The project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more details.

Code in scala-mongodb-streaming is derived in part from:

+ MongoDB Reactive Streams Java Driver, licensed under the Apache License 2.0.
+ FS2, licensed under the MIT License.

```
Copyright 2019-2019 All scala-mongodb-streaming contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
