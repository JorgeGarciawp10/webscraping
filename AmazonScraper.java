package webscrapping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AmazonScraper {
    public static void main(String[] args) {
        scrapeProductosAmazon();
        }

        public static void scrapeProductosAmazon() {
            try {
                // Realiza una solicitud HTTP GET a la página de resultados de Amazon para la búsqueda "juegos"
                Document document = Jsoup.connect("https://www.amazon.com/s?k=juegos").get();

                // Encuentra todos los elementos de producto en la página
                Elements productos = document.select(".sg-col-inner .s-result-item");

                // Crea un BufferedWriter para escribir en el archivo de texto
                BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.csv"));

                // Itera sobre cada producto y extrae el título y el precio
                for (Element producto : productos) {
                    // Encuentra el elemento de título del producto
                    Element tituloElemento = producto.selectFirst(".a-size-base-plus");

                    // Encuentra el elemento de precio del producto
                    Element precioElemento = producto.selectFirst(".a-offscreen");

                    if (tituloElemento != null && precioElemento != null) {
                        String titulo = tituloElemento.text();
                        String precio = precioElemento.text();

                        // Escribe el nombre del producto y el precio en el archivo CSV
                        writer.write(titulo + "," + precio);
                        writer.newLine();
                    }
                }

                // Cierra el BufferedWriter
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
