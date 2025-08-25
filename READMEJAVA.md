# Java Programming Projects

Collection of Java applications built during Summer 2025 demonstrating OOP principles, recursive algorithms, and interactive game development using JavaLib libraries.

## Projects Overview

### 1. Aim Target Game (aim.java)
Interactive bullseye clicking game with timed gameplay and precision scoring system (20/10/5/1 points based on click proximity).

**Key Implementation:**
- 30-tick timer with automatic termination
- Distance calculation using "Math.hypot()" for click accuracy
- Random target repositioning after hits

**Skills Used:**
- **Mathematical Thinking:** Distance formula for precision calculation between click position and target center
- **Event-Driven Design:** Mouse click handling and tick-based state updates
- **Game Logic:** Score zones based on percentage of radius (15%, 50%, 75%)

### 2. Digital Clock (clock.java)
Real-time clock display with 12-hour AM/PM format and automatic rollover handling.

**Key Implementation:**
- Modular arithmetic for minute/hour rollovers (59→0, 23→0)
- Edge case handling for midnight/noon transitions
- String formatting with leading zeros

**Skills Used:**
- **Modular Arithmetic:** Hour and minute rollover calculations
- **Logical Thinking:** Complex conditional logic for 12-hour format conversion
- **Edge Case Handling:** Special cases for 12:00 AM (midnight) and 12:00 PM (noon)

### 3. Collatz Conjecture Visualizer (collatzConjecture.java)
Interactive visualization of the 3n+1 problem with keyboard controls.

**Key Implementation:**
- Even: n/2, Odd: 3n+1
- Right arrow key progression
- Termination detection at n=1

**Skills Used:**
- **Algorithm Implementation:** Classic mathematical conjecture algorithm
- **State Management:** Tracking sequence progression until termination
- **Mathematical Thinking:** Understanding and implementing the 3n+1 problem

### 4. Domino Chain Validator (domino.java)
Linked list implementation validating legal domino sequences where touching numbers must match ([3|4]→[4|2] is valid because the 4s touch).

Key Implementation:
- Chain validation where right value of one domino must equal left value of next
- Double counting (dominoes where left equals right, like [5|5])
- Strong link detection (when two consecutive doubles connect properly, like [2|2]→[2|2])
- Interface-based list abstraction

Skills Used:
- Recursion: Traversing the domino chain with base and recursive cases
- Accumulator Pattern: Tracking previous values through recursive calls
- Abstraction: Interface IDomino with empty/non-empty implementations
- Logical Thinking: Complex validation rules for matching and strong links

### 5. Candidate Hiring System (hireProblem.java)
Alternative solution to the secretary problem - a famous algorithm challenge where you must interview candidates one-by-one in random order and make immediate hire/reject decisions (no going back to rejected candidates). 
Traditional solution: reject the first 37% then hire next candidate better than all previous first 37%. 
My approach uses absolute thresholds instead, and if you can't find a candidate that meets them, then you have to take the last person.

Key Implementation:
- 4 threshold criteria (teamPlayer>80, softSkills>75, workEthic>70, technicalSkills>60)
- Immediate accept/reject decisions with no returns
- Guaranteed selection: last candidate becomes fallback if none meet criteria

Skills Used:
- Algorithm Design: Modified secretary problem with guaranteed selection
- Accumulator Pattern: Tracking last candidate as fallback option
- Recursion: List traversal with accumulated state
- Decision Logic: Multi-criteria boolean evaluation

### 6. Planetary System Simulator (planet.java)
Solar system model with 8 planets and 10 moons using abstract classes and composition.

Key Implementation:
- Abstract CelestialBody base class
- Mutation methods for properties
- Recursive moon/planet counting across the system

Skills Used:
- Abstraction: Abstract base class for celestial bodies, interface for moon lists
- Accumulator Recursion Pattern: Building comma-separated moon lists and finding the largest moon across nested planet/moon structures.
- Object Composition: Planets containing moon lists, systems containing planets
- Design Patterns: Visitor-like pattern for system-wide operations

## Testing Strategy

All projects include comprehensive unit testing using the Tester library:
- Boundary conditions and edge cases
- State mutations and deterministic Random testing
- Helper method isolation
  
