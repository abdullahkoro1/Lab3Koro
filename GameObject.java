/**
 * Project: Space Game - Systems Integration
 * Purpose Details: GameObject Class Define for sender
 * Course: IST 242
 * Author: Abdullah Koro
 * Date Developed: 6/3/24
 * Last Date Changed: 6/9/24
 * Revision: 1
 */

/**
 * Represents GameObject Class
 */

public class GameObject

{

    private String name;
    private int score;

    // No-argument constructor

    public GameObject() {
        // Default constructor
    }

    // Constructor with parameters
    public GameObject(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    // Getter and setter methods for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for 'score'
    public int getScore() {
        return score;
    }

    public void setScore(int score)

    {
        this.score = score;
    }
}
