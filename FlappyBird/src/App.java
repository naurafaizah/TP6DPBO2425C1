import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // membuat jendela utama dengan judul flappy bird
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // membuat objek logic dan view
        Logic logika = new Logic();
        View tampilan = new View(logika);

        // menghubungkan view dengan logic
        logika.setView(tampilan);

        // memberi fokus ke panel agar input keyboard bisa terbaca
        tampilan.requestFocus();

        // menambahkan panel view ke dalam frame
        frame.add(tampilan);
        frame.pack();

        // menampilkan jendela game
        frame.setVisible(true);
    }
}
