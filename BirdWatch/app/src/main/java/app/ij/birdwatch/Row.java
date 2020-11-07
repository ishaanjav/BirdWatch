package app.ij.birdwatch;

class Row {
    public String bird1, bird2;
    public boolean present;
    public int prob1, prob2;

    public Row(String b1, float p1, String b2, float p2) {
        bird1 = b1;
        prob1 = (int) (100 * p1);
        prob2 = (int) (100 * p2);
        bird2 = b2;
        present = true;
    }

    public Row(String b1, float p1) {
        bird1 = b1;
        prob1 = (int) (100 * p1);
        present = false;
    }
}
