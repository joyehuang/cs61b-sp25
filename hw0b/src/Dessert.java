public class Dessert {
    int price;
    int flavour;
    static int numDesserts = 0;

    public Dessert(int flavour, int price) {
        this.price = price;
        this.flavour = flavour;
        numDesserts++;
    }

    public void printDessert() {
        System.out.println(flavour + " "+ price+ " " + numDesserts);
    }

    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
