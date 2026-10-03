# Assignment 3 | Bridge Pattern

**Student:** Ismail Shargazin  
**Group:** SE-2529  
**Topic:** A - Drawing  
**Repository URL:** ADD_YOUR_GITHUB_REPOSITORY_URL  
**Base commit:** `3e83649893ab1d5f86b477116a9f4d6354c5b9c9`  
**Submitted source commit:** `7ee44ec63b30724cb481bd05ed4be2dc7404c64b`

The two independent dimensions are shape (Circle or Square) and rendering method (Vector, Raster, or ASCII). A shape stores a `Renderer` interface reference and delegates its low-level rendering to that object. Changing the renderer changes the description while preserving the same shape object and its domain data.

## Source role map

| Bridge role | Class | Source path |
| --- | --- | --- |
| Abstraction | `Shape` | `src/Shape.java` |
| A1: Refined Abstraction | `Circle` | `src/Circle.java` |
| A2: Refined Abstraction | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| I1: Concrete Implementor | `VectorRenderer` | `src/VectorRenderer.java` |
| I2: Concrete Implementor | `RasterRenderer` | `src/RasterRenderer.java` |
| I3: Concrete Implementor | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

The bridge field is `Shape.renderer` (`protected Renderer`). The abstract operation is `Shape.execute()`, implemented by `Circle.execute()` and `Square.execute()`; each calls its renderer through the interface. `Shape.setImplementation(Renderer)` replaces the implementation reference. `Main.runDemo()` contains T5: `original == afterSwitch` checks object identity, then ID and radius are compared before and after switching.

## Build and run

From the project root, using JDK 17 or newer:

```sh
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

No input or external libraries are required. `demo-output.txt` records a real run of the submitted source. The `PASS`/`FAIL` values and summary are calculated from comparisons in `Main`.

## Expected results

| Check | Actual behavior to verify | Expected result |
| --- | --- | --- |
| T1 | Circle radius 2, VectorRenderer | `VECTOR circle radius=2` |
| T2 | Circle radius 2, RasterRenderer | `RASTER circle radius=2 pixels` |
| T3 | Square side 3, VectorRenderer | `VECTOR square side=3` |
| T4 | Square side 3, RasterRenderer | `RASTER square side=3 pixels` |
| T5 | Same Circle, VectorRenderer then RasterRenderer | `sameObject=true`; `stateUnchanged=true`; before and after equal T1 and T2 strings |
| T6 | Circle radius 2, AsciiRenderer | `ASCII circle radius=2 art=(o)` |
| T7 | Square side 3, AsciiRenderer | `ASCII square side=3 art=[#]` |

The calculated summary is `SUMMARY: 7/7 PASS` when all results match. A failed check also prints its expected value.

## Extension and evidence

The base commit contains I1, I2, T1-T5, and the runtime switch. The second commit adds only `src/AsciiRenderer.java` and updates `src/Main.java` among Java files; `sources.txt` also gains the new source path. `extension.diff` was generated with:

```sh
git diff 3e83649893ab1d5f86b477116a9f4d6354c5b9c9 HEAD -- src > extension.diff
```

Bridge separates dimensions designed to vary independently. Adapter instead makes an existing incompatible interface usable by a client. Bridge adds interfaces and classes, which is a trade-off for flexibility.

## References

- Course handout: *Assignment 3 | Bridge Pattern*, sections 1-9.
- Course *Lecture 4*, Bridge Pattern.
- Course *Lecture 3*, Adapter Pattern (for the comparison in the report).

Before submitting, replace `ADD_YOUR_GITHUB_REPOSITORY_URL` in this README and `report.pdf` with the repository you pushed. Submit that repository URL and its exact final commit hash in Moodle. If changing this README creates another commit, use its new hash as the submitted commit while keeping the recorded base commit unchanged.
