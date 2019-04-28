package SupportFeatures;

import java.util.Random;

public class ThemeAndTask {
    private final static String[] theme = {"Nature", "Color", "Campus"};
    private final static String[] task_nature = {"flower", "grass", "tree", "sky", "cloud"};
    private final static String[] task_color = {"Red", "Green", "Blue", "Orange",
            "Pink", "Purple", "Yellow"};
    private final static String[] task_campus = {"building", "sport", "brick", "glasses", "animal"};

    public static int randomNum(int max_bound) {
        Random rand = new Random();
        int n = rand.nextInt(max_bound);
        return n;
    }

    public static String ThemeGenerator() {
        return theme[randomNum(theme.length)];
    }

    public static String ThemeGuide(String themeName) {
        String words = "";
        if (themeName.equals("Nature")) {
            words = "We live in a beautiful world. Let's explore it!";
        }
        if (themeName.equals("Color")) {
            words = "Color is a power in our life. Let's find colors!";
        }
        if (themeName.equals("Campus")) {
            words = "Let's walk around the campus and find something interesting...";
        }
        return words +
                "Follow the instruction of each task and " +
                "take some pictures of the world surronding us!";
    }

    public static String taskGenerator(String taskType) {
        String taskName = "";
        if (taskType.equals("Nature")) {
            taskName = task_nature[randomNum(task_nature.length)];
        }
        if (taskType.equals("Color")) {
            taskName = task_color[randomNum(task_color.length)];
        }
        if (taskType.equals("Campus")) {
            taskName = task_campus[randomNum(task_campus.length)];
        }
        return "Take 4 photos that contains: " + taskName;
    }
}
