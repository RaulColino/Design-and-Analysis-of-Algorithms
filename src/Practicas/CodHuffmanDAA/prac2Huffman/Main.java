public class Main {

    public static void main(String[] args) {
	CodificHuffman c = new CodificHuffman();

    // Método que calcula y muestra las frecuencias de ocurrencias de cada carácter en un fichero
	c.calcularFrecuenciasFichero("C:/Users/Usuario/Desktop/CodHuffmanDAA/Ejemplo.txt");

    // Método que a partir de un fichero dado crea uno nuevo codificado
	c.codificarPorHuffman("C:/Users/Usuario/Desktop/CodHuffmanDAA/Ejemplo.txt","C:/Users/Usuario/Desktop/CodHuffmanDAA/EjemploCodif.txt");

	// Método que recibe una palabra y devuelve la correspondiente codificación Huffman y las frecuencias de cada carácter
	c.codificarPalabra("esternocleidomastoideo");

    }
}





/*DAA Práctica 2  Autor: Raúl Colino Singh */
