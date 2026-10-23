public class Revista extends ItemBiblioteca {

    public Revista(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return 7;
    }

    @Override
    public double getMultaPorDiaAtraso() {
        return 1.00;
    }
}