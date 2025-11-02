import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPostX = frameWidth / 2;
    int getPlayerStartPostY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;

    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;

    public Logic() {
        // memuat gambar burung
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPostX, getPlayerStartPostY, playerWidth, playerHeight, birdImage);

        // memuat gambar pipa atas dan bawah
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        // membuat label skor di pojok kiri atas
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 200, 30);

        // timer untuk menambahkan pipa baru setiap 1.5 detik
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        pipesCooldown.start();

        // timer utama game (loop 60 kali per detik)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void setView(View view) {
        this.view = view;
        // menambahkan label skor ke tampilan
        view.setLayout(null);
        view.add(scoreLabel);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // menempatkan dua pipa (atas dan bawah) dengan jarak tertentu secara acak
    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // mengatur pergerakan burung dan pipa setiap frame
    public void move() {
        if (gameOver) return;

        // pergerakan vertikal burung
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        // menggerakkan setiap pipa ke kiri
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // menambah skor jika burung berhasil melewati pipa atas
            if (pipe.getImage() == upperPipeImage && pipe.getPosX() + pipeWidth == player.getPosX()) {
                score++;
                scoreLabel.setText("Score: " + score);
            }

            // mengecek tabrakan antara burung dan pipa
            if (checkCollision(player, pipe)) {
                gameOver = true;
                showGameOver();
            }
        }

        // mengecek jika burung jatuh ke bawah layar
        if (player.getPosY() > frameHeight - playerHeight) {
            gameOver = true;
            showGameOver();
        }
    }

    // fungsi untuk mengecek apakah burung bertabrakan dengan pipa
    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    // menampilkan pesan game over dan menghentikan permainan
    public void showGameOver() {
        gameLoop.stop();
        pipesCooldown.stop();
        JOptionPane.showMessageDialog(null, "Game Over!\nScore kamu: " + score + "\nTekan R untuk restart");
    }

    // mengulang permainan dari awal
    public void restartGame() {
        player.setPosX(playerStartPostX);
        player.setPosY(getPlayerStartPostY);
        player.setVelocityY(0);

        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: 0");

        gameOver = false;

        gameLoop.start();
        pipesCooldown.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // kontrol keyboard untuk lompat dan restart
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        } else if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
    }

    public void keyReleased(KeyEvent e) {}
}
