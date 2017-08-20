### Hexagonal Architecture Day

This work has an Endpoint and one "right" aka input port CollectionBuilder adapted by a CollectionBuilder from CSV File or DB.

There is no "left" aka output port.

The entrypoint is not a main but REST API.

Domain can "act" on port but not "react" to ports. 
The reacting part is the entrypoint which is "acting" on domain which acts on ports.

We could define a input port for Collection which can read from HttpServletRequest or Rabbit MQ Message.
