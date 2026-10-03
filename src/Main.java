import java.util.Objects;

public final class Main {
    private static int passed;
    private static int total;

    private Main() {
    }

    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.err.println("Usage: java -cp out Main --demo");
            return;
        }
        runDemo();
    }

    private static void runDemo() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        check("T1", "Circle + VectorRenderer",
                new Circle("circle-1", 2, vector).execute(), "VECTOR circle radius=2");
        check("T2", "Circle + RasterRenderer",
                new Circle("circle-2", 2, raster).execute(), "RASTER circle radius=2 pixels");
        check("T3", "Square + VectorRenderer",
                new Square("square-1", 3, vector).execute(), "VECTOR square side=3");
        check("T4", "Square + RasterRenderer",
                new Square("square-2", 3, raster).execute(), "RASTER square side=3 pixels");

        Circle original = new Circle("switch-circle", 2, vector);
        Circle afterSwitch = original;
        String idBefore = original.getId();
        int radiusBefore = original.getRadius();
        String before = original.execute();
        afterSwitch.setImplementation(raster);
        String after = afterSwitch.execute();
        boolean sameObject = original == afterSwitch;
        boolean stateUnchanged = Objects.equals(idBefore, afterSwitch.getId())
                && radiusBefore == afterSwitch.getRadius();
        boolean switchCorrect = sameObject && stateUnchanged
                && Objects.equals(before, "VECTOR circle radius=2")
                && Objects.equals(after, "RASTER circle radius=2 pixels");
        total++;
        if (switchCorrect) {
            passed++;
        }
        System.out.println("T5 " + status(switchCorrect)
                + " | Circle + VectorRenderer -> RasterRenderer"
                + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged
                + " | before=" + before + " | after=" + after);
        if (!switchCorrect) {
            System.out.println("  expected: sameObject=true, stateUnchanged=true, "
                    + "before=VECTOR circle radius=2, after=RASTER circle radius=2 pixels");
        }

        Renderer ascii = new AsciiRenderer();
        check("T6", "Circle + AsciiRenderer",
                new Circle("circle-3", 2, ascii).execute(), "ASCII circle radius=2 art=(o)");
        check("T7", "Square + AsciiRenderer",
                new Square("square-3", 3, ascii).execute(), "ASCII square side=3 art=[#]");

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static void check(String id, String classes, String actual, String expected) {
        boolean ok = Objects.equals(actual, expected);
        total++;
        if (ok) {
            passed++;
        }
        System.out.println(id + " " + status(ok) + " | " + classes + " | result=" + actual);
        if (!ok) {
            System.out.println("  expected: " + expected);
        }
    }

    private static String status(boolean ok) {
        return ok ? "PASS" : "FAIL";
    }
}
