public class Item {
    private float weight;
    private int value;


    public Item(float weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}