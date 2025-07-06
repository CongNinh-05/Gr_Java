package co.smokefree.dto.leaderboard;

public class LeaderboardEntry {
    private String userName;
    private long daysSmokeFree;
    private double moneySaved;

    public LeaderboardEntry(String userName, long daysSmokeFree, double moneySaved) {
        this.userName = userName;
        this.daysSmokeFree = daysSmokeFree;
        this.moneySaved = moneySaved;
    }

    public String getUserName() {
        return userName;
    }

    public long getDaysSmokeFree() {
        return daysSmokeFree;
    }

    public double getMoneySaved() {
        return moneySaved;
    }
}