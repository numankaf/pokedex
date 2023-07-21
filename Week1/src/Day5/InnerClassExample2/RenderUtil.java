package Day5.InnerClassExample2;

public class RenderUtil {
    public static void doRender(Renderable renderable){
        renderable.getRenderer().render();
    }
}
