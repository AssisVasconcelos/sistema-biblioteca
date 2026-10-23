public class Livro extends ItemBiblioteca {

    public Livro(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return 14;
    }

    @Override
    public double getMultaPorDiaAtraso() {
        return 0.50;
    }
}