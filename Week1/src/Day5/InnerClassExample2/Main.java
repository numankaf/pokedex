package Day5.InnerClassExample2;

public class Main {
    public static void main(String[] args){
        Renderable r1 = new Button();
        Renderable r2 = new Form();
        RenderUtil.doRender(r1);
        RenderUtil.doRender(r2);
    }
}
