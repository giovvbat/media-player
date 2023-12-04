package media_player.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Musica;

public class MusicaDao {
    ArrayList<Musica> musicas;

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void carregarMusicas() throws IOException {
        String caminhoArquivo = "arquivos/musicas.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()){
            arquivo.getParentFile().mkdirs();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while((linha = br.readLine()) != null) {
                int indicePonto = linha.lastIndexOf(".");
                String nomeMusica = linha.substring(0, indicePonto);
                
                Musica m = new Musica(nomeMusica, linha);
                musicas.add(m);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }
}