# FilRouge Adapter

-- for the PNEditor

This is a project created for an IT course at IMT Atlantique engineer school.

More specifically, this is the FilRouge project, Exercice 6 from the UE MAPD of the TAFF DCL.

## Content

- "Référentiel des documents"
- Glossary
- Self evaluation
  - Comments
  - Modularity
  - Safety
  - Evolutivity and Maintainability
- Source management

## "Référentiel des documents"

- The code we wrote was copy pasted to the package `org.pneditor.petrinet.models.hamcyt`
- The code of the adapter is in the package `org.pneditor.petrinet.adapters.imta`
- The self-evaluation is presented in this here README file
- The "référentiel des documents" -- this list

Note: the given projet's code has been modified to run both on Windows and Linux.
Note 2: we did not modify the source code of the model we produced earlier.

## Glossary

The following terms are used in the project:

Place:
A node of the Petri network which can contain a number of tokens

Counter:
The number of tokens contained by a place

Transition:
A node of the Petri network which can be fired (pulled).

Pulling:
A synonyme of fireing. It can be used both for a transition or for an arc.

Arc:
In a Petri network, arcs can only link one place and one transition together.
Arcs are oriented, see "ArcPull" and "ArcPush"

ArcPull:
An arc, oriented from a place to a transition. Firing an ArcPull will remove
tokens from the place.

ArcPush:
An arc, oriented from a transtition to a place. Firing an ArcPush will add
tokens to their place.

Multiplicity (of an arc):
The "value" of the arc. The number of token it takes or adds to the place it's
linked to when it is fired. Note however that ArcPullVacuum and ArcPullZero
do not have multiplicities: They follow their own logic

## Self evaluation

### Comments

- [x] Code written in English
- [x] Comments all in the same language
- [x] Javadoc on all exposed methods
- [x] Comments dedicated to maintainers

### Style and conventions

- [x] Homogeneous indentation (tabs)
- [x] UpperCamelCase for Classes and lowerCamelCase for variables
- [?] Methods are ordered -- We preserved the order of the parent classes
- [x] Names are meaningful (NB: subjective criterion)

### Modularity

- [x] No class in the root package
- [x] ~~`package.info`~~ `package-info.java` describes each package
      responsibility
- [ ] Each class responsibility is defined
- [x] No public attributes
- [x] Methods are short (except for tests) -- (record of 14 lines held by addRegularArc)

### Safety

- [x] Methods protect themselves -- no other exposed methods than the required ones
- [ ] ~~All methods have at least one test~~ No test
- [ ] ~~Easy way to run all tests (`mvn test`)~~

### Evolutivity and Maintainability

- [x] Explicit imports
- [x] No magic constants (no constants at all, by good design)
- [x] Factorized code (as much as reasonable)

## Source management

The source are managed using git. A copy of the development repository is
available [on Github](https://github.com/mathieucaroff/FilRougeAdapter).

## Note for collaborators

Sample command to run the project on linux:

```
/usr/lib/jvm/java-8-oracle/bin/java -Dfile.encoding=UTF-8 -classpath /mnt/c/ox/filrouge/adapter/bin:/mnt/c/ox/filrouge/adapter/lib/hamcrest-core-1.3.jar:/mnt/c/ox/filrouge/adapter/lib/javax.xml.bind.jar:/mnt/c/ox/filrouge/adapter/lib/junit-4.12.jar:/mnt/c/ox/filrouge/adapter/lib/org.pneditor.petrinet.adapters-8.jar:/mnt/c/ox/filrouge/adapter/lib/org.pneditor.petrinet.models-8.jar org.pneditor.editor.Main
```
