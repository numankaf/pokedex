package Day5.InnerClassExample2;

public class Button implements Renderable {
    private ButtonRenderer renderer = new ButtonRenderer();
    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    class ButtonRenderer implements Renderer{

        @Override
        public void render() {
            System.out.println("Rendering Button");
        }
    }
}
