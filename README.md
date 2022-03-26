# Simulations for the 100 Prisoners and a Light Bulb Riddle

## The Riddle
Taken from William Wu:

>One hundred prisoners have been newly ushered into prison. The warden tells
them that starting tomorrow, each of them will be placed in an isolated cell, unable to communicate amongst each other. Each day, the warden will choose
one of the prisoners uniformly at random with replacement, and place him in
a central interrogation room containing only a light bulb with a toggle switch.
The prisoner will be able to observe the current state of the light bulb. If he
wishes, he can toggle the light bulb. He also has the option of announcing that
he believes all prisoners have visited the interrogation room at some point in
time. If this announcement is true, then all prisoners are set free, but if it is
false, all prisoners are executed.
The warden leaves, and the prisoners huddle together to discuss their fate.
**Can they agree on a protocol that will guarantee their freedom?**

We assume that the initial state of the bulb is `OFF` and that the prisoners can count how many days have elapsed.

## Protocol 1 (_The Leadered Protocol_)
This is the first one that I discovered, which I call the _leadered protocol_. Below is William Wu's definition of this protocol (his "single counter protocol"):      


Letting prisoners have different roles, we assign one prisoner to be “the counter." They will maintain an integer variable in their head that is initialized to `1`. Call this variable `T`. Upon entering the room, prisoners adhere to the following instructions:
* If you are not the counter:
    * If the bulb is `OFF`, and you have never turned the bulb `ON` before, turn it `ON`.
    * If the bulb is `ON`, do nothing.
* If you are the counter:
    * If the bulb is `OFF`, do nothing.
    * If the bulb is `ON`, turn it `OFF`, and set `T=T+1`.
    * If `T = n`, announce that all prisoners have visited.

The expected runtime of this protocol is 
$$
\begin{align*}
\mathbb{E} [X] &= (n-1)n + \sum_{i=1}^{n-1}\frac{1}{i} \\
&= n^2 - n + nH_{n-1}
\end{align*}
$$

where $H_{n}$ denotes the $n$-th harmonic number. Please refer to Wu's paper in the References section for more details.

### Implementation
`leaderer_protocol.py` implements the leadered protocol. 

Syntax:

    usage: leadered_protocol.py [-h] [-p PRISONER_COUNT] [-i ITERATIONS]
Sample:

    $ python leadered_protocol.py -p 100 -i 1000
    Prisoner count: 100
    Number of iterations: 1000
    Average: 10423.786

## Protocol 2 (_The Egalitarian Protocol_)
This protocol was suggested by a friend in high school. Every prisoner is assigned to a day from `0` to `n-1` (assuming there are `n` prisoners), and keeps a checklist. The checklist is used to record which prisoners the owner of the checklist has seen so far. The days are counted in `mod n`. When prisoner `p` enters the room on day `d`, they adhere to the following instructions, in this order:

* If the bulb is `ON`:
    * Record in your checklist that prisoner `(d-1) mod n` has visited the room.
    * Turn `OFF` the bulb.
    * If the checklist is _complete_ (i.e. if you have recorded `n-1` unique prisoners in the checklist), announce that all prisoners have visited.
* If this is your day (i.e. `p=d`):
    * Turn `ON` the bulb.


### Implementation
`Egaliter.java` implements the egalitarian protocol. The variables `prisonerCount` and `iterations` within the code can be modified.

### Improvement
The prisoners can speed up the above protocol by turning the bulb `ON` at day `d` also if they know that prisoner `d` has been to the room. They do not have to be prisoner `d` themselves. The above protocol then becomes the following:

* If the bulb is `ON`:
    * Record in your checklist that prisoner `(d-1) mod n` has visited the room.
    * Turn `OFF` the bulb.
    * If the checklist is _complete_ (i.e. if you have recorded `n-1` unique prisoners in the checklist), announce that all prisoners have visited.
* If this is your day (i.e. `p=d`) or you know that prisoner `d` has visited the room:
    * Turn `ON` the bulb.

`EgaliterGelismis.java` implements this improved version of the egalitarian protocol.

## References
https://www.ocf.berkeley.edu/~wwu/papers/100prisonersLightBulb.pdf
