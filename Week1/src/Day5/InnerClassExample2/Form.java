package Day5.InnerClassExample2;

public class Form  implements Renderable{
    private FormRenderer renderer = new FormRenderer();

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    class FormRenderer implements  Renderer{

        @Override
        public void render() {
            System.out.println("Rendering Form");
        }
    }
}
