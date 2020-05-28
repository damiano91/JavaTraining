package Image;

public class StartImage {
    public static void main(String[] args) {
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        Image img = new Image(url);
        img.createTxt();
        img.makeGreyscale();
    }
}
