public class Main {

    static int PLACES = 10;

    public static void main(String[] args) {
        Place[] places = new Place[PLACES];
        /*for (int i = 0; i < places.length; i++) {
            places[i] = new Place();
        }*/
        for (Place p : places) {
            System.out.println(p);
        }
    }
}
