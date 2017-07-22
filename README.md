# InMemoryQueue

Problem Statement:

Design an efficient in-memory queueing system with low latency requirements
Functional specification:

● Queue holds JSON messages

● Allow subscription of consumers to messages that match a particular
expression

● Consumers register callbacks that will be invoked whenever there is a new
message

● Queue will have one producer and multiple consumers

● Consumers might have dependency relationships between them. For ex, if
there are three consumers A, B and C. One dependency relationship can be
that C cannot consumer a particular message before A and B have consumed
it. C -> (A,B) (-> means must process after)

● Queue is bounded in size and completely held in-memory. Size is configurable.

● Handle concurrent writes and reads consistently between producer and
consumers.

● Provide retry mechanism to handle failures in message processing.


