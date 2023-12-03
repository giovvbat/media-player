package media_player.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Musica;
import media_player.modelo.Playlist;
import media_player.modelo.Usuario;

public class PlaylistDao {
    ArrayList<Playlist> playlists;
    
    public void carregarPlaylists(ArrayList<Usuario> usuarios) throws IOException {
        String caminhoPasta = "arquivos/playlists";
        File pasta = new File(caminhoPasta);

        if (!pasta.exists()){
            pasta.getParentFile().mkdirs();
        }

        for(File f : pasta.listFiles()) {
            int indiceInicio = f.getName().indexOf("_");
            int indiceFinal = f.getName().lastIndexOf(".txt");
            String nomePlaylist = f.getName().substring(indiceInicio, indiceFinal);

            try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                String nomeUsuario = br.readLine();
                int idUsuario = Integer.parseInt(br.readLine());

                Usuario usuario = null;

                for(Usuario u : usuarios) {
                    if(u.getLogin() == nomeUsuario && u.getId() == idUsuario) {
                        usuario = u;
                        break;
                    }
                }

                if(usuario == null) {
                    continue;
                }

                ArrayList<Musica> musicas = new ArrayList<>();

                String tituloMusica;
                while((tituloMusica = br.readLine()) != null) {
                    String caminhoMusica = br.readLine();

                    Musica m = new Musica(tituloMusica, caminhoMusica);
                    musicas.add(m);
                }

                Playlist playlist = new Playlist(nomePlaylist, musicas, usuario);
                playlists.add(playlist);
            }
            catch(IOException e) {
                throw e;
            }
        }
    }

    public void cadastrarPlaylist(String nome, ArrayList<Musica> musicas, Usuario usuarioDono) throws IOException {
        String caminhoPlaylist = "arquivos/playlists/playlist_" + nome + ".txt";
        File arquivoPlaylist = new File(caminhoPlaylist);

        if (!arquivoPlaylist.exists()){
            arquivoPlaylist.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoPlaylist))) {
            writer.write(usuarioDono.getLogin());
            writer.newLine();
            writer.write(usuarioDono.getId());
            writer.newLine();

            for(Musica musica : musicas) {
                writer.write(musica.getNome());
                writer.newLine();
                writer.write(musica.getCaminho());
                writer.newLine();
            }
        } 
        catch (IOException e) {
            throw e;
        }
        
        Playlist p = new Playlist(nome, musicas, usuarioDono);
        playlists.add(p);
    }

    public void adicionarMusica(Playlist playlist, Musica musica) throws IOException {
        String caminhoPlaylist = "arquivos/playlists/playlist_" + playlist.getTitulo() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoPlaylist, true))) {
            writer.write(musica.getNome());
            writer.newLine();
            writer.write(musica.getCaminho());
            writer.newLine();
        } 
        catch (IOException e) {
            throw e;
        }

        Playlist playlistArray = null;
        for(Playlist p : playlists) {
            if(p.getTitulo() == playlist.getTitulo()) {
                playlistArray = p;
            }
        }

        playlistArray.getMusicas().add(musica);
    }
}
