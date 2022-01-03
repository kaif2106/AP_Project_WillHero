package plswrk.willherofx;

class Player_Date{
    private final int score;
    private String name;
    private final String date;
    Player_Date(int score, String name, String date)
    {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
