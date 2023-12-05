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
    javazoom.jl.player.Player playMP3;

    public void carregarUsuarios() {
        try {
            u.carregarUsuarios();
        }
        catch(Exception e) {
            throw e;
        }
    }

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
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean carregarDiretorios() {
        try {
            d.carregarDiretorios();
        }
        catch (IO Exception e) {
            return false;
        }

        return true;
    }

    public boolean carregarMusicas() {
        try {
            m.carregarMusicas();
        }
        catch (IO Exception e) {
            return false;
        }

        return true;
    }

    public boolean carregarPlaylists() {
        if(!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }

        try {
            ((UsuarioVIP)usuarioLogado).getP().carregarPlaylists(usuarioLogado);
        }
        catch (Exception e) {
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
        } 
        catch (Exception e) {
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
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public void tocarMusica(String caminho) {
        try {
            FileInputStream file = new FileInputStream(caminho);
            javazoom.jl.player.advanced.AdvancedPlayer playMP3 = new javazoom.jl.player.advanced.AdvancedPlayer(file);

            this.playMP3 = playMP3;
            new Thread() {
                @Override
                public void run() {
                    try {
                        playMP3.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
        catch (Exception e) {
            throw e;
        }
    }

    public void pararMusica() {
        if(playMP3 != null) {
            playMP3.close();
        }
    }
}