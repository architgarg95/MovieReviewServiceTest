package MovieReviewSystem.Users;

public enum UserType {

    VIEWER(1),
    CRITIC(2),
    EXPERT(3),
    ADMIN(4);

    private final int scoreMultiplier;

    private UserType(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }

    public int getScoreMultiplier() {
        return scoreMultiplier;
    }
}
