public class Node {

    private int level, profit, bound;
    private float weight;

    public Node(int level, int profit, float weight) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
    }

    public Node(int level, int profit, int bound, float weight) {
        this.level = level;
        this.profit = profit;
        this.bound = bound;
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
