package media_player.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Usuario;
import media_player.modelo.UsuarioVIP;
import media_player.dao.DiretorioDao;
import media_player.dao.MusicaDao;
import media_player.dao.UsuarioDao;
import media_player.modelo.Musica;
import media_player.modelo.Playlist;

public class Player {
    Musica musicaAtual;
    Playlist playlistAtual;
    Usuario usuarioLogado;
    UsuarioDao u;
    DiretorioDao d;
    MusicaDao m;

    public boolean fazerLogin(String senha, String login) {
        for(Usuario i : u.getUsuarios()) {
            if(i.getLogin() == login && i.getSenha() == senha) {
                usuarioLogado = i;
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarUsuario(String senha, String login, String tipo) {
        try {
            u.cadastrarUsuario(login, senha, tipo);
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean cadastrarDiretorio(String caminho) {
        try {
            d.cadastrarDiretorio(caminho);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean criarPlaylist(String nome) {
        if (usuarioLogado instanceof UsuarioVIP) {
            return false;
        }
        try {
            ((UsuarioVIP)usuarioLogado).getP().cadastrarPlaylist(nome, usuarioLogado);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    

    public boolean adicionarMusicaEmPlaylist(String caminhoMusica, String nomePlaylist) {
        if(!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }
        
        Musica musicaAdicionada = null;
        for(Musica musica : m.getMusicas()) {
            if(musica.getCaminho() == caminhoMusica) {
                musicaAdicionada = musica;
                break;
            }
        }
        
        if(musicaAdicionada == null) {
            return false;
        }
        
        try {
            ((UsuarioVIP)usuarioLogado).getP().adicionarMusica(nomePlaylist, musicaAdicionada, usuarioLogado);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}