package MyImage;

public class StartImage {
    public static void main(String[] args) {
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        MyImage img = new MyImage(url);
        img.createTxt();
        img.makeGreyscale();
    }
}
