package media_player.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Usuario;
import media_player.dao.UsuarioDao;
import media_player.modelo.Musica;
import media_player.modelo.Playlist;

public class Player {
    ArrayList<Musica> musicas;
    Musica musicaAtual;
    Playlist playlistAtual;
    Usuario usuarioLogado;
    UsuarioDao u;

    public boolean fazerLogin(String senha, String login) {
        for(Usuario i : u.getUsuarios()) {
            if(i.getLogin() == login && i.getSenha() == senha) {
                usuarioLogado = i;
                return true;
            }
        }
        return false;
    }

    
}