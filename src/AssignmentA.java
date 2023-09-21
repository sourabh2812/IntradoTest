public class AssignmentA {

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            System.out.print(i + " "); // Print the line number

            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("Teamwork");
            } else if (i % 3 == 0) {
                System.out.println("Team");
            } else if (i % 5 == 0) {
                System.out.println("work");
            } else {
                System.out.println("noise");
            }
        }
    }
}
