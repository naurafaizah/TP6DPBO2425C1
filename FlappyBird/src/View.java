import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Logic logic;

    int width = 360; int height = 640;

    public View(Logic logic) {
        this.logic = logic;

        // mengatur ukuran panel sesuai ukuran frame
        setPreferredSize(new Dimension(width, height));

        // mengatur warna latar belakang menjadi biru langit
        setBackground(Color.cyan);

        // membuat panel dapat menerima input keyboard
        setFocusable(true);

        // menambahkan key listener dari logic untuk mendeteksi tombol
        addKeyListener(logic);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // memanggil fungsi untuk menggambar semua elemen game
        draw(g);
    }

    public void draw(Graphics g) {
        // menggambar burung (player)
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // menggambar semua pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // jika game over, tampilkan tulisan besar di tengah layar
        if (logic.gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", width / 2 - 120, height / 2 - 20);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.BLACK);
            g.drawString("Tekan R untuk restart", width / 2 - 110, height / 2 + 20);
        }
    }
}
