package media_player.visao;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Visao {
    public static void main(String[] args) {
        JLabel label = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setText("Musicas");
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setBorder(border);
        label.setBounds(100,200,400,400);

//        ------------------------------------------
        JFrame frame = new JFrame();
        frame.setTitle("Player de musica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.add(label);
        frame.setLayout(null);
//        frame.pack(); ajusta automaticamente o tamanho da tela em relaçao aos componentes
    }
}
