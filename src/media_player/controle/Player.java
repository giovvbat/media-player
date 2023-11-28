package media_player.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Usuario;
import media_player.modelo.Musica;
import media_player.modelo.Playlist;

public class Player {
    ArrayList<Usuario> usuarios;
    ArrayList<Musica> musicas;
    Musica musicaAtual;
    Playlist playlistAtual;
    Usuario usuarioLogado;

    private void cadastrarUsuarios() throws IOException {
        String caminhoArquivo = "arquivos/usuarios.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String login;
            while((login = br.readLine()) != null) {
                String senha = br.readLine();
                Usuario usuario = new Usuario();
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuarios.add(usuario);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    
}