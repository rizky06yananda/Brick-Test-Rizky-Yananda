import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        final Document document = Jsoup.connect("https://www.tokopedia.com/search?component_id=02.02.01.01&navsource=home&q=handphone&source=universe&st=product").get();
        try {
            String csv = "D:\\Java\\hasil.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            List<String> stringList = new ArrayList<>();
            for (Element element : document.getElementsByTag("div")) {
                final String title = element.getElementsByTag("div").select(".css-1f4mp12").text();
                final String rating = element.getElementsByTag("span").select(".css-etd83i").text();
                final String price = element.getElementsByTag("div").select(".css-rhd610").text();
                final String img = element.getElementsByTag("img").attr("src");
                int mouse = title.indexOf("MOUSE");
                int printer = title.indexOf("PRINTER");
                if (!(mouse >= 0) && !(printer >= 0)) {
                    if (!title.equals("") && !rating.equals("") && !price.equals("") && !img.equals("")) {
                        stringList.add(title + "," + rating + "," + price + "," + img);
                        System.out.println("title " + title + " rating " + rating + " price " + price + " image " + img);
                    }
                }

            }
            for (String data : stringList) {
                String[] split2 = data.split(",");
                writer.writeNext(split2);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

    }


}
