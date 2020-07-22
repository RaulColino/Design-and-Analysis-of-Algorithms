public class Main {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();
        //Método para cargar los datos iniciales
        sudoku.inicializar("C:/Users/Usuario/Desktop/SudokuDAA/datos.txt");
        //Método para resolver el sudoku (Si no se han cargado los datos, lo resuelve sin los datos iniciales)
        sudoku.resolver();
    }
}












/*DAA Práctica 3  Autor: Raúl Colino Singh */