import java.util.Objects;

public abstract class Shape {
    private final String id;
    protected Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = Objects.requireNonNull(id, "id");
        this.renderer = Objects.requireNonNull(renderer, "renderer");
    }

    public String getId() {
        return id;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer");
    }

    public abstract String execute();
}
