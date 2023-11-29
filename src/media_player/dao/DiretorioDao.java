package media_player.dao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DiretorioDao {
    public void cadastrarDiretorio(String caminho) throws IOException {
        String caminhoArquivo = "arquivos/diretorios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write(caminho);
            writer.newLine();
        } 
        catch (IOException e) {
            throw e;
        }
    }

}